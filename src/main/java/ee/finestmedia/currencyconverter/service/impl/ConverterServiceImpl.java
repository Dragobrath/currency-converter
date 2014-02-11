/**
 * 
 */
package ee.finestmedia.currencyconverter.service.impl;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.util.exception.MappingException;

import ee.finestmedia.currencyconverter.client.ClientFactory;
import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.model.UnifiedCurrencyDataFeed;
import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.ConverterService;

/**
 * @author Anton Dubov
 */
public class ConverterServiceImpl implements ConverterService {
  
  private static final Logger LOG = LoggerFactory.getLogger(ConverterServiceImpl.class);

  @Autowired
  private ConfigurationService configurationService;
  
  private ClientFactory clientFactory;
  
  public void setClientFactory(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  @Override
  public UnifiedCurrencyDataFeed getCurrenciesList() {
    DateTime yesterday = new DateTime().minusDays(1);
    UnifiedCurrencyDataFeed dataFeeds = new UnifiedCurrencyDataFeed();
    try {
      dataFeeds = getUnifiedCurrencyFeed(yesterday.toDate());
    } catch (JAXBException | IOException | MappingException | ParseException e) {
      LOG.error(e.getMessage(), e);
    }
    return dataFeeds;
  }

  @Override
  public UnifiedCurrencyDataFeed convertCurrency(String originCurrencyCode, String destinationCurrencyCode, double amount, Date date) {
    UnifiedCurrencyDataFeed dataFeeds = new UnifiedCurrencyDataFeed();
    try {
      dataFeeds = getUnifiedCurrencyFeed(date);
    } catch (JAXBException | IOException | MappingException | ParseException e) {
      LOG.error(e.getMessage(), e);
    }
    return dataFeeds;
  }

  private UnifiedCurrencyDataFeed getUnifiedCurrencyFeed(Date date) throws JAXBException, IOException, MappingException, ParseException {
    CurrencyDataSources currencyDataSources = configurationService.getCurrencyDataSources();
    UnifiedCurrencyDataFeed unifiedCurrencyDataFeed = new UnifiedCurrencyDataFeed();
    for (CurrencyDataSource currencyDataSource : currencyDataSources.getCurrencyDataSource()) {
      if (currencyDataSource.isEnabled()) {
        unifiedCurrencyDataFeed.addCurrencyDataFeed(getCurrencyFeedFromDataSource(currencyDataSource, date));
      }
    }
    return unifiedCurrencyDataFeed;
  }

  private CurrencyDataFeed getCurrencyFeedFromDataSource(CurrencyDataSource currencyDataSource, Date date) throws JAXBException, IOException, MappingException, ParseException {
    CurrencyDataFeedClient dataFeedClient = clientFactory.getClient(currencyDataSource.getId());
    return dataFeedClient.getCurrencyDataFeed(currencyDataSource, date);
  }

}
