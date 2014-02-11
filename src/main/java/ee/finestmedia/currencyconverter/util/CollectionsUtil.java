package ee.finestmedia.currencyconverter.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionsUtil {
  
  public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> collection) {
    List<T> list = new ArrayList<T>(collection);
    Collections.sort(list);
    return list;
  }

}
