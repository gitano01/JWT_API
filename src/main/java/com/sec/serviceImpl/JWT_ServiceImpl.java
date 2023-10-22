package com.sec.serviceImpl;

import com.google.gson.Gson;
import com.sec.model.ApiResponse;
import com.sec.model.Credenciales;
import com.sec.model.Response;
import com.sec.model.SuccessResponse;
import com.sec.model.UserRequest;
import com.sec.serviceDao.JWT_ServiceDao;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWT_ServiceImpl implements JWT_ServiceDao {

	@Autowired
	CredencialesServiceInvoke credencialService;
	
	private String SECRET_KEY = "";

	public Response getToken(UserRequest user) throws Exception {

		ApiResponse response = null;
		// Ir a validar el usuario y traer la credencial para generar el token
		String json = new Gson().toJson(user);
		SuccessResponse  success = new Gson().fromJson(credencialService.postDataToApi(json), SuccessResponse.class);   
	    String cred = new Gson().toJson(success.getResponse());
	    Credenciales c = new Gson().fromJson(cred,Credenciales.class);
		
		SECRET_KEY = c.getCredencialconsumersecret();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usuarionombre",user.getUsername());
		map.put("rolTipo",c.getRolTipo());
		
		
		

		return getToken(map, user);
	}

	public Response getToken(Map<String, Object> extraClaims, UserRequest user) throws Exception {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'00:00");
			Date issuedAt = new Date(System.currentTimeMillis());
			Date expiredIn = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
			String jwt = Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername()).setIssuedAt(issuedAt)
					.setExpiration(expiredIn).signWith(getKey(), SignatureAlgorithm.HS256).compact();
			return new Response("bearer", jwt, sdf.format(expiredIn), sdf.format(issuedAt));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

//	public Key getKey() {
//		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//		return Keys.hmacShaKeyFor(keyBytes);
//	}
	
	public Key getKey() {
		byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
		System.out.println(keyBytes.toString());
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token, String user) {
		final String username = getUsernameFromToken(token);
		System.out.println(username.toString());
		return (username.equals(user) && !isTokenExpired(token));
	}

	public Claims getAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	public boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}

}
