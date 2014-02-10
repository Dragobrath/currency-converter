package ee.finestmedia.currencyconverter.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Set;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;

public class CurrencyDataFeed {

  private CurrencyDataSources.CurrencyDataSource currencyDataSource;
  private Set<Entry> entries;

  public CurrencyDataSources.CurrencyDataSource getCurrencyDataSource() {
    return currencyDataSource;
  }

  public void setCurrencyDataSource(CurrencyDataSources.CurrencyDataSource currencyDataSource) {
    this.currencyDataSource = currencyDataSource;
  }

  public Set<Entry> getEntries() {
    return entries;
  }

  public void setEntries(Set<Entry> entries) {
    this.entries = entries;
  }

  public class Entry {
    private Currency currency;
    private Date date;
    private BigDecimal rate;

    public Entry() {
    }

    public Entry(String currencyCode, Date date, BigDecimal rate) {
      this.currency = Currency.getInstance(currencyCode);
      this.date = date;
      this.rate = rate;
    }

    public Currency getCurrency() {
      return currency;
    }

    public void setCurrency(Currency currency) {
      this.currency = currency;
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
