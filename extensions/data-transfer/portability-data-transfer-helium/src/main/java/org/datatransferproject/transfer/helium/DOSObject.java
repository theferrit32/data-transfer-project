package org.datatransferproject.transfer.helium;

import org.datatransferproject.types.transfer.models.file.FileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DOSObject {
  private String dataObjectName;
  private String dataObjectId;
  private String dateCreated;
  private String dateLastUpdated;
  private List<DOSUrlObject> urls;

  public DOSObject(String dataObjectName, String dataObjectId, String dateCreated, String dateLastUpdated, List<DOSUrlObject> urls) {
    this.dataObjectName = dataObjectName;
    this.dataObjectId = dataObjectId;
    this.dateCreated = dateCreated;
    this.dateLastUpdated = dateLastUpdated;
    this.urls = urls;
  }

  public DOSObject(HashMap<String,Object> map) {
    if (map.containsKey("dataobject_name")) {
      this.dataObjectName = (String) map.get("dataobject_name");
    } else {
      throw new IllegalArgumentException("missing dataobject_name");
    }

    if (map.containsKey("dataobject_id")) {
      this.dataObjectId = (String) map.get("dataobject_id");
    } else {
      throw new IllegalArgumentException("missing dataobject_id");
    }

    if (map.containsKey("date_created")) {
      this.dateCreated = (String) map.get("date_created");
    } else {
      throw new IllegalArgumentException("missing date_created");
    }

    if (map.containsKey("date_last_updated")) {
      this.dateLastUpdated = (String) map.get("date_last_updated");
    } else {
      throw new IllegalArgumentException("missing date_last_updated");
    }

    if (map.containsKey("urls")) {
      this.urls = new ArrayList<DOSUrlObject>();
      List<HashMap<String,Object>> objectUrls = (List<HashMap<String, Object>>) map.get("urls");
      for (HashMap<String,Object> urlObject : objectUrls) {
        this.urls.add(new DOSUrlObject(urlObject));
      }
    } else {
      throw new IllegalArgumentException("missing urls");
    }
  }

  public String getDataObjectName() {
    return dataObjectName;
  }

  public void setDataObjectName(String dataObjectName) {
    this.dataObjectName = dataObjectName;
  }

  public String getDataObjectId() {
    return dataObjectId;
  }

  public void setDataObjectId(String dataObjectId) {
    this.dataObjectId = dataObjectId;
  }

  public String getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(String dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getDateLastUpdated() {
    return dateLastUpdated;
  }

  public void setDateLastUpdated(String dateLastUpdated) {
    this.dateLastUpdated = dateLastUpdated;
  }

  public List<DOSUrlObject> getUrls() {
    return urls;
  }

  public void setUrls(List<DOSUrlObject> urls) {
    this.urls = urls;
  }

}
