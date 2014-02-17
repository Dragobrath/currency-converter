package ee.finestmedia.currencyconverter.util.exception;

public class CurrencyNotFoundException extends Exception {
  
  private static final long serialVersionUID = 1L;

  public CurrencyNotFoundException(String message) {
    super(message);
  }

}
