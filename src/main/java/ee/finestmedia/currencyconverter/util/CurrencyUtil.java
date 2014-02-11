package ee.finestmedia.currencyconverter.util;

import java.math.BigDecimal;

public class CurrencyUtil {
  
  public static final int PRECISION_SCALE = 10;
  
  public static BigDecimal divide(double divident, int divisor) {
    BigDecimal dividentAsBigDecimal = BigDecimal.valueOf(divident);
    BigDecimal divisorAsBigDecimal = new BigDecimal(divisor);
    return dividentAsBigDecimal.divide(divisorAsBigDecimal, PRECISION_SCALE, BigDecimal.ROUND_HALF_UP);
  }
  
  public static BigDecimal invertRate(BigDecimal divisor) {
    return new BigDecimal(1).divide(divisor, PRECISION_SCALE, BigDecimal.ROUND_HALF_UP);
  }

}
