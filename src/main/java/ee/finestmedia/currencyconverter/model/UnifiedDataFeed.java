package ee.finestmedia.currencyconverter.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ee.finestmedia.currencyconverter.util.CollectionsUtil;

public class UnifiedDataFeed {

  private Set<DataFeed.Entry> unifiedDataFeedEntries = new HashSet<>();

  private List<DataFeed> dataFeeds = new ArrayList<>();

  public void addDataFeed(DataFeed dataFeed) {
    this.unifiedDataFeedEntries.addAll(dataFeed.getEntries());
    this.dataFeeds.add(dataFeed);
  }

  public List<DataFeed.Entry> getUnifiedDataFeedEntries() {
    return CollectionsUtil.asSortedList(unifiedDataFeedEntries);
  }

  public List<DataFeed> getDataFeeds() {
    return dataFeeds;
  }

}
