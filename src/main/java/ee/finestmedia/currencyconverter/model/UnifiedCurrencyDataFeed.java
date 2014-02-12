package ee.finestmedia.currencyconverter.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ee.finestmedia.currencyconverter.util.CollectionsUtil;

public class UnifiedCurrencyDataFeed {

  private Set<CurrencyDataFeed.Entry> unifiedDataFeedEntries = new HashSet<>();

  private List<CurrencyDataFeed> dataFeeds = new ArrayList<>();

  public void addCurrencyDataFeed(CurrencyDataFeed currencyDataFeed) {
    this.unifiedDataFeedEntries.addAll(currencyDataFeed.getEntries());
    this.dataFeeds.add(currencyDataFeed);
  }

  public List<CurrencyDataFeed.Entry> getUnifiedDataFeedEntries() {
    return CollectionsUtil.asSortedList(unifiedDataFeedEntries);
  }

  public List<CurrencyDataFeed> getDataFeeds() {
    return dataFeeds;
  }

}
