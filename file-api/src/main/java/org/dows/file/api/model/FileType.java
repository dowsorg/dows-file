package org.dows.file.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件类型
 */
@Data
public class FileType implements Serializable {
    /**
     * 类型名称
     */
    private String name;
    /**
     * 类型值
     */
    private String value;
    /**
     * 文件保存根目录
     */
    private String path;
    /**
     * 日前目录格式
     */
    private String prefix;
}
