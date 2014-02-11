/**
 * 
 */
package ee.finestmedia.currencyconverter.service;

import java.math.BigDecimal;
import java.util.Date;

import ee.finestmedia.currencyconverter.model.UnifiedCurrencyDataFeed;

/**
 * @author Anton Dubov
 */
public interface ConverterService {

  UnifiedCurrencyDataFeed getCurrenciesList();

  UnifiedCurrencyDataFeed convertCurrency(String originCurrencyCode, String destinationCurrencyCode, BigDecimal amount, Date date);

}
