package ee.finestmedia.currencyconverter.service;

import javax.xml.bind.JAXBException;

import java.io.IOException;

public interface XMLProcessingService {

  Object unmarshalXMLFromURL(String uri, Class<?> classToUnmarshall) throws JAXBException, IOException;

  Object unmarshalXMLFromFile(String fileName, Class<?> classToUnmarshall) throws JAXBException, IOException;

}
