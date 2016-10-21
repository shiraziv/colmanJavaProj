package properties;

import java.beans.XMLDecoder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class PropertiesLoder {
	
private static PropertiesLoder instance;
	
	private Properties properties;
	
	public Properties getProperties() {
		return properties;
	}
	
	/***
	 * Private constructor to allow creation of one instance only.
	 */
	private PropertiesLoder() 
	{
		properties = new Properties();
		try {
			File file = new File("lib/properties.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(properties.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(properties, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * returns the created instance of type PropertiesManager
	 * @return properties
	 */
	public static PropertiesLoder getInstance() {
		if (instance == null) 
			instance = new PropertiesLoder();
		return instance;
	}
	
	public Properties readXML(String path) {
		getInstance();
		try {
			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(Properties.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			properties = (Properties)unmarshaller.unmarshal(file);
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		}
		return properties;
	}


	public void writeXml(String viewType) {
		getInstance();
		properties.setViewType(viewType);
		try {
			File file = new File("lib/properties.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(properties.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(properties, file);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	
}