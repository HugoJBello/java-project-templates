package com.hjbello.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileCleaner {


	public void prepararCsv (String path, String fileName) throws IOException{
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get(path));	
		String output="";

		// Cambiar los inicios de fila que empiezan por "CASO..." y los ;CASO
		for (String line:input){
			line=line.replaceAll("\"" , "").replaceAll("¥", "Ñ").replaceAll("¢","ó");

			if (line.startsWith("CASO")){
				output= output +" * " + line;
			} else {
				output= output + "\n" + line;
			}
		}
		output=output.replaceAll(";CASO", " * CASO");

		
		 

		// Unificar los CASO 1, CASO1 CASO1.3 y demás cosas así


		//guardar output en un archivo
		PrintWriter out = new PrintWriter(fileName);
		out.println(output);
		out.close();

	}
}
