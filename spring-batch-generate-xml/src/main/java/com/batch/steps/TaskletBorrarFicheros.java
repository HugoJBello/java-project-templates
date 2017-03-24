package com.batch.steps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Component("taskletBorrarFicheros")
public class TaskletBorrarFicheros implements Tasklet{

	private static final Log log = LogFactory.getLog(TaskletBorrarFicheros.class);

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		log.info("------------------------------------------");
		log.info("Deleting files from previous executions");
		log.info("------------------------------------------");

		String fileNamePattern = "output";
		String pathToDelete = "resources/outputs";
		File baseDirectory = new File(pathToDelete);

		ArrayList<File> filesWithPattern = new ArrayList<>();

		log.info("Searching files");
		for (File file : baseDirectory.listFiles()){
			if (file.getName().startsWith(fileNamePattern)){
				filesWithPattern.add(file);
				log.info("found file:");
				log.info(file.getName());
			}
		}

		log.info("Deleting files");
		for (File file: filesWithPattern){
			try {
				log.info("Deleting file " + file.getName());
				file.delete();
			} catch (Exception e) {
				log.error("Error found deleting file "  + file.getName());// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return RepeatStatus.FINISHED;
	}

}
