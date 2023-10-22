package com.sec.model;

import lombok.Data;

@Data
public class FailResponse extends ApiResponse{

	public String detalles;	
	public FailResponse(int codigo, String mensaje, String detalles) {
		super(codigo,mensaje);
		this.detalles=detalles;
	}

}
