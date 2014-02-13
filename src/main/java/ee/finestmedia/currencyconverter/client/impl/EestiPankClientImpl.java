package ee.finestmedia.currencyconverter.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import ee.finestmedia.currencyconverter.client.parser.ParserFactory;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;
import info.eestipank.producers.types.Report;
import info.eestipank.producers.types.Report.Body.Currencies.Currency;

@Repository("eestipank")
public class EestiPankClientImpl extends AbstractBaseClientImpl {

  private static final Class<?> RESPONSE_TYPE = Report.class;
  private static final String DATA_TYPE = "xml";
  private static final String RESPONSE_DATE_FORMAT = "DD.MM.YY";

  @Autowired
  private ParserFactory parserFactory;

  @Override
  protected DataFeed mapParserResponseToDataFeedModel(Object parserResponse) throws MappingException, ParseException {
    if (!RESPONSE_TYPE.isAssignableFrom(parserResponse.getClass())) {
      throw new MappingException(RESPONSE_DOES_NOT_MATCH);
    }

    Report report = (Report) parserResponse;
    DataFeed dataFeed = new DataFeed();
    Set<DataFeed.Entry> entries = dataFeed.getEntries();

    String fixingsDateAsString = report.getBody().getFixingsDate();
    Date fixingsDate = new SimpleDateFormat(RESPONSE_DATE_FORMAT).parse(fixingsDateAsString);

    for (Currency currency : report.getBody().getCurrencies().getCurrency()) {
      BigDecimal rateAsBigDecimal = parseRateAsBigDecimal(currency.getRate());
      DataFeed.Entry entry = new DataFeed.Entry(currency.getName(), fixingsDate, rateAsBigDecimal);
      entry.setDisplayName(currency.getText());
      entries.add(entry);
    }

    return dataFeed;
  }
  
  private BigDecimal parseRateAsBigDecimal(String rate) {
    return new BigDecimal(rate.replace(",", ".").replace(" ", ""));
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
