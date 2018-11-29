package org.datatransferproject.transfer.helium.files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.datatransferproject.transfer.helium.DOSObject;
import org.datatransferproject.transfer.helium.DOSPage;
import org.datatransferproject.types.transfer.models.file.FileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

public class HeliumConnection implements Iterable<FileModel> {
  private static final Logger logger = LoggerFactory.getLogger(HeliumConnection.class);

  private HeliumClient heliumClient;
  //private String url;
  private HeliumConnectionIterator iterator;

  public HeliumConnection(HeliumClient heliumClient) {
    //this.url = url;
    this.heliumClient = heliumClient;
  }

  public static class HeliumConnectionIterator implements Iterator {
    private HeliumConnection connection = null;
    private DOSPage currentPage = null;
    private Iterator<DOSObject> resultIterator = null;

    public HeliumConnectionIterator(HeliumConnection connection) {
      this.connection = connection;
    }

    private DOSPage fetchPage(int pageNumber) {
      logger.debug("fetching page {}", pageNumber);
      String remoteUrl = connection.heliumClient.getEndpoint() + "/dataobjects/?page=" + Long.toString(pageNumber);
      StringBuilder result = new StringBuilder();
      URL url;
      try {
        url = new URL(remoteUrl);
      } catch (MalformedURLException e) {
        e.printStackTrace();
        return null;
      }
      HttpURLConnection conn;
      try {
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.addRequestProperty("Authorization",
            "Bearer " + connection.heliumClient.getAccessToken());

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
          result.append(line);
        }
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
      ObjectMapper objectMapper = new ObjectMapper();
      TypeReference<HashMap<String,Object>> ref = new TypeReference<HashMap<String, Object>>() {};
      try {
        HashMap<String,Object> map = objectMapper.readValue(result.toString(), ref);
        return new DOSPage(pageNumber, map);
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    /**
     * Check to see if a new object listing page needs to be fetched from the API, and fetch it if so.
     *
     * Set currentPage to point to the new page. Set resultIterator to an iterator over the result listing
     * of the DOS page from the API.
     */
    private void ensureFetched() {
      if (this.currentPage == null || this.resultIterator == null) {
        logger.debug("currentPage not initialized yet");
        this.currentPage = fetchPage(1); // page indexing begins at 1
        this.resultIterator = currentPage.getResults().iterator();
      } else if (!resultIterator.hasNext()) {
        logger.debug("current resultIterator has reached end of result set");
        this.currentPage = fetchPage(currentPage.getPageNumber() + 1); // fetch next page
        this.resultIterator = currentPage.getResults().iterator();
      }
    }

    @Override
    public boolean hasNext() {
      ensureFetched();
      return this.resultIterator.hasNext();
    }

    private FileModel DOSObjectToFileModel(DOSObject dosObject) {
      return new FileModel(dosObject.getDataObjectId(), dosObject.getDataObjectName(), "");
    }

    @Override
    public FileModel next() {
      ensureFetched();
      DOSObject object = this.resultIterator.next();
      return DOSObjectToFileModel(object);
    }

    @Override
    public void remove() {
      throw new NotImplementedException();
    }

    @Override
    public void forEachRemaining(Consumer consumer) {
      while (this.hasNext()) {
        consumer.accept(this.next());
      }
    }
  }

  @Override
  public Iterator<FileModel> iterator() {
    return new HeliumConnectionIterator(this);
  }

  @Override
  public void forEach(Consumer consumer) {

  }

  @Override
  public Spliterator<FileModel> spliterator() {
    throw new NotImplementedException();
  }
}
