package org.datatransferproject.transfer.helium.files;

import org.datatransferproject.spi.transfer.provider.ExportResult;
import org.datatransferproject.spi.transfer.provider.Exporter;
import org.datatransferproject.spi.transfer.provider.ImportResult;
import org.datatransferproject.spi.transfer.provider.Importer;
import org.datatransferproject.spi.transfer.types.ExportInformation;
import org.datatransferproject.types.transfer.auth.AuthData;
import org.datatransferproject.types.transfer.auth.TokensAndUrlAuthData;
import org.datatransferproject.types.transfer.models.file.FileModel;

import java.util.Optional;
import java.util.UUID;

public class HeliumFileImporter implements Importer<TokensAndUrlAuthData, FileModel> {

    @Override
    public ImportResult importItem(UUID jobId, TokensAndUrlAuthData authData, FileModel data) throws Exception {
        return null;
    }
}
