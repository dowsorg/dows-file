package org.dows.file.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 抽象上传文件
 */
@Data
public abstract class BaseFile implements Serializable {
    /**
     * 用户自定义文件名
     */
    private String fileName;
}
