package ee.finestmedia.currencyconverter.client.parser;

import java.util.Date;

import org.springframework.stereotype.Repository;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;

/**
 * @author Anton Dubov
 * 
 */
@Repository("EestiPank")
public class XmlParser {

  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSources.CurrencyDataSource currencyDataSource, Date date) {
    return null;
  }

}
