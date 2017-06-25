package com.hjbello;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hjbello.webcam.DetectMotion;
 
 
@RestController
public class CaptureImageRestController {
 
    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }
 
//    @RequestMapping("/hello/{player}")
//    public Message message(@PathVariable String seconds) {//REST Endpoint.
// 
//        Message msg = new Message(player, "Hello " + player);
//        return msg;
//    }
    
    @RequestMapping(value = "/capture/", method = RequestMethod.POST)
    public ResponseEntity<CapturedMovement> messagePost(@RequestBody RequestCapture request)  {//REST Endpoint.
    	int seconds = request.getSeconds();
    	
    	DetectMotion detector = new DetectMotion("" + seconds);
    	try {
			detector.record();
		} catch (IOException e) {
			e.printStackTrace();
		}
     	ArrayList<String> imagesPath = detector.getListOfObtaiedImages();
     	ArrayList<byte[]> imagesBase64 = detector.getListOfObtainedImageBase64(); 
    	
    	CapturedMovement response = new CapturedMovement();
    	response.setImagesPath(imagesPath);
    	response.setImagesBase64(imagesBase64);
    	
        return new ResponseEntity<CapturedMovement>(response, HttpStatus.OK);
    }
}
