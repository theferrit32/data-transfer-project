package org.datatransferproject.transfer.helium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DOSPage {
  private int pageNumber;
  private int count;
  private String nextUrl;
  private String previousUrl;
  private List<DOSObject> results;

  public DOSPage(
      int pageNumber,
      int count,
      String nextUrl,
      String previousUrl,
      List<DOSObject> results) {
    this.pageNumber = pageNumber;
    this.count = count;
    this.nextUrl = nextUrl;
    this.previousUrl = previousUrl;
    this.results = results;
  }

  public DOSPage(int pageNumber, HashMap<String,Object> map) {
    this.pageNumber = pageNumber;
    if (map.containsKey("count")) {
      this.count = Integer.parseInt((String) map.get("count"));
    } else {
      throw new IllegalArgumentException("missing count field");
    }

    if (map.containsKey("next")) {
      this.nextUrl = (String) map.get("next");
    }
    if (map.containsKey("previous")) {
      this.previousUrl = (String) map.get("previous");
    }

    if (map.containsKey("results")) {
      List<HashMap<String,Object>> resultsList = (List<HashMap<String, Object>>) map.get("results");
      this.results = new ArrayList<DOSObject>();
      for (HashMap<String,Object> result : resultsList) {
        this.results.add(new DOSObject(result));
      }
    } else {
      throw new IllegalArgumentException("missing results field");
    }
  }
  public int getPageNumber() {
    return pageNumber;
  }

  public int getCount() {
    return count;
  }

  public String getNextUrl() {
    return nextUrl;
  }

  public String getPreviousUrl() {
    return previousUrl;
  }

  public List<DOSObject> getResults() {
    return results;
  }
}
