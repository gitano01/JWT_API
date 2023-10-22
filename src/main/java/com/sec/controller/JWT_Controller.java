package com.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sec.model.ApiResponse;
import com.sec.model.FailResponse;
import com.sec.model.Response;
import com.sec.model.SuccessResponse;
import com.sec.model.UserRequest;
import com.sec.model.UserVality;
import com.sec.serviceDao.JWT_ServiceDao;

@RestController
@RequestMapping(path = "/v1")
public class JWT_Controller {

	@Autowired
	JWT_ServiceDao jwtService;

	@PostMapping(path = "/jwt/generateToken")
	public ResponseEntity<ApiResponse> generateToken(@RequestBody UserRequest user) throws Exception {


		ApiResponse response = null;
		try {
			
			Response jwt = jwtService.getToken(user);

			if (jwt != null && !jwt.equals("")) {
				response = new SuccessResponse(200, "Operación Exitosa", jwt);
				return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
			} else {
				response = new FailResponse(400, "Operación Fallida", "No se pudo genera el token");
				return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {

			response = new FailResponse(500, "Operación Fallida", e.getMessage());
			return new ResponseEntity<ApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/jwt/checkTokenValidate")
	public ResponseEntity<ApiResponse> validateToken(@RequestBody UserVality user_vality) {

		ApiResponse response = null;
//		if(user_vality != null) {
//			if()
//		}

		boolean check = jwtService.isTokenValid(user_vality.getJwt(), user_vality.getUser());

		if (check) {
			response = new SuccessResponse(200, "Operacion Exitosa", "El token es válido");
			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		} else {
			response = new FailResponse(400, "Operacion Fallida", "El token no es válido");
			return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		}

	}

}
