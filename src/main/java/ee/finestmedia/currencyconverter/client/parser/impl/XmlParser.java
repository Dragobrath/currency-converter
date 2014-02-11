package ee.finestmedia.currencyconverter.client.parser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ee.finestmedia.currencyconverter.client.parser.Parser;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.service.XMLProcessingService;

/**
 * @author Anton Dubov
 */
@Repository
public class XmlParser implements Parser {

  @Autowired
  XMLProcessingService xmlProcessingService;

  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date, Class<?> classToUnmarshal) throws JAXBException, IOException {
    String url = getUrlWithAppliedDate(currencyDataSource, date);
    xmlProcessingService.unmarshalXMLFromURL(url, classToUnmarshal);
    return null;
  }

  private String getUrlWithAppliedDate(CurrencyDataSource currencyDataSource, Date date) {
    String baseUrl = currencyDataSource.getUrl();
    DateFormat dateFormat = new SimpleDateFormat(currencyDataSource.getDateFormat());
    return baseUrl + dateFormat.format(date);
  }

}
