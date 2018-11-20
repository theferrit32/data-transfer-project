package org.datatransferproject.transfer.helium.files;

public class HeliumClient {
    private String endpoint;
    private String accessToken;

    public static enum Protocol {
        DOS, REST
    }


    public HeliumClient(String endpoint, String accessToken) {
        this.endpoint = endpoint;
    }

    public Object fetchFile(String id) {
        return null;
    }
}
