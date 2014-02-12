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
import ee.finestmedia.currencyconverter.service.XMLProcessingService;

/**
 * @author Anton Dubov
 */
@Repository("xmlParser")
public class XmlParser implements Parser {

  @Autowired
  XMLProcessingService xmlProcessingService;

  public Object getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date, Class<?> responseType) throws JAXBException, IOException {
    String url = getUrlWithAppliedDate(currencyDataSource, date);
    return xmlProcessingService.unmarshalXMLFromURL(url, responseType);
  }

  private String getUrlWithAppliedDate(CurrencyDataSource currencyDataSource, Date date) {
    String baseUrl = currencyDataSource.getUrl();
    DateFormat dateFormat = new SimpleDateFormat(currencyDataSource.getDateFormat());
    return baseUrl + dateFormat.format(date);
  }

}
