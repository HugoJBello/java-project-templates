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
@Component("writerModificarCasos")
@Scope("step")
public class WriterModificarCasos implements ItemWriter<ArrayList<File>> {

	private static final Log log = LogFactory.getLog(WriterModificarCasos.class);


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
			log.info("modificando los xml con casos");
			log.info("-------------------------------------------");
			linesCsv.remove(0);

			int cont=0;
			for (File file: files){
				try {

					// Modificamos el xml a partir de lo obtenido	
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					Document doc1 = docBuilder.parse(file.getPath());
					Node cuerpoSoap = doc1.getFirstChild();
					Node contenidoMensaje = cuerpoSoap.getChildNodes().item(1)
							.getChildNodes().item(1)
							.getChildNodes().item(1);
					Node metadatos = contenidoMensaje.getChildNodes().item(1);
 					Node datosRecogida1 = obtain.obtainNode("DatosRecogida",metadatos);	

					Document doc2 = docBuilder.parse(file.getPath());
					Node cuerpoSoap2 = doc2.getFirstChild();
					Node contenidoMensajePro = cuerpoSoap2.getChildNodes().item(1)
							.getChildNodes().item(1)
							.getChildNodes().item(1);
					Node metadatos2 = contenidoMensajePro.getChildNodes().item(1);
 					Node datosRecogida2 = obtain.obtainNode("DatosRecogida",metadatos2);
					
					Document doc3 = docBuilder.parse(file.getPath());
					Node cuerpoSoap3 = doc3.getFirstChild();
					Node contenidoMensaje3 = cuerpoSoap3.getChildNodes().item(1)
							.getChildNodes().item(1)
							.getChildNodes().item(1);
					Node metadatos3 = contenidoMensaje3.getChildNodes().item(1);
 					Node datosRecogida3 = obtain.obtainNode("DatosRecogida",metadatos3);

					NodeList nodos1Padre =datosRecogida1.getChildNodes();
					NodeList nodos2Padre =datosRecogida2.getChildNodes();
					NodeList nodos3Padre =datosRecogida3.getChildNodes();


					//Cambiamos el nie de titular en el campo AEAT_TITULAR_PROCEDIMIENTO_NIF
					// y ponemos los datos de la tabla
					for(int k =0; k< nodos1Padre.getLength(); k++){
						NodeList nodos1  = datosRecogida1.getChildNodes().item(k).getChildNodes();
						NodeList nodos2  = datosRecogida2.getChildNodes().item(k).getChildNodes();
						NodeList nodos3  = datosRecogida3.getChildNodes().item(k).getChildNodes();

						for (int j =0; j< nodos1.getLength();  j++){
							Node nodo = nodos2.item(j);
							if (nodo.getTextContent().contains("AEAT_TITULAR_PROCEDIMIENTO_NIF")){
								String casos = nodos1.item(j+1).getTextContent();
								String caso1 = casos.split(":")[1].split("\\*")[0].trim();
								String caso2 = casos.split(":")[2].split("\\*")[0].trim();
								String caso3 = casos.split(":")[3].trim();

								nodos1.item(j+1).setTextContent(caso1);
								nodos2.item(j+1).setTextContent(caso2);
								nodos3.item(j+1).setTextContent(caso3);
							}
							//#TODO
							if (nodo.getTextContent().contains("AEAT_REFERENCIA")){
								String casos = nodos1.item(j+1).getTextContent();
								if (casos.contains("CASO")){
									String caso1 = casos.split(":")[1].split("\\*")[0].trim();
									String caso2 = casos.split(":")[2].split("\\*")[0].trim();
									String caso3 = casos.split(":")[3].trim();

									nodos1.item(j+1).setTextContent(caso1);
									nodos2.item(j+1).setTextContent(caso2);
									nodos3.item(j+1).setTextContent(caso3);		
								}
															
							}



						}
					}

					// Guardamos el archivo
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source1 = new DOMSource(doc1);
					String path1 = file.getPath().split(".xml")[0] + "_1.xml";
					StreamResult result = new StreamResult(new File(path1));
					transformer.transform(source1, result);

					DOMSource source2 = new DOMSource(doc2);
					String path2 = file.getPath().split(".xml")[0] + "_2.xml";
					StreamResult result2 = new StreamResult(new File(path2));
					transformer.transform(source2, result2);

					DOMSource source3 = new DOMSource(doc3);
					String path3 = file.getPath().split(".xml")[0] + "_3.xml";
					StreamResult result3 = new StreamResult(new File(path3));
					transformer.transform(source3, result3);

				} catch (Exception e) {
					log.error("error modificando "  + file.getName());// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cont++;
			}
		}

	}

}
