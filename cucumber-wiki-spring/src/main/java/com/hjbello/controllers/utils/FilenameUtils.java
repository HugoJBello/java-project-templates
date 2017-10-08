package com.hjbello.controllers.utils;

public class FilenameUtils {
	
	public FilenameUtils() {
		super();
	}

	public String cleanFilename (String str){
		str = str.replaceAll("[^a-zA-Z0-9/]" , "_").toLowerCase();
		return str;
	}

}
