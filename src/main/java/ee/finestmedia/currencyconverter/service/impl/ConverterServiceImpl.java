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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import ee.finestmedia.currencyconverter.generated.DataFeedSources;
import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.model.UIRequest;
import ee.finestmedia.currencyconverter.model.UIResponse;
import ee.finestmedia.currencyconverter.model.UIResponse.BankAndAmount;
import ee.finestmedia.currencyconverter.model.UnifiedDataFeed;
import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.ConverterService;
import ee.finestmedia.currencyconverter.service.DataFeedService;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

@Service
public class ConverterServiceImpl implements ConverterService {

  private static final Logger LOG = LoggerFactory.getLogger(ConverterServiceImpl.class);

  @Autowired
  private ConfigurationService configurationService;

  @Autowired
  private DataFeedService dataFeedService;

  @Override
  public UnifiedDataFeed getUnifiedDataFeedForThePreviousDay() {
    DateTime yesterday = new DateTime().minusDays(1);
    UnifiedDataFeed dataFeeds = new UnifiedDataFeed();
    try {
      dataFeeds = getUnifiedDataFeed(yesterday.toDate());
    } catch (JAXBException | IOException | MappingException | ParseException e) {
      LOG.error(e.getMessage(), e);
    }
    return dataFeeds;
  }

  @Override
  public UIResponse convertCurrency(UIRequest request) throws JAXBException, IOException, MappingException, ParseException {
    UIResponse response = new UIResponse();
    UnifiedDataFeed dataFeeds = new UnifiedDataFeed();
    DateFormat dateFormat = new SimpleDateFormat(request.getDateFormat());
    dataFeeds = getUnifiedDataFeed(dateFormat.parse(request.getDate()));
    // TODO: Implement calculation
    // return dummy response
    for (DataFeed dataFeed : dataFeeds.getDataFeeds()) {
      response.getResults().add(new BankAndAmount(dataFeed.getDataFeedSourceDisplayName(), String.valueOf(new Random().nextDouble())));
    }
    return response;
  }

  private UnifiedDataFeed getUnifiedDataFeed(Date date) throws JAXBException, IOException, MappingException, ParseException {
    DataFeedSources dataFeedSources = configurationService.getDataFeedSources();
    UnifiedDataFeed unifiedDataFeed = new UnifiedDataFeed();
    for (DataFeedSource dataFeedSource : dataFeedSources.getDataFeedSource()) {
      if (dataFeedSource.isEnabled()) {
        DataFeed dataFeed = dataFeedService.getDataFeedFromDataFeedSourceClient(dataFeedSource, date);
        unifiedDataFeed.addDataFeed(dataFeed);
      }
    }
    return unifiedDataFeed;
  }

}
