/**
 * 
 */
package ee.finestmedia.currencyconverter.service.impl;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.client.ClientFactory;
import ee.finestmedia.currencyconverter.client.CurrencyDataFeedClient;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.model.UnifiedCurrencyDataFeed;
import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.ConverterService;
import ee.finestmedia.currencyconverter.util.CustomKeyGeneratorUtil;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

/**
 * @author Anton Dubov
 */
@Service
public class ConverterServiceImpl implements ConverterService {

  private static final Logger LOG = LoggerFactory.getLogger(ConverterServiceImpl.class);

  @Autowired
  private ConfigurationService configurationService;

  @Autowired
  private ClientFactory clientFactory;

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
      // TODO: Implement calculation
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
        CurrencyDataFeed currencyDataFeed = ((ConverterService) this).getCurrencyFeedFromDataSourceClient(currencyDataSource, date);
        unifiedCurrencyDataFeed.addCurrencyDataFeed(currencyDataFeed);
      }
    }
    return unifiedCurrencyDataFeed;
  }

  @Cacheable(value = "feedByDateCache", key = CustomKeyGeneratorUtil.SPEL_EXPRESSION_GENERATE_KEY_FOR_FEED_BY_DATE_CACHE)
  @Override
  public CurrencyDataFeed getCurrencyFeedFromDataSourceClient(CurrencyDataSource currencyDataSource, Date date) throws JAXBException,
                                                                                                               IOException,
                                                                                                               MappingException,
                                                                                                               ParseException {
    LOG.info("feedByDateCache for key {} was not found. Creating a new one.", CustomKeyGeneratorUtil.getKey(currencyDataSource.getId(), date));
    CurrencyDataFeedClient dataFeedClient = clientFactory.getClient(currencyDataSource.getId());
    CurrencyDataFeed currencyDataFeed = dataFeedClient.getCurrencyDataFeed(currencyDataSource, date);
    return currencyDataFeed;
  }
}
