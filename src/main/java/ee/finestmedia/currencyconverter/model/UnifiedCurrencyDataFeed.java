package ee.finestmedia.currencyconverter.model;

import java.util.List;
import java.util.Set;

public class UnifiedCurrencyDataFeed {

  private Set<CurrencyDataFeed.Entry> unifiedDataFeedEntrySet;

  private List<CurrencyDataFeed> dataFeeds;

  public void addCurrencyDataFeed(CurrencyDataFeed currencyDataFeed) {
    this.unifiedDataFeedEntrySet.addAll(currencyDataFeed.getEntries());
    this.dataFeeds.add(currencyDataFeed);
  }

  public Set<CurrencyDataFeed.Entry> getUnifiedDataFeedEntrySet() {
    return unifiedDataFeedEntrySet;
  }

  public void setUnifiedDataFeedEntrySet(Set<CurrencyDataFeed.Entry> unifiedDataFeedEntrySet) {
    this.unifiedDataFeedEntrySet = unifiedDataFeedEntrySet;
  }

  public List<CurrencyDataFeed> getDataFeeds() {
    return dataFeeds;
  }

  public void setDataFeeds(List<CurrencyDataFeed> dataFeeds) {
    this.dataFeeds = dataFeeds;
  }

}
