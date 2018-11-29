package org.datatransferproject.transfer.helium.files;

import org.datatransferproject.types.transfer.models.file.FileModel;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HeliumClient {
  private String endpoint;
  private String accessToken;

  public HeliumClient(String endpoint, String accessToken) {
    this.endpoint = endpoint;
    this.accessToken = accessToken;
  }

  public String getAccessToken() {
   return this.accessToken;
  }

  public String getEndpoint() {
    return this.endpoint;
  }

  public FileModel fetchFile(String id) {
    // https://helium.commonsshare.org/dosapi/dataobjects/2ef6d55329d94ceda9da056b3073cf25/
    return null;
  }

}
