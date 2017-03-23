package com.batch.steps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Component("taskletStep2")
public class TaskletStep2 implements Tasklet{

	private static final Log log = LogFactory.getLog(TaskletStep2.class);

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		log.info("------------------------------------------");
		log.info("Inside step 2");
		log.info("------------------------------------------");

		String inputCsvPath = "resources/input4_mod.csv";
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get(inputCsvPath));		
		
		log.info("creating xml files");
		String prototypeXmlPath= "resources/ejemploMock.xml";
		Utils obtain = new Utils();
		
		int cont = 1;
		input.remove(0);
		for (String line: input){
			line=line.replaceAll("\"" , "").replaceAll("¥", "Ñ").replaceAll("¢","ó");
			String[] camposCsv = line.split(";");

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(prototypeXmlPath);

			// Obtenemos el elemento raiz
			Node cuerpoSoap = doc.getFirstChild();
 
			//tns:RespuestaRecogidaTicketFirmado
			int ultimoTicket = 56523371+ cont;

			Node respuesta = cuerpoSoap.getChildNodes().item(1).getChildNodes().item(1);
			log.info(respuesta.getNodeName());
			respuesta.getAttributes().getNamedItem("tns:ticketID").setTextContent(""+ultimoTicket);
			
			//obtemos el nodo contenidoMensaje
			Node contenidoMensaje = cuerpoSoap.getChildNodes().item(1)
					.getChildNodes().item(1)
					.getChildNodes().item(1);


			//modificamos atributo de metadato 
			Node metadatos = contenidoMensaje.getChildNodes().item(1);
			log.info(metadatos.getNodeName());
			metadatos.getAttributes().getNamedItem("tns:ticketID").setTextContent(""+ultimoTicket);

			//modificamos mensajeID
			Node mensajeId =metadatos.getChildNodes().item(1);
			log.info(mensajeId.getNodeName());
			String mensajeIdStr = camposCsv[0];
			mensajeId.setTextContent(mensajeIdStr);

			for(int i = 0; i<12; i++){
				if (i<10){
				Node nodo = metadatos.getChildNodes().item(2*i+1);
				nodo.setTextContent(camposCsv[i]);
				} else if(i>10) {
					int j=i+1;
					Node nodo = metadatos.getChildNodes().item(2*j+1);
					nodo.setTextContent(camposCsv[j]);
					
				}
			}

			
			//obtenemos el nodo eventoRecogida
			Node eventoRecogida = obtain.obtainNode("EventoRecogida",metadatos);		 
			eventoRecogida.getAttributes().item(0).setTextContent(camposCsv[12]);

			// fecha
			eventoRecogida.getChildNodes().item(1).setTextContent(camposCsv[13]);
			// razón social
			Node personaJuridica = eventoRecogida.getChildNodes().item(3).getChildNodes().item(1);
			personaJuridica.getChildNodes().item(1).setTextContent("razón soc. modif");
			personaJuridica.getChildNodes().item(3).setTextContent("nie modificado");

			// datos recogida
			Node datosRecogida = obtain.obtainNode("DatosRecogida",metadatos);		 
			log.info(datosRecogida.getNodeName());

			//ape:DatoRecogida
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader("<a>" + camposCsv[14]+"</a>"));
			Document doc2 = docBuilder.parse(is);
			NodeList nodos2 = doc2.getFirstChild().getChildNodes();
			
			


			for (int j =0; j< nodos2.getLength()/2;  j++){
				Element datoRecogida = doc.createElement("ape:DatoRecogida");
				Element campo = doc.createElement("ape:Campo");
				Element valor = doc.createElement("ape:Valor");
				campo.setTextContent(nodos2.item(j*2).getTextContent());
				valor.setTextContent(nodos2.item(j*2+1).getTextContent());
				datoRecogida.appendChild(campo);
				datoRecogida.appendChild(valor);

				datosRecogida.appendChild(datoRecogida);
			}

			

			// Guardamos el archivo
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("resources/outputs/output"  +"_" + mensajeIdStr + ".xml"));
			transformer.transform(source, result);	 		
			log.info("Done");


			cont++;
		}
		return RepeatStatus.FINISHED;
	}

}
