package org.datatransferproject.types.transfer.models.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import org.datatransferproject.types.transfer.models.ContainerResource;

import java.util.Collection;

public class FileContainer extends ContainerResource {
    private final Collection<FileModel> files;

    public FileContainer(
            @JsonProperty("files") Collection<FileModel> files) {
        this.files = files == null ? ImmutableList.of() : files;
    }

    public Collection<FileModel> getFiles() { return this.files; }
}
