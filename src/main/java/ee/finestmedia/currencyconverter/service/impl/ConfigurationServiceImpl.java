package ee.finestmedia.currencyconverter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.io.IOException;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.XMLProcessingService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

  private static final Logger LOG = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

  @Autowired
  private XMLProcessingService xmlProcessingService;
  
  private CurrencyDataSources currencyDataSources = new CurrencyDataSources();

  public CurrencyDataSources getCurrencyDataSources() {
    if (isThereAnyCurrencyDataSources()) {
      return currencyDataSources;
    }
    try {
      currencyDataSources = (CurrencyDataSources) xmlProcessingService.unmarshalXMLFromFile("currencyDataSources.xml", CurrencyDataSources.class);
    } catch (JAXBException | IOException e) {
      LOG.error("Could not load currency data source configuration");
      LOG.error(e.getMessage(), e);
    }
    return currencyDataSources;
  }
  
  private boolean isThereAnyCurrencyDataSources() {
    return !(currencyDataSources.getCurrencyDataSource() == null || currencyDataSources.getCurrencyDataSource().isEmpty());
  }

}
