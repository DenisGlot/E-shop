package com.denisgl.dao.jdbc.exceptions;

public class NoPropertiesLoaded extends RuntimeException{

	
	private static final long serialVersionUID = 12346234235354634L;
	
	private final static String error = "Your properties for jdbc were not loaded!";
	
	public NoPropertiesLoaded() {
		super(error);
		
	}

}
