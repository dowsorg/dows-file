package org.dows.file.api.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 普通上传文件
 */
@Data
public class FileEntity extends BaseFileEntity {
    /**
     * 文件数据
     */
    private MultipartFile file;
    /**
     * 文件md5
     */
    private String md5;
}
