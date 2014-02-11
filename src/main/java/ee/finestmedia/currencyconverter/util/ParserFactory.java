package ee.finestmedia.currencyconverter.util;

import ee.finestmedia.currencyconverter.client.parser.Parser;

public interface ParserFactory {

  public Parser getParser(String id);

}
