package com.sec.model;

import lombok.Data;

@Data
public class SuccessResponse extends ApiResponse {
	
	private Object response;

	public SuccessResponse(int codigo, String mensaje, Object response) {
		super(codigo, mensaje);
		this.response= response;
		
		// TODO Auto-generated constructor stub
	}
}

