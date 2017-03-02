package com.example.batch;

import com.example.batch.NoOpItemWriter;

import junit.framework.TestCase;

public class NoOpItemWriterTests extends TestCase {

	private NoOpItemWriter writer = new NoOpItemWriter();
	
	public void testWrite() throws Exception {
		writer.write(null); // nothing bad happens
	}

}
