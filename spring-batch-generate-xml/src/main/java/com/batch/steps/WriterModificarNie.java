package com.batch.steps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Dummy {@link ItemWriter} which only logs data it receives.
 */
@Component("writerModificarNie")
@Scope("step")
public class WriterModificarNie implements ItemWriter<ArrayList<File>> {

	private static final Log log = LogFactory.getLog(WriterModificarNie.class);


	@Value("#{jobExecutionContext['pathInputFile']}")
	private String pathInputFile;
	
	@Value("#{jobExecutionContext['pathInputFileMod']}")
	private String pathInputFileMod;
	
	public void write(List<? extends ArrayList<File>> data) throws Exception {
		Iterator ite = data.iterator();
		ArrayList<String> linesCsv = (ArrayList<String>) Files.readAllLines(Paths.get(pathInputFileMod));		
		Utils obtain = new Utils();

		while (ite.hasNext()) {
			ArrayList<File> files = (ArrayList<File>) ite.next();
			log.info("-------------------------------------------");
			log.info("modificando los nies y ref de los xml");
			log.info("-------------------------------------------");
			linesCsv.remove(0);

			int cont=0;
			for (File file: files){
				try {
					boolean contieneCaso = false;

					// Sacamos el campo que necesitamos de la tabla

					//AEAT_TITULAR_PROCEDIMIENTO_NIF
					String nieIntegracion = "";
					String niePreproduccion = "";

					//AEAT_REFERENCIA
					String refIntegracion = "";
					String refPreProduccion ="";

					String mensajeIdStr ="";
					for (String line : linesCsv){
						String[] camposCsv = line.split(";");
						if (file.getName().contains(camposCsv[0])){
							mensajeIdStr = camposCsv[0];
							nieIntegracion = camposCsv[15];
							niePreproduccion = camposCsv[16];
							refIntegracion = camposCsv[18];
							refPreProduccion = camposCsv[19];
							if (refIntegracion.contains("CASO") || refPreProduccion.contains("CASO")
									|| nieIntegracion.contains("CASO") || niePreproduccion.contains("CASO")){
								contieneCaso=true;
							}
							break;
						}
					}

					// Modificamos el xml a partir de lo obtenido	
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document docInt = docBuilder.parse(file.getPath());
					Node cuerpoSoap = docInt.getFirstChild();
					Node contenidoMensaje = cuerpoSoap.getChildNodes().item(1)
							.getChildNodes().item(1)
							.getChildNodes().item(1);
					Node metadatos = contenidoMensaje.getChildNodes().item(1);
					Node datosRecogida = obtain.obtainNode("DatosRecogida",metadatos);	

					Document docPro = docBuilder.parse(file.getPath());
					Node cuerpoSoapPro = docPro.getFirstChild();
					Node contenidoMensajePro = cuerpoSoapPro.getChildNodes().item(1)
							.getChildNodes().item(1)
							.getChildNodes().item(1);
					Node metadatosPro = contenidoMensajePro.getChildNodes().item(1);
					Node datosRecogidaPro = obtain.obtainNode("DatosRecogida",metadatosPro);

					NodeList nodosIntPadre =datosRecogida.getChildNodes();

					//Cambiamos el nie de titular en el campo AEAT_TITULAR_PROCEDIMIENTO_NIF
					// y ponemos los datos de la tabla
					for(int k =0; k< nodosIntPadre.getLength(); k++){
						NodeList nodosInt  = datosRecogida.getChildNodes().item(k).getChildNodes();
						NodeList nodosPro  = datosRecogidaPro.getChildNodes().item(k).getChildNodes();

						for (int j =0; j< nodosInt.getLength();  j++){
							Node nodo = nodosInt.item(j);
							if (nodo.getTextContent().contains("AEAT_TITULAR_PROCEDIMIENTO_NIF")){
								if(!nieIntegracion.equals("")) {
									nodosInt.item(j+1).setTextContent(nieIntegracion);
								}
								if(!niePreproduccion.equals("")) {
									nodosPro.item(j+1).setTextContent(niePreproduccion);
								}
							}
							if (nodo.getTextContent().contains("AEAT_REFERENCIA")){
								if(!refIntegracion.equals("")) {
									nodosInt.item(j+1).setTextContent(refIntegracion);
								}
								if(!refPreProduccion.equals("")) {
									nodosPro.item(j+1).setTextContent(refPreProduccion);
								}
							}
						}



					}

					// Guardamos el archivo
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					
					DOMSource sourceInt = new DOMSource(docInt);
					String pathInt = contieneCaso ? "resources/outputs/output" +"_" + mensajeIdStr + "_int_CASO" + ".xml" : "resources/outputs/output" +"_" + mensajeIdStr + "_int" + ".xml"; 
					StreamResult resultInt = new StreamResult(new File(pathInt));
					transformer.transform(sourceInt, resultInt);

					DOMSource sourcePro = new DOMSource(docPro);
					String pathPro = contieneCaso ? "resources/outputs/output" +"_" + mensajeIdStr + "_pro_CASO" + ".xml" : "resources/outputs/output" +"_" + mensajeIdStr + "_pro" + ".xml"; 
					StreamResult resultPro = new StreamResult(new File(pathPro));
					transformer.transform(sourcePro, resultPro);

				} catch (Exception e) {
					log.error("error modificando "  + file.getName());
					e.printStackTrace();
				}
				cont++;
			}
		}

	}

}
