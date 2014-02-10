/**
 * 
 */
package ee.finestmedia.currencyconverter.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import ee.finestmedia.currencyconverter.dao.CurrencyDataFeedDao;
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
      unifiedCurrencyDataFeed.addCurrencyDataFeed(getDataSourceFeed(currencyDataSource, date));
    }
    return unifiedCurrencyDataFeed;
  }

  private CurrencyDataFeed getDataSourceFeed(CurrencyDataSources.CurrencyDataSource currencyDataSource, Date date) {
    getDao(currencyDataSource.getDaoName()).getCurrencyDataFeed(currencyDataSource, date);
    return null;
  }

  private CurrencyDataFeedDao getDao(String daoName) {
    try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/WEB-INF/classes/applicationContext.xml")) {
      CurrencyDataFeedDao dao = (CurrencyDataFeedDao) applicationContext.getBean(daoName);
      return dao;
    }
  }

}
