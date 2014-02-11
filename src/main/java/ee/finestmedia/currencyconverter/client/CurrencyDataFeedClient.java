package ee.finestmedia.currencyconverter.client;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

public interface CurrencyDataFeedClient {

  CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date) throws JAXBException, IOException, MappingException, ParseException;

}
