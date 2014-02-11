package ee.finestmedia.currencyconverter.util;

import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;

public interface ClientFactory {
  
  public CurrencyDataFeedClient getClient(String id);

}
