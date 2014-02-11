package ee.finestmedia.currencyconverter.client;


public interface ClientFactory {
  
  public CurrencyDataFeedClient getClient(String id);

}
