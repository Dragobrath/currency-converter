/**
 * 
 */
package ee.finestmedia.currencyconverter.service;

import java.util.Date;

import ee.finestmedia.currencyconverter.model.UnifiedDataFeed;

/**
 * @author Anton Dubov
 */
public interface ConverterService {

  UnifiedDataFeed getCurrenciesList();

  UnifiedDataFeed convertCurrency(String originCurrencyCode, String destinationCurrencyCode, double amount, Date date);

}
