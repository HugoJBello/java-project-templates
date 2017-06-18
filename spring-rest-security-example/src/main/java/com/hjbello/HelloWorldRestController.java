package com.hjbello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
public class HelloWorldRestController {
 
    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }
 
    @RequestMapping("/hello/{player}")
    public Message message(@PathVariable String player) {//REST Endpoint.
 
        Message msg = new Message(player, "Hello " + player);
        return msg;
    }
    
    @RequestMapping(value = "/hello-post/{player}", method = RequestMethod.POST)
    public ResponseEntity<Message> messagePost(@PathVariable String player, @RequestBody Message messageIn) {//REST Endpoint.
    	String playerOut = "hi  " + messageIn.getName();
    	String textOut = messageIn.getText();
    	System.out.println("we got " + messageIn.getName());
    	System.out.println("---------");
    	Message messageOut = new Message (playerOut,textOut);
    	
        return new ResponseEntity<Message>(messageOut, HttpStatus.OK);
    }
}
