package ee.finestmedia.currencyconverter.client.parser;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;

public interface Parser {

  Object getDataFeed(DataFeedSource dataFeedSource, Date date, Class<?> responseType) throws JAXBException, IOException;

}
