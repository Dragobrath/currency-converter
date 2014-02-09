package ee.finestmedia.currencyconverter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ee.finestmedia.currencyconverter.service.XMLProcessingService;

@Service
public class XMLProcessingServiceImpl implements XMLProcessingService {
  
  private static final Logger LOG = LoggerFactory.getLogger(XMLProcessingServiceImpl.class);

  @Override
  public Object unmarshalXMLFromURL(String uri, Class<?> classToUnmarshall) throws JAXBException, IOException {
    HttpURLConnection connection = getHttpConnection(uri);
    InputStream xml = connection.getInputStream();

    JAXBContext jc = JAXBContext.newInstance(classToUnmarshall);
    Object unmarshalledObject = jc.createUnmarshaller().unmarshal(xml);

    connection.disconnect();
    return unmarshalledObject;
  }

  @Override
  public Object unmarshalXMLFromFile(String fileName, Class<?> classToUnmarshall) throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance(classToUnmarshall);
    return jc.createUnmarshaller().unmarshal(new File(fileName));
  }

  private HttpURLConnection getHttpConnection(String uri) throws IOException {
    HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Accept", "application/xml");
    return connection;
  }

}
