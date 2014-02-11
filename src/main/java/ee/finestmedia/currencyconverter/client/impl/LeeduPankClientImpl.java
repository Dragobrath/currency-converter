package ee.finestmedia.currencyconverter.client.impl;

import java.util.Date;

import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.generated.ExchangeRates;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.util.ParserFactory;

public class LeeduPankClientImpl implements CurrencyDataFeedClient {

  private static final Class<ExchangeRates> CLASS_TO_UNMARSHAL = ExchangeRates.class;

  private ParserFactory parserFactory;

  public void setParserFactory(ParserFactory parserFactory) {
    this.parserFactory = parserFactory;
  }

  @Override
  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date) {
    return null;
  }

}
