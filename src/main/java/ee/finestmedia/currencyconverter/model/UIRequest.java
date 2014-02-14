package ee.finestmedia.currencyconverter.model;

import java.io.Serializable;

public class UIRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String originCurrencyCode;
  private String destinationCurrencyCode;
  private String date;
  private String dateFormat;
  private double amount;

  public UIRequest() {
  }

  public UIRequest(String originCurrencyCode, String destinationCurrencyCode, String date, double amount) {
    this.originCurrencyCode = originCurrencyCode;
    this.destinationCurrencyCode = destinationCurrencyCode;
    this.date = date;
    this.amount = amount;
  }

  public String getOriginCurrencyCode() {
    return originCurrencyCode;
  }

  public void setOriginCurrencyCode(String originCurrencyCode) {
    this.originCurrencyCode = originCurrencyCode;
  }

  public String getDestinationCurrencyCode() {
    return destinationCurrencyCode;
  }

  public void setDestinationCurrencyCode(String destinationCurrencyCode) {
    this.destinationCurrencyCode = destinationCurrencyCode;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDateFormat() {
    return dateFormat;
  }

  public void setDateFormat(String dateFormat) {
    this.dateFormat = dateFormat;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

}
