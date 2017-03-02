package com.example.batch; 

import com.example.batch.NoOpItemReader;

import junit.framework.TestCase;

public class NoOpItemReaderTests extends TestCase {

	private NoOpItemReader reader = new NoOpItemReader();
	
	public void testReadOnce() throws Exception {
		assertEquals("Hello world!", reader.read());
	}

	public void testReadTwice() throws Exception {
		reader.read();
		assertEquals(null, reader.read());
	}

}
