package com.example.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("reader")
public class NoOpItemReader implements ItemReader<String> {
	
	private static final Log log = LogFactory.getLog(NoOpItemReader.class);

	private int index = 0;
	
	/**
	 * Reads next record from input
	 */
	public String read() throws Exception {
		if (index < 1) {
			log.info("-------------------");
			index++;
			return "done";
		}
		else {
			return null;
		}
		
	}

}
