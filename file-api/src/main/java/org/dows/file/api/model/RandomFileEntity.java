package org.dows.file.api.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 随机上传文件
 */
@Data
public class RandomFileEntity extends BaseFileEntity {
    /**
     * 文件数据
     */
    private MultipartFile file;

    private long seek;
    /**
     * 是否追加数据
     */
    private boolean append = true;
}
