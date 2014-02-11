package ee.finestmedia.currencyconverter.client.parser;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.CurrencyDataSources.CurrencyDataSource;
import ee.finestmedia.currencyconverter.model.CurrencyDataFeed;

public interface Parser {

  CurrencyDataFeed getCurrencyDataFeed(CurrencyDataSource currencyDataSource, Date date, Class<?> responseType) throws JAXBException, IOException;

}
