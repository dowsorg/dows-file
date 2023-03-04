package org.dows.file.api.model;

import lombok.Data;


/**
 * @author lait.zhang@gmail.com
 * @description: 随机上传文件
 * @weixin SH330786
 * @date 3/12/2022
 */
@Data
public class RandomFile extends BaseFile {
    /**
     * 文件数据
     */
    private byte[] data;
    /**
     * 文件指针位置
     */
    private long seek;
    /**
     * 是否追加数据
     */
    private boolean append = true;
}
