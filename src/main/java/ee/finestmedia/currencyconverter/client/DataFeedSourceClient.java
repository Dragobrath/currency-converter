package ee.finestmedia.currencyconverter.client;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

public interface DataFeedSourceClient {

  DataFeed getDataFeed(DataFeedSource dataFeedSource, Date date) throws JAXBException, IOException, MappingException, ParseException;

}
