package com.hjbello.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hjbello.restfulws.RequestCapture;
import com.hjbello.restfulws.WebcamRestWS;



public class TestWebcamRestWS {

	@Test
	public void test() throws JsonProcessingException {
		WebcamRestWS wr = new WebcamRestWS();
		RequestCapture request = new RequestCapture(1);
		System.out.println("testing web service client");
		System.out.println(wr.invokeWecamRestWS(request));
		assertNotNull("Asserting that return class is not null", wr.invokeWecamRestWS(request));
	}

}
