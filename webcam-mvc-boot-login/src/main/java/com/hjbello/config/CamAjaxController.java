package com.hjbello.config;

import static org.junit.Assert.assertNotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hjbello.restfulws.CapturedMovement;
import com.hjbello.restfulws.RequestCapture;
import com.hjbello.restfulws.WebcamRestWS;

@RestController
public class CamAjaxController {
	@Secured({"ROLE_ADMIN"})
	//@JsonView(CapturedMovement.class)
	@RequestMapping(value = "/invokeRestful", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<CapturedMovement> capture (@RequestBody RequestCapture request) throws JsonProcessingException{
		System.out.println("Invoking web service to capture cam images");
		WebcamRestWS wr = new WebcamRestWS();
		System.out.println(request.getSeconds());
		return new ResponseEntity<CapturedMovement>(wr.invokeWecamRestWS(request), HttpStatus.OK);
	}

}
