package com.accenture.redsocial.dto;

public class Credenciales {
	
	private String user;
	private String pass;
	private int numRow;
	
	
	public Credenciales(String user, String pass, int numRow) {
		
		this.user = user;
		this.pass = pass;
		this.numRow = numRow;
	}

	
	public Credenciales(int numRow) {
		
		this.numRow = numRow;
	}


	public Credenciales() {
		
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	public int getNumRow() {
		return numRow;
	}


	public void setNumRow(int numRow) {
		this.numRow = numRow;
	}
	
	
	

}
