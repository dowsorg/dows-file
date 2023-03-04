package org.dows.file.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseFileEntity implements Serializable {
    /**
     * 文件名（用户约定的文件名）
     */
    private String fileName;
}
