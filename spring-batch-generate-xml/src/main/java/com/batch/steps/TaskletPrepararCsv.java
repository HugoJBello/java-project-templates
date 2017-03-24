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
public class TaskletPrepararCsv implements Tasklet{

	private static final Log log = LogFactory.getLog(TaskletPrepararCsv.class);
	
	private String pathInputFile = "resources/inputs/input5.csv";
	
	private String pathInputFileMod;
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		log.info("------------------------------------------");
		log.info("preparando el csv para generar los xml");
		log.info("------------------------------------------");
		
 		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("pathInputFile", pathInputFile);

		pathInputFileMod = pathInputFile.split(".csv")[0] + "_mod.csv"; //resources/input4_mod.csv";
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("pathInputFileMod", pathInputFileMod);

		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get(pathInputFile));	
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

		//guardar output en un archivo
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(pathInputFileMod), "UTF-8"));
		try {
			out.write(output);
		} finally {
			out.close();
		}
 


		return RepeatStatus.FINISHED;
	}

}
