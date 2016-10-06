package com.mx;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class Principal {
	
	public static void main(String[] args) {
		Path dir = Paths.get("C:/Users/dzuniga/Desktop/CFDI_3D124BAD-2E16-4BC6-9CAC-359A396EDB14_28_01_2016");
		
		try {	
			DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
			for (Path file: stream){
				resumen(file);
				//detallado(file);
			}
		}
			catch (Exception ex) {
				System.err.println(ex);
		}
	}
	
	public static void resumen(Path file) throws FileNotFoundException, XMLStreamException{
		StringBuilder sbuilder = new StringBuilder("");
		verResumenDocumentoXML(file, sbuilder);
		System.out.println(sbuilder);
	}
	
	public static void detallado(Path file) throws FileNotFoundException, XMLStreamException{
		System.out.println("Nombre del archivo: "+file.getFileName());
		System.out.println("-------------------------------------------------------------------------");
		verDocumentoXML(file);
		System.out.println("-------------------------------------------------------------------------");
	}
	
	public static void verDocumentoXML(Path file) throws FileNotFoundException, XMLStreamException{
		XMLInputFactory factory = XMLInputFactory.newFactory();
		InputStream fileInputStream = new FileInputStream(file.toFile());
		XMLStreamReader xmlReader = factory.createXMLStreamReader(fileInputStream);
		
		while(xmlReader.hasNext()) {
			int eventType = xmlReader.next();
			
			switch(eventType) {
                case XMLEvent.START_ELEMENT:
                	String localName = xmlReader.getLocalName().toUpperCase();
                	if(localName.equals("COMPROBANTE") || localName.equals("EMISOR") || localName.equals("IMPUESTOS")
                		|| localName.equals("TRASLADO") || localName.equals("TIMBREFISCALDIGITAL")){
                		System.out.println("SECCION: "+xmlReader.getLocalName().toUpperCase());
                    	verAtributos(xmlReader);
                	}
                break;
            }
		}
	}
	
	public static void verAtributos(XMLStreamReader xmlReader){
		int attributes = xmlReader.getAttributeCount();
		StringBuilder sb = new StringBuilder("");
        for(int i=0;i<attributes;++i) {
        	String atributo = xmlReader.getAttributeLocalName(i).toUpperCase();
        	String valor = xmlReader.getAttributeValue(i);
        	switch (atributo) {
				case "FOLIO" : 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "FECHA": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "NOCERTIFICADO": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "SUBTOTAL": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "DESCUENTO": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "TOTAL": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "RFC": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "NOMBRE": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "TOTALIMPUESTOSTRASLADADOS": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "TASA": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "IMPUESTO": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "IMPORTE": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
				case "NOCERTIFICADOSAT": 
					sb.append(atributo).append(": ").append(valor).append("\n");
					break;
			}            
        }
        System.out.println(sb);
	}
	
	public static void verResumenDocumentoXML(Path file, StringBuilder sbuilder) throws FileNotFoundException, XMLStreamException{
		XMLInputFactory factory = XMLInputFactory.newFactory();
		InputStream fileInputStream = new FileInputStream(file.toFile());
		XMLStreamReader xmlReader = factory.createXMLStreamReader(fileInputStream);
		
		while(xmlReader.hasNext()) {
			int eventType = xmlReader.next();
			
			switch(eventType) {
                case XMLEvent.START_ELEMENT:
                	String localName = xmlReader.getLocalName().toUpperCase();
                	if(localName.equals("COMPROBANTE") || localName.equals("EMISOR") || localName.equals("IMPUESTOS")
                		|| localName.equals("TRASLADO")){
                    	verTotalesIva(xmlReader, sbuilder);
                	}
                break;
            }
		}
	}
	
	public static void verTotalesIva(XMLStreamReader xmlReader, StringBuilder sb){
		int attributes = xmlReader.getAttributeCount();
        for(int i=0;i<attributes;++i) {
        	String atributo = xmlReader.getAttributeLocalName(i).toUpperCase();
        	String valor = xmlReader.getAttributeValue(i);
        	switch (atributo) {
				/*case "SUBTOTAL": 
					sb.append(atributo).append("\t").append(valor).append("\t");
					break;
				case "DESCUENTO": 
					sb.append(atributo).append("\t").append(valor).append("\t");
					break;
				case "TOTAL": 
					sb.append(atributo).append("\t").append(valor).append("\t");
					break;*/
				case "TOTALIMPUESTOSTRASLADADOS": 
					sb.append(atributo).append("\t").append(valor).append("\t");
					break;
				case "TASA": 
					sb.append(atributo).append("\t").append(valor).append("\t");
					break;
				case "IMPUESTO": 
					sb.append(atributo).append("\t").append(valor).append("\t");
					break;
				case "IMPORTE": 
					sb.append(atributo).append("\t").append(valor).append("\t");
					break;
			}            
        }
	}
}
