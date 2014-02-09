package ee.finestmedia.currencyconverter.model;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyDataFeed {

  private String currencyCode;
  private String displayName;
  private Date date;
  private BigDecimal rate;
  
  public CurrencyDataFeed() {
  }
  
  public CurrencyDataFeed(String currencyCode, String displayName, Date date, BigDecimal rate) {
    this.currencyCode = currencyCode;
    this.displayName = displayName;
    this.date = date;
    this.rate = rate;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

}
