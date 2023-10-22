package com.sec.model;

import java.util.Date;

import lombok.Data;

@Data
public class Response {
		private String tokenType;
		private String accesToken;
		private String expiresIn;
		private String issuedAt;
		
		public Response(String tokenType, String accesToken, String expiresIn, String issuedAt) {
			super();
			this.tokenType = tokenType;
			this.accesToken = accesToken;
			this.expiresIn = expiresIn;
			this.issuedAt = issuedAt;
		}
		
		
	}
