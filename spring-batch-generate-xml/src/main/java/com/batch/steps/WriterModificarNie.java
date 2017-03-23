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
public class WriterModificarNie implements ItemWriter<ArrayList<File>> {

	private static final Log log = LogFactory.getLog(WriterModificarNie.class);

	/**
	 * @see ItemWriter#write(java.util.List)
	 */
	public void write(List<? extends ArrayList<File>> data) throws Exception {
		Iterator ite = data.iterator();
		String inputCsvPath = "resources/input4_mod.csv";
		ArrayList<String> linesCsv = (ArrayList<String>) Files.readAllLines(Paths.get(inputCsvPath));		
		Utils obtain = new Utils();


		while (ite.hasNext()) {
			ArrayList<File> files = (ArrayList<File>) ite.next();
			log.info("-------------------------------------------");
			log.info("modificando los nies");
			log.info("-------------------------------------------");
			linesCsv.remove(0);

			int cont=0;
			for (File file: files){
				try {
					// Sacamos la columna de la tabla que necesitamos

					String txtIntegracion = "";
					String txtPreproduccion = "";
					String mensajeIdStr ="";
					for (String line : linesCsv){
						String[] camposCsv = line.split(";");
						if (file.getName().contains(camposCsv[0])){
							mensajeIdStr = camposCsv[0];
							txtIntegracion = camposCsv[15];
							txtPreproduccion = camposCsv[16];
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
					Node eventoRecogida = obtain.obtainNode("EventoRecogida",metadatos);		 
					Node datosRecogida = obtain.obtainNode("DatosRecogida",metadatos);	

					Document docPro = docBuilder.parse(file.getPath());
					Node cuerpoSoapPro = docPro.getFirstChild();
					Node contenidoMensajePro = cuerpoSoapPro.getChildNodes().item(1)
							.getChildNodes().item(1)
							.getChildNodes().item(1);
					Node metadatosPro = contenidoMensajePro.getChildNodes().item(1);
					Node eventoRecogidaPro = obtain.obtainNode("EventoRecogida",metadatosPro);		 
					Node datosRecogidaPro = obtain.obtainNode("DatosRecogida",metadatosPro);

					NodeList nodosIntPadre =datosRecogida.getChildNodes();
					NodeList nodosProPadre =datosRecogidaPro.getChildNodes();

					for(int k =0; k< nodosIntPadre.getLength(); k++){
						NodeList nodosInt  = datosRecogida.getChildNodes().item(k).getChildNodes();
						NodeList nodosPro  = datosRecogidaPro.getChildNodes().item(k).getChildNodes();

						for (int j =0; j< nodosInt.getLength();  j++){
							Node nodo = nodosInt.item(j);
							log.info("********************************************");
							log.info(nodo.getNodeName());
							log.info(nodo.getTextContent());
							if (nodo.getTextContent().contains("AEAT_TITULAR_PROCEDIMIENTO_NIF")){
								nodosInt.item(j+1).setTextContent(txtIntegracion);
								nodosPro.item(j+1).setTextContent(txtPreproduccion);
								log.info("***************/*/*/*/*/*/*/******************");
							}
						}



					}
					log.info("/--/-/-/-/--/-/-/-/-/-/-/-");

					// Guardamos el archivo
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource sourceInt = new DOMSource(docInt);

					StreamResult result = new StreamResult(new File("resources/outputs/output" +"_" + mensajeIdStr + "_int" + ".xml"));
					transformer.transform(sourceInt, result);


					DOMSource sourcePro = new DOMSource(docPro);
					StreamResult resultPro = new StreamResult(new File("resources/outputs/output" +"_" + mensajeIdStr + "_pr" + ".xml"));
					transformer.transform(sourcePro, resultPro);
					log.info("Done");

					log.info(datosRecogida.getNodeName());


				} catch (Exception e) {
					log.error("error modificando "  + file.getName());// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cont++;
			}
		}

	}

}
