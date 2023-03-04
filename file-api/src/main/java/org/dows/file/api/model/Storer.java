package org.dows.file.api.model;

import lombok.Data;

/**
 * @author lait.zhang@gmail.com
 * @description: 存储设置
 * @weixin SH330786
 * @date 6/5/2022
 */
@Data
public class Storer {
    private String host;
    private Endpoint endpoint;
    // 客户端回调地址
    private String callback;
}
