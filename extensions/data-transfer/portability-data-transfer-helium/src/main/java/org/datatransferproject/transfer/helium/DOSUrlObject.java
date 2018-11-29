package org.datatransferproject.transfer.helium;

import java.util.HashMap;

public class DOSUrlObject {
  private String url;
  private String checksum;
  private String checksumType;
  private String mimeType;
  private long size;

  public DOSUrlObject(String url, String checksum, String checksumType, String mimeType, long size) {
    this.url = url;
    this.checksum = checksum;
    this.checksumType = checksumType;
    this.mimeType = mimeType;
    this.size = size;
  }

  public DOSUrlObject(HashMap<String,Object> map) {
    if (map.containsKey("url")) {
      this.url = (String) map.get("url");
    } else {
      throw new IllegalArgumentException("missing url");
    }

    if (map.containsKey("checksum")) {
      this.checksum = (String) map.get("checksum");
    } else {
      throw new IllegalArgumentException("missing checksum");
    }

    if (map.containsKey("checksum_type")) {
      this.checksumType = (String) map.get("checksum_type");
    } else {
      throw new IllegalArgumentException("missing checksum_type");
    }

    if (map.containsKey("mime_type")) {
      this.mimeType = (String) map.get("mime_type");
    } else {
      throw new IllegalArgumentException("missing mime_type");
    }

    if (map.containsKey("size")) {
      this.size = Long.parseLong((String) map.get("size"));
    } else {
      throw new IllegalArgumentException("missing size");
    }
  }

  public String getUrl() {
    return url;
  }

  public String getChecksum() {
    return checksum;
  }

  public String getChecksumType() {
    return checksumType;
  }

  public String getMimeType() {
    return mimeType;
  }

  public long getSize() {
    return size;
  }
}
