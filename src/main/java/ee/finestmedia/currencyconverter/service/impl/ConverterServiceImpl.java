/**
 * 
 */
package ee.finestmedia.currencyconverter.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.model.UnifiedCurrencyDataFeed;
import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.ConverterService;
import ee.finestmedia.currencyconverter.util.ClientFactory;

/**
 * @author Anton Dubov
 */
@Service
public class ConverterServiceImpl implements ConverterService {

  @Autowired
  private ConfigurationService configurationService;
  
  private ClientFactory clientFactory;
  
  public void setClientFactory(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  @Override
  public String getCurrenciesList() {
    DateTime yesterday = new DateTime().minusDays(1);
    return getCurrenciesListByDate(yesterday.toDate());
  }

  @Override
  public String getCurrenciesListByDate(Date date) {
    CurrencyDataSources currencyDataSources = configurationService.getCurrencyDataSources();

    return null;
  }

  @Override
  public String convertCurrency(String originCurrencyCode, String destinationCurrencyCode, BigDecimal amount, Date date) {
    UnifiedCurrencyDataFeed dataFeeds = getUnifiedCurrencyFeed(date);
    return null;
  }

  private UnifiedCurrencyDataFeed getUnifiedCurrencyFeed(Date date) {
    CurrencyDataSources currencyDataSources = configurationService.getCurrencyDataSources();
    UnifiedCurrencyDataFeed unifiedCurrencyDataFeed = new UnifiedCurrencyDataFeed();
    for (CurrencyDataSource currencyDataSource : currencyDataSources.getCurrencyDataSource()) {
      unifiedCurrencyDataFeed.addCurrencyDataFeed(getCurrencyFeedFromDataSource(currencyDataSource, date));
    }
    return unifiedCurrencyDataFeed;
  }

  private CurrencyDataFeed getCurrencyFeedFromDataSource(CurrencyDataSource currencyDataSource, Date date) {
    clientFactory.getClient(currencyDataSource.getName()).getCurrencyDataFeed(currencyDataSource, date);
    return null;
  }

}
