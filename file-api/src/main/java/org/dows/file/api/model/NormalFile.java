package org.dows.file.api.model;

import lombok.Data;

import java.io.File;

/**
 * @author lait.zhang@gmail.com
 * @description: 普通文件
 * @weixin SH330786
 * @date 3/12/2022
 */
@Data
public class NormalFile extends BaseFile {
    /**
     * 文件
     */
    private File file;
    /**
     * 文件md5
     */
    private String md5;
}
