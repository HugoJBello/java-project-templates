package com.batch.steps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("readerModificarCasos")
public class ReaderModificarCasos implements ItemReader<ArrayList<File>> {


	private String dirXml= "resources/outputs/";
	private int index = 0;
	private static final Log log = LogFactory.getLog(ReaderModificarCasos.class);
	private String pattern = "CASO";
	
	private ArrayList<File> filesWithPattern = new ArrayList<File>();

	public ArrayList<File> read() throws Exception {
		if (index==0){
			log.info("-----------------------------------------------");
			log.info("Leyendo los xml generados por el paso anterior");
			log.info("-----------------------------------------------");

			for (File file : new File(dirXml).listFiles()){
				if (file.getName().contains(pattern)){
					filesWithPattern.add(file);
				}
					log.info("found file:");
					log.info(file.getName());
				
			}
			index++;
			return filesWithPattern;
		} else {
			return null;
		}
	}

}
