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
   * Divides double by integer
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
   * Divides one BigDecimal by another
   * 
   * @param divident
   * @param divisor
   * @return result
   */
  public static BigDecimal divide(BigDecimal divident, BigDecimal divisor) {
    return divident.divide(divisor, PRECISION_SCALE, BigDecimal.ROUND_HALF_UP);
  }

}
