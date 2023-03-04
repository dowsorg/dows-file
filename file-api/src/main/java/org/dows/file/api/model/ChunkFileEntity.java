package org.dows.file.api.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 分片上传文件
 */
@Data
public class ChunkFileEntity extends FileEntity {
    /**
     * 文件数据
     */
    private MultipartFile file;
    /**
     * 文件md5
     */
    private String md5;
    /**
     * 分片md5
     */
    private String chunkMd5;
    /**
     * 分片总数
     */
    private int chunkCount;
    /**
     * 分片索引
     */
    private int chunkIndex;
}
