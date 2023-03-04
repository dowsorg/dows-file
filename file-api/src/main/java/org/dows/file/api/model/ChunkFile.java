package org.dows.file.api.model;

import lombok.Data;

/**
 * @author lait.zhang@gmail.com
 * @description: 分片文件
 * @weixin SH330786
 * @date 3/12/2022
 */
@Data
public class ChunkFile extends BaseFile {
    /**
     * 分片文件数据
     */
    private byte[] data;
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
