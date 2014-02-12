/**
 * 
 */
package ee.finestmedia.currencyconverter.service;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.model.UnifiedCurrencyDataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

/**
 * @author Anton Dubov
 */
public interface ConverterService {

  UnifiedCurrencyDataFeed getCurrenciesList();

  UnifiedCurrencyDataFeed convertCurrency(String originCurrencyCode, String destinationCurrencyCode, double amount, Date date);

  public CurrencyDataFeed getCurrencyFeedFromDataSourceClient(CurrencyDataSource currencyDataSource, Date date) throws JAXBException,
                                                                                                               IOException,
                                                                                                               MappingException,
                                                                                                               ParseException;

}
