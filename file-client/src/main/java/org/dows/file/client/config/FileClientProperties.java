package org.dows.file.client.config;


import lombok.Data;
import org.dows.file.api.FileAccess;
import org.dows.file.api.model.Coder;
import org.dows.file.api.model.Endpoint;
import org.dows.file.api.model.Uploader;
import org.dows.framework.api.util.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 6/3/2022
 */
@Component
@Data
@PropertySource(value = "classpath:application-file.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "cop.file")
public class FileClientProperties {
    // 上传host
    private String host;
    // 上传文件端点
    //private String endpoint;
    //private Map<String, String> endpoint;
    private Endpoint endpoint;
    // 文件存放位置
    private String storePath;

    private Class<FileAccess> adapter;

    @NestedConfigurationProperty
    private Coder coder;
    @NestedConfigurationProperty
    private Uploader uploader;
    //auth
    @NestedConfigurationProperty
    private Auth auth;

    // 公用全局链接池
    @NestedConfigurationProperty
    private FileConectionPool pool;
    /**
     * 自定义连接池
     * todo 针对不同的签名or账号or不同的业务设定各自的连接池
     */
    private Map<String, FileConectionPool> isolatePools;

    /**
     * 默认配置，可覆盖
     */
    @Data
    public static class FileConectionPool {

        /**
         * 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
         */
        private int socketTimeout = 300;

        /**
         * java配置的优先级低于yml配置；如果yml配置不存在，会采用java配置
         */
        /**
         * 连接池的最大连接数
         */
        private int maxTotalConnect = 300;
        /**
         * 同路由的并发数
         */
        private int maxConnectPerRoute = 50;
        /**
         * 客户端和服务器建立连接超时，默认2s
         */
        private int connectTimeout = 2 * 1000;
        /**
         * 从连接池获取连接的超时时间,不宜过长,单位ms
         */
        private int connectionRequestTimeout = 5000;
        /**
         * 指客户端从服务器读取数据包的间隔超时时间,不是总读取时间，默认30s
         */
        private int readTimeout = 30 * 1000;

        private String charset = "UTF-8";
        /**
         * 重试次数,默认2次
         */
        private int retryTimes = 2;

        /**
         * 针对不同的地址,特别设置不同的长连接保持时间
         */
        private Map<String, Integer> keepAliveTargetHost;
        /**
         * 针对不同的地址,特别设置不同的长连接保持时间,单位 s
         */
        private int keepAliveTime = 60;

    }

    @Data
    public static class Auth {
        private String secretId;
        private String secretKey;
    }
}
