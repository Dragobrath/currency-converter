package ee.finestmedia.currencyconverter.dao.impl;

import org.springframework.stereotype.Repository;

import java.util.Date;

import ee.finestmedia.currencyconverter.dao.CurrencyDataFeedDao;
import ee.finestmedia.currencyconverter.generated.CurrencyDataSources;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;

/**
 * @author Anton Dubov
 *
 */
@Repository("LeeduPank")
public class LeeduPankDaoImpl implements CurrencyDataFeedDao {

  @Override
  public CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSources.CurrencyDataSource currencyDataSource, Date date) {
    System.out.println("WE ARE IN EESTI PANK DAO");
    return null;
  }

}
