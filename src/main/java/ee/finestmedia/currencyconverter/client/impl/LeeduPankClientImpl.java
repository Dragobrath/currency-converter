package ee.finestmedia.currencyconverter.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Set;

import ee.finestmedia.currencyconverter.util.EURNotFoundException;
import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;
import ee.finestmedia.currencyconverter.generated.leedupank.ExchangeRates;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.util.CurrencyUtil;
import ee.finestmedia.currencyconverter.util.MappingException;

public class LeeduPankClientImpl extends CurrencyDataFeedClient {

  private static final Logger LOG = LoggerFactory.getLogger(LeeduPankClientImpl.class);

  private static final Class<ExchangeRates> RESPONSE_TYPE = ExchangeRates.class;
  private static final String RESPONSE_DATE_FORMAT = "YYYY.MM.DD";

  @Override
  protected Class<?> getResponseType() {
    return RESPONSE_TYPE;
  }

  @Override
  protected CurrencyDataFeed mapParserResponseToCurrencyDataFeedModel(Object parserResponse) throws MappingException, ParseException {
    if (RESPONSE_TYPE.isAssignableFrom(parserResponse.getClass())) {
      throw new MappingException(RESPONSE_DOES_NOT_MATCH);
    }

    ExchangeRates exchangeRates = (ExchangeRates) parserResponse;
    CurrencyDataFeed currencyDataFeed = new CurrencyDataFeed();
    Set<CurrencyDataFeed.Entry> entries = currencyDataFeed.getEntries();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(RESPONSE_DATE_FORMAT);

    try {
      BigDecimal rateOfEURToLTL = getRateOfEURToLTL(exchangeRates);

      for (ExchangeRates.Item item : exchangeRates.getItem()) {
        Date date = simpleDateFormat.parse(item.getDate());
        BigDecimal rateOfEURToCurrency = getRateOfEURToCurrency(item, rateOfEURToLTL);
        CurrencyDataFeed.Entry entry = currencyDataFeed.new Entry(item.getCurrency(), date, rateOfEURToCurrency);
        entry.setDisplayName(Currency.getInstance(item.getCurrency()).getDisplayName());
        entries.add(entry);
      }
    } catch (EURNotFoundException e) {
      LOG.error(e.getMessage(), e);
    }
    return currencyDataFeed;
  }

  private BigDecimal getRateOfEURToLTL(ExchangeRates exchangeRates) throws EURNotFoundException {
    for (ExchangeRates.Item item : exchangeRates.getItem()) {
      if ("EUR".equals(item.getCurrency())) {
        return CurrencyUtil.divide(item.getRate(), item.getQuantity());
      }
    }
    throw new EURNotFoundException("EUR is missing from the feed!");
  }

  private BigDecimal getRateOfEURToCurrency(ExchangeRates.Item item, BigDecimal rateOfEURToLTL) {
    BigDecimal rateOfCurrencyToLTL = CurrencyUtil.divide(item.getRate(), item.getQuantity());
    BigDecimal rateOfEURToCurrency = rateOfEURToLTL.divide(rateOfCurrencyToLTL, CurrencyUtil.PRECISION_SCALE, BigDecimal.ROUND_HALF_UP);
    return rateOfEURToCurrency;
  }

}