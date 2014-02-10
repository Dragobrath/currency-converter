/**
 * 
 */
package ee.finestmedia.currencyconverter.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Anton Dubov
 */
public interface ConverterService {

  String getCurrenciesList();

  String getCurrenciesListByDate(Date date);

  String convertCurrency(String originCurrencyCode, String destinationCurrencyCode, BigDecimal amount, Date date);

}
