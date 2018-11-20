package org.datatransferproject.transfer.helium.files;

import org.datatransferproject.spi.transfer.provider.ExportResult;
import org.datatransferproject.spi.transfer.provider.Exporter;
import org.datatransferproject.spi.transfer.types.ExportInformation;
import org.datatransferproject.types.transfer.auth.AuthData;
import org.datatransferproject.types.transfer.auth.TokensAndUrlAuthData;
import org.datatransferproject.types.transfer.models.file.FileModel;

import java.util.Optional;
import java.util.UUID;

public class HeliumFileImporter implements Exporter<TokensAndUrlAuthData, FileModel> {

    @Override
    public ExportResult<FileModel> export(UUID jobId, TokensAndUrlAuthData authData, Optional<ExportInformation> exportInformation) throws Exception {
        return null;
    }
}
