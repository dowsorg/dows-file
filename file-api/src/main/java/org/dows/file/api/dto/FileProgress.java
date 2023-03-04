package org.dows.file.api.dto;

import lombok.Data;

@Data
public class FileProgress {
    private long bytesRead;
    private long contentLength;
    private long items;
}
