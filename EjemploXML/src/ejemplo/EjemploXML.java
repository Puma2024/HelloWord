package ejemplo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class EjemploXML {

	public static void main(String[] args) throws Exception{
		Document docXML1 = leerXML("libros.xml");
		imprimirXML(docXML1);
		guardarXML(docXML1, "salidalibros.xml");
		
		Document docXML2 = crearXML();
		imprimirXML(docXML2);
		guardarXML(docXML2, "salida.xml");
	}
	
	static Document crearXML() throws ParserConfigurationException {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder() ;
		Document document = documentBuilder.newDocument();
		Element elementLibro = document.createElement("libro");
		Element elementTitulo = document.createElement ("titulo");
		Attr attributeDisponible = document.createAttribute("disponiblePapel");
		attributeDisponible.setValue("true");
		Text textTitulo = document.createTextNode("LENGUAJES DE MARCAS Y ...");
		elementTitulo.appendChild(textTitulo);
		elementLibro.appendChild(elementTitulo);
		elementLibro.setAttribute ("disponibleDigital", "true");
		elementLibro.setAttributeNode (attributeDisponible);
		Element elementAutor=document.createElement("autor");
		Text elementText=document.createTextNode("Lois Lane");
		elementText.appendChild(elementText);
		elementLibro.appendChild(elementAutor);
		document.appendChild(elementLibro);
		Element elementFechas=document.createElement("fecha");
		Text elementFecha=document.createTextNode("09/06/04");
		elementLibro.appendChild(elementFechas);
		elementLibro.appendChild(elementFecha);
		return document;
	}
	
	static Document leerXML(String fichero) throws ParserConfigurationException, IOException, SAXException{
		File fXmlFile = new File(fichero);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(fXmlFile);
		return document;
	}
	
	static void guardarXML (Document document, String fichero) throws IOException, TransformerException {
		DOMSource domSource = new DOMSource (document) ;
		FileWriter fileWriter = new FileWriter(new File(fichero));
		StreamResult streamResult = new StreamResult (fileWriter);
		TransformerFactory transformerFactory = TransformerFactory.newInstance() ;
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty (OutputKeys.INDENT, "yes");
		transformer.transform(domSource, streamResult);
		fileWriter.close();
	}

	
	static void imprimirXML (Document document) throws TransformerException {
		System.out.println("-------------------------");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StringWriter stringWriter = new StringWriter();
		DOMSource domSource = new DOMSource (document);
		transformer.setOutputProperty (OutputKeys.INDENT, "yes");
		transformer.transform(domSource, new StreamResult (stringWriter));
		String stringXML = stringWriter.toString();	
		System.out.println(stringXML);
		System.out.println("-------------------------");
	}
}

