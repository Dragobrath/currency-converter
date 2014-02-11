package ee.finestmedia.currencyconverter.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Set;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;

public class CurrencyDataFeed {

  private CurrencyDataSource currencyDataSource;
  private Set<Entry> entries;

  public CurrencyDataSource getCurrencyDataSource() {
    return currencyDataSource;
  }

  public void setCurrencyDataSource(CurrencyDataSource currencyDataSource) {
    this.currencyDataSource = currencyDataSource;
  }

  public Set<Entry> getEntries() {
    return entries;
  }

  public void setEntries(Set<Entry> entries) {
    this.entries = entries;
  }

  public class Entry {
    private String currencyCode;
    private String displayName;
    private Date date;
    private BigDecimal rate;

    public Entry() {
    }

    public Entry(String currencyCode, Date date, BigDecimal rate) {
      this.setCurrencyCode(currencyCode);
      this.setDisplayName(Currency.getInstance(currencyCode).getDisplayName());
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
}
