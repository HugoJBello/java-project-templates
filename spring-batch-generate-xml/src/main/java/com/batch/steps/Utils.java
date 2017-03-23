package com.batch.steps;

 
import java.io.File;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

public class Utils {
	public Node obtainNode(String str, Node nodeImput){
		Node output = null;
 		for (int i=1; i< nodeImput.getChildNodes().getLength(); i++){
			Node nodo= nodeImput.getChildNodes().item(i);
 			if (nodo.getNodeName().contains(str)){
				output = nodo;	
  				break;
			}	
		}
		return output;
	}
	

	public String getStringFromDoc(org.w3c.dom.Document doc)    {
        try
        {
           DOMSource domSource = new DOMSource(doc);
           StringWriter writer = new StringWriter();
           StreamResult result = new StreamResult(writer);
           TransformerFactory tf = TransformerFactory.newInstance();
           Transformer transformer = tf.newTransformer();
           transformer.transform(domSource, result);
           writer.flush();
           return writer.toString();
        }
        catch(TransformerException ex)
        {
           ex.printStackTrace();
           return null;
        }
    }
	
	
}
