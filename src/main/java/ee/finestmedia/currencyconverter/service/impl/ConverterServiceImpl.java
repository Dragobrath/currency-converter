/**
 * 
 */
package ee.finestmedia.currencyconverter.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.model.UnifiedCurrencyDataFeed;
import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.ConverterService;

/**
 * @author Anton Dubov
 */
@Service
public class ConverterServiceImpl implements ConverterService {

  @Autowired
  private ConfigurationService configurationService;
  
  @Autowired
  private CurrencyDataFeedClient currencyDataFeedClient;

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
    for (CurrencyDataSources.CurrencyDataSource currencyDataSource : currencyDataSources.getCurrencyDataSource()) {
      unifiedCurrencyDataFeed.addCurrencyDataFeed(getCurrencyFeedFromDataSource(currencyDataSource, date));
    }
    return unifiedCurrencyDataFeed;
  }

  private CurrencyDataFeed getCurrencyFeedFromDataSource(CurrencyDataSources.CurrencyDataSource currencyDataSource, Date date) {
	  currencyDataFeedClient.getCurrencyDataFeed(currencyDataSource, date);
    return null;
  }

}
