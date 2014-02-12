package ee.finestmedia.currencyconverter.client.impl;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;

import ee.finestmedia.currencyconverter.client.parser.Parser;
import ee.finestmedia.currencyconverter.client.parser.ParserFactory;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

public abstract class AbstractBaseClientImpl implements CurrencyDataFeedClient {

  public static final String RESPONSE_DOES_NOT_MATCH = "Parser response does not match expected type";

  private static final String PARSER_POSTFIX = "Parser";

  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date) throws JAXBException,
                                                                                               IOException,
                                                                                               MappingException,
                                                                                               ParseException {
    Parser parser = getParserFactory().getParser(getDataType() + PARSER_POSTFIX);
    Object parserResponse = parser.getCurrencyDataFeed(currencyDataSource, date, getResponseType());
    CurrencyDataFeed currencyDataFeed = mapParserResponseToCurrencyDataFeedModel(parserResponse);
    currencyDataFeed.setDataSourceDisplayName(currencyDataSource.getDisplayName());
    return currencyDataFeed;
  }

  protected abstract CurrencyDataFeed mapParserResponseToCurrencyDataFeedModel(Object parserResponse) throws MappingException, ParseException;

  protected abstract Class<?> getResponseType();

  protected abstract ParserFactory getParserFactory();

  protected abstract String getDataType();

}