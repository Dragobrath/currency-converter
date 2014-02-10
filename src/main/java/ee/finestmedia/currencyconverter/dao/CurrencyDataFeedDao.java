package ee.finestmedia.currencyconverter.dao;

import java.util.Date;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;

import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;

public interface CurrencyDataFeedDao {
  
  CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSources.CurrencyDataSource currencyDataSource, Date date);

}
