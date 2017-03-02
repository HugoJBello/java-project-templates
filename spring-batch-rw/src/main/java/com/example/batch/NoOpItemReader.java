package com.example.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("reader")
public class NoOpItemReader implements ItemReader<String> {
	
	private int index = 0;
	
	/**
	 * Reads next record from input
	 */
	public String read() throws Exception {
		if (index < 1) {
			System.out.println("------------------------------");
			index++;
			return "done";
		}
		else {
			return null;
		}
		
	}

}
