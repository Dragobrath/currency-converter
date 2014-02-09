package ee.finestmedia.currencyconverter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.XMLProcessingService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

  private static final Logger LOG = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

  @Autowired
  private XMLProcessingService xmlProcessingService;

  public CurrencyDataSources getCurrencyDataSources() {
    CurrencyDataSources unmarshalledObject;
    try {
      unmarshalledObject = (CurrencyDataSources) xmlProcessingService.unmarshalXMLFromFile("currencyDataSources.xml", CurrencyDataSources.class);
    } catch (JAXBException e) {
      unmarshalledObject = new CurrencyDataSources();
      LOG.error("Could not load currency data source configuration");
      LOG.error(e.getMessage(), e);
    }
    return unmarshalledObject;
  }

}
