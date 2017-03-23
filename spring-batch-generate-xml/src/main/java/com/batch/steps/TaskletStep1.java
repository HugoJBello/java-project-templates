package com.batch.steps;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component("taskletStep1")
public class TaskletStep1 implements Tasklet{

	private static final Log log = LogFactory.getLog(TaskletStep1.class);

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		log.info("------------------------------------------");
		log.info("Inside step 1");
		log.info("------------------------------------------");

		String path= "resources/input4.csv";
		String pathOutput = "resources/input4_mod.csv";

		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get(path));	
		String output="";
		input.remove(0);


		// Cambiar los inicios de fila que empiezan por "CASO..." y los ;CASO
		for (String line:input){
			line=line.replaceAll("\"" , "").replaceAll("¥", "Ñ").replaceAll("¢","ó");
			line= line;
			if (line.trim().startsWith("CASO")){
				output= output +" * " + line;
			} else {
				output= output + "\n" + line;
			}
		}
		output = output.replaceAll("\n", " ; ; ; ; ; \n");

		//output=output.replaceAll(";CASO", " * CASO");
		//output=output.replaceAll("> \\*", ">;");
		log.info(output);


		// Unificar los CASO 1, CASO1 CASO1.3 y demás cosas así


		//guardar output en un archivo
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(pathOutput), "UTF-8"));
		try {
			out.write(output);
		} finally {
			out.close();
		}
		//	PrintWriter out = new PrintWriter(pathOutput);
		//	out.println(output);
		//	out.close();


		return RepeatStatus.FINISHED;
	}

}
