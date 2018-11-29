package org.datatransferproject.transfer.helium.files;

import org.datatransferproject.spi.transfer.provider.ExportResult;
import org.datatransferproject.spi.transfer.provider.Exporter;
import org.datatransferproject.spi.transfer.types.ExportInformation;
import org.datatransferproject.types.transfer.auth.AppCredentials;
import org.datatransferproject.types.transfer.auth.TokensAndUrlAuthData;
import org.datatransferproject.types.transfer.models.file.FileContainer;
import org.datatransferproject.types.transfer.models.file.FileModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

public class HeliumFileExporter implements Exporter<TokensAndUrlAuthData, FileContainer> {

  private final String DOSEndpoint = "https://helium.commonsshare.org/dosapi/";
  @Override
  public ExportResult<FileContainer> export(
      UUID jobId,
      TokensAndUrlAuthData authData,
      Optional<ExportInformation> exportInformation)
      throws Exception {
    ArrayList<FileModel> exportedFiles = new ArrayList<FileModel>();

    String accessToken = "";

    HeliumClient client = new HeliumClient(DOSEndpoint, accessToken);
    HeliumConnection conn = new HeliumConnection(client);

    for (FileModel file : conn ) {
      exportedFiles.add(file);
    }
    return new ExportResult<FileContainer>(ExportResult.ResultType.END, new FileContainer(exportedFiles));
  }
}
