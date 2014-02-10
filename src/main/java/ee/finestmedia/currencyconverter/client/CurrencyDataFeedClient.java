package ee.finestmedia.currencyconverter.client;

import java.util.Date;

import org.springframework.stereotype.Service;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;

@Service
public class CurrencyDataFeedClient {
  
  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSources.CurrencyDataSource currencyDataSource, Date date) {
	  String dataType = currencyDataSource.getDataType();
	  return null;
  }

}
