package ee.finestmedia.currencyconverter.client;

import org.springframework.stereotype.Service;

import java.util.Date;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.util.ParserFactory;

@Service
public interface CurrencyDataFeedClient {

  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date);
  
  public void setParserFactory(ParserFactory parserFactory);

}
