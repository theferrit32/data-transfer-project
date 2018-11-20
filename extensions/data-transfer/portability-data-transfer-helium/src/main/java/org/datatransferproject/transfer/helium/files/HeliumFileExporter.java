package org.datatransferproject.transfer.helium.files;

import org.datatransferproject.spi.transfer.provider.ExportResult;
import org.datatransferproject.spi.transfer.provider.Exporter;
import org.datatransferproject.spi.transfer.types.ExportInformation;
import org.datatransferproject.types.transfer.auth.TokensAndUrlAuthData;
import org.datatransferproject.types.transfer.models.file.FileContainer;
import org.datatransferproject.types.transfer.models.file.FileModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

public class HeliumFileExporter implements Exporter<TokensAndUrlAuthData, FileContainer> {

    @Override
    public ExportResult<FileContainer> export(
            UUID jobId,
            TokensAndUrlAuthData authData,
            Optional<ExportInformation> exportInformation)
            throws Exception {
        ArrayList<FileModel> exportedFiles = new ArrayList<FileModel>();
        String endpoint = "";
        String accessToken = "";

        HeliumClient client = new HeliumClient(endpoint, accessToken);
        HeliumConnection<FileModel> conn = new HeliumConnection<>(client);
        //Iterator<FileModel> iter = conn.iterator();
        for (FileModel file : conn) {
            exportedFiles.add(file);
        }
        return new ExportResult<FileContainer>(ExportResult.ResultType.END, new FileContainer(exportedFiles));


    }
}
