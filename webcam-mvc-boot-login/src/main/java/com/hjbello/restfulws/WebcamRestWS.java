package com.hjbello.restfulws;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebcamRestWS {

	private String url = "http://localhost:8080";
	private String user = "user";
	private String password = "1234";

 
	public CapturedMovement invokeWecamRestWS ( RequestCapture requestCapture) throws JsonProcessingException{
		//First we prepare the authentification
		String urlCapture = url + "/capture/";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		BasicCredentialsProvider credentialsProvider =  new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
		httpClient.setCredentialsProvider(credentialsProvider);
		ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(httpClient);

		//Here we convert the request to json, and then to httpEntity
		ObjectMapper mapper = new ObjectMapper();
		String requestCaptureJson = mapper.writeValueAsString(requestCapture);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request= new HttpEntity(requestCaptureJson, headers);

		//We invoke the web service
		RestTemplate restTemplate = new RestTemplate(rf);
		CapturedMovement response = restTemplate.postForObject(urlCapture,request, CapturedMovement.class);
		return response;
	}
	
	public SystemInfo obtainWebcamInfoRestWS () throws JsonProcessingException{
		//First we prepare the authentification
		String urlInfo = url + "/camInfo/";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		BasicCredentialsProvider credentialsProvider =  new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
		httpClient.setCredentialsProvider(credentialsProvider);
		ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(httpClient);

		//We invoke the web service
		RestTemplate restTemplate = new RestTemplate(rf);
		SystemInfo response = restTemplate.getForObject(urlInfo,SystemInfo.class);
		return response;
	}

}
