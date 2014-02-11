package ee.finestmedia.currencyconverter.client;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.util.MappingException;
import ee.finestmedia.currencyconverter.client.parser.Parser;
import ee.finestmedia.currencyconverter.client.parser.ParserFactory;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;

@Service
public abstract class CurrencyDataFeedClient {

  public static final String RESPONSE_DOES_NOT_MATCH = "Parser response does not match expected type";
  private static final String PARSER_POSTFIX = "Parser";


  protected ParserFactory parserFactory;

  public void setParserFactory(ParserFactory parserFactory) {
    this.parserFactory = parserFactory;
  }

  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date) throws JAXBException, IOException, MappingException, ParseException {
    Parser parser = parserFactory.getParser(currencyDataSource.getDataType() + PARSER_POSTFIX);
    Object parserResponse = parser.getCurrencyDataFeed(currencyDataSource, date, getResponseType());
    CurrencyDataFeed currencyDataFeed = mapParserResponseToCurrencyDataFeedModel(parserResponse);
    return currencyDataFeed;
  }

  protected abstract CurrencyDataFeed mapParserResponseToCurrencyDataFeedModel(Object parserResponse) throws MappingException, ParseException;
  
  protected abstract Class<?> getResponseType();

}
