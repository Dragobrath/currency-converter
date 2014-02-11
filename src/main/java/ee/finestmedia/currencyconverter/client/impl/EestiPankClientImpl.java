package ee.finestmedia.currencyconverter.client.impl;

import java.util.Date;

import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.generated.Report;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.util.ParserFactory;

public class EestiPankClientImpl implements CurrencyDataFeedClient {

  private static final Class<Report> CLASS_TO_UNMARSHAL = Report.class;

  private ParserFactory parserFactory;

  public void setParserFactory(ParserFactory parserFactory) {
    this.parserFactory = parserFactory;
  }

  @Override
  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date) {
    return null;
  }

}
