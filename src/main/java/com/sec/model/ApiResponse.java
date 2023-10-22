package com.sec.model;

import lombok.Data;

@Data
public class ApiResponse {

	private int codigo;
	private String mensaje;
	
	public ApiResponse(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		
	}
	
}
