package org.dows.file.api.model;

import lombok.Data;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 6/5/2022
 */
@Data
public class TimeoutSetting {
    //设置从connect Manager获取Connection 超时时间，单位毫秒
    private int connectionRequestTimeout;
    //请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
    private int socketTimeout;
    //设置连接超时时间，单位毫秒
    private int connectTimeout;
}
