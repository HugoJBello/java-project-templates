package com.hjbello.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hjbello.restfulws.SystemInfo;
import com.hjbello.restfulws.WebcamRestWS;

public class TestWebcamRestWSInfoTest {

	@Test
	public void test() throws JsonProcessingException {
		WebcamRestWS wr = new WebcamRestWS();
		SystemInfo sysInfo = wr.obtainWebcamInfoRestWS();
		System.out.println("testing web service client");
		System.out.println(sysInfo);
		assertNotNull("asserting info is not null", sysInfo);
	}

}
