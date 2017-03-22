package com.hjbello.main;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLFromCSV {


	public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException, TransformerException {

		String inputXmlPath = "resources/ejemploMock.xml";
		String inputCsvPath = "resources/input4.csv";
		Utils obtain = new Utils();
		
		String inputModificadoCsvPath = "resources/input4_mod.csv";
		FileCleaner fc = new FileCleaner();
		fc.prepararCsv(inputCsvPath, inputModificadoCsvPath);
		
		ArrayList<String> input = (ArrayList<String>) Files.readAllLines(Paths.get("resources/input3.csv"));		


		int cont = 1;
		input.remove(0);
		for (String line: input){
			line=line.replaceAll("\"" , "").replaceAll("¥", "Ñ").replaceAll("¢","ó");
			String[] camposCsv = line.split(";");

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputXmlPath);

			// Obtenemos el elemento raiz
			Node cuerpoSoap = doc.getFirstChild();
 
			//tns:RespuestaRecogidaTicketFirmado
			int ultimoTicket = 56523371+ cont;

			Node respuesta = cuerpoSoap.getChildNodes().item(1).getChildNodes().item(1);
			System.out.println(respuesta.getNodeName());
			respuesta.getAttributes().getNamedItem("tns:ticketID").setTextContent(""+ultimoTicket);
			
			//obtemos el nodo contenidoMensaje
			Node contenidoMensaje = cuerpoSoap.getChildNodes().item(1)
					.getChildNodes().item(1)
					.getChildNodes().item(1);


			//modificamos atributo de metadato 
			Node metadatos = contenidoMensaje.getChildNodes().item(1);
			System.out.println(metadatos.getNodeName());
			metadatos.getAttributes().getNamedItem("tns:ticketID").setTextContent(""+ultimoTicket);

			//modificamos mensajeID
			Node mensajeId =metadatos.getChildNodes().item(1);
			System.out.println(mensajeId.getNodeName());
			mensajeId.setTextContent(camposCsv[0]);

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
			System.out.println(datosRecogida.getNodeName());

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
			StreamResult result = new StreamResult(new File("resources/outputs/output" + cont +".xml"));
			transformer.transform(source, result);	 		
			System.out.println("Done");


			cont++;
		}


	}
}