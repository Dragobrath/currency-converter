package ee.finestmedia.currencyconverter.client.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import ee.finestmedia.currencyconverter.util.exception.MappingException;

import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;
import ee.finestmedia.currencyconverter.client.parser.ParserFactory;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import info.eestipank.producers.types.Report;
import info.eestipank.producers.types.Report.Body.Currencies.Currency;

public class EestiPankClientImpl extends CurrencyDataFeedClient {

  private static final Class<?> RESPONSE_TYPE = Report.class;
  private static final String DATA_TYPE = "xml";
  private static final String RESPONSE_DATE_FORMAT = "DD.MM.YY";

  private ParserFactory parserFactory;

  public void setParserFactory(ParserFactory parserFactory) {
    this.parserFactory = parserFactory;
  }

  @Override
  protected CurrencyDataFeed mapParserResponseToCurrencyDataFeedModel(Object parserResponse) throws MappingException, ParseException {
    if (!RESPONSE_TYPE.isAssignableFrom(parserResponse.getClass())) {
      throw new MappingException(RESPONSE_DOES_NOT_MATCH);
    }

    Report report = (Report) parserResponse;
    CurrencyDataFeed currencyDataFeed = new CurrencyDataFeed();
    Set<CurrencyDataFeed.Entry> entries = currencyDataFeed.getEntries();

    String fixingsDateAsString = report.getBody().getFixingsDate();
    Date fixingsDate = new SimpleDateFormat(RESPONSE_DATE_FORMAT).parse(fixingsDateAsString);

    for (Currency currency : report.getBody().getCurrencies().getCurrency()) {
      BigDecimal rateAsBigDecimal = BigDecimal.valueOf(currency.getRate());
      CurrencyDataFeed.Entry entry = currencyDataFeed.new Entry(currency.getName(), fixingsDate, rateAsBigDecimal);
      entry.setDisplayName(currency.getText());
      entries.add(entry);
    }

    return currencyDataFeed;
  }

  @Override
  protected ParserFactory getParserFactory() {
    return parserFactory;
  }

  @Override
  protected Class<?> getResponseType() {
    return RESPONSE_TYPE;
  }

  @Override
  protected String getDataType() {
    return DATA_TYPE;
  }

}
