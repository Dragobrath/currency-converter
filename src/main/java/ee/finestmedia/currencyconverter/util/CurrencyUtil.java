package ee.finestmedia.currencyconverter.util;

import java.math.BigDecimal;

/**
 * Utility class for operations on currencies
 * 
 * @author Anton Dubov
 */
public class CurrencyUtil {
  
  public static final int PRECISION_SCALE = 10;
  
  /**
   * Divides one number by another
   * 
   * @param divident
   * @param divisor
   * @return result
   */
  public static BigDecimal divide(double divident, int divisor) {
    BigDecimal dividentAsBigDecimal = BigDecimal.valueOf(divident);
    BigDecimal divisorAsBigDecimal = new BigDecimal(divisor);
    return dividentAsBigDecimal.divide(divisorAsBigDecimal, PRECISION_SCALE, BigDecimal.ROUND_HALF_UP);
  }
  
  /**
   * Divides 1 by given number, thus inverts currency rate
   * 
   * @param currencyRate
   * @return result
   */
  public static BigDecimal invertRate(BigDecimal currencyRate) {
    return new BigDecimal(1).divide(currencyRate, PRECISION_SCALE, BigDecimal.ROUND_HALF_UP);
  }

}
