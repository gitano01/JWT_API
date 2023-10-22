package com.sec.serviceDao;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import com.sec.model.Response;
import com.sec.model.UserRequest;

import io.jsonwebtoken.Claims;

public interface JWT_ServiceDao {

	public Response getToken(UserRequest user) throws Exception;
	public Response getToken(Map<String,Object> extraclaims, UserRequest user) throws Exception;
	//public Key getKey(String user);
	public String getUsernameFromToken(String token);
	public boolean isTokenValid(String token, String user);
	public Claims getAllClaims(String token);
	public <T> T getClaim(String token, Function<Claims,T> claimsResolver);
	public Date getExpiration(String token);
	public boolean isTokenExpired(String token);
}
