package org.dows.file.api.model;

import lombok.Data;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 6/5/2022
 */
@Data
public class Endpoint {
    //分块上传
    private String upChuck;
    //普通上传/upload
    private String upNormal;
    //分块下载/download/chunk
    private String downChuck;
    //普通下载: /download
    private String downNormal;
}
