package com.sec.model;

public class Credenciales {

	private String credencialconsumersecret;
	private String rolTipo;
	
	public String getCredencialconsumersecret() {
		return credencialconsumersecret;
	}
	public void setCredencialconsumersecret(String credencialconsumersecret) {
		this.credencialconsumersecret = credencialconsumersecret;
	}
	public String getRolTipo() {
		return rolTipo;
	}
	public void setRolTipo(String tipo) {
		this.rolTipo = tipo;
	}
	
	@Override
	public String toString() {
		return "CredencialesResponse [credencialconsumersecret="
				+ credencialconsumersecret + ", roltipo="
				+ rolTipo + "]";
	}
	
}
