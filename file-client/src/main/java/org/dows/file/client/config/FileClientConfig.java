package org.dows.file.client.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.dows.file.api.FileAccess;
import org.dows.file.client.FileClient;
import org.dows.file.client.impl.HttpClientFileAccessImpl;
import org.dows.file.client.impl.RestTemplateFileAccessImpl;
import org.dows.file.client.impl.WebClientFileAccessImpl;
import org.dows.file.client.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lait.zhang@gmail.com
 * @description: 绕过证书认证，信任所有https请求,
 * @weixin SH330786
 * @date 6/3/2022
 */
@Slf4j
@Configuration
public class FileClientConfig {
    @Autowired
    private FileClientProperties fileClientProperties;

    @Bean
    FileClient fileClient(FileAccess fileAccess) {
        return new FileClient(fileAccess);
    }

    @ConditionalOnProperty(prefix = "cop.file", name = {"adapter"}, havingValue = "impl.org.dows.file.client.HttpClientFileAccessImpl")
    @Bean
    public FileAccess httpClientFileAccess() {
        return new HttpClientFileAccessImpl(fileClientProperties);
    }


    @ConditionalOnProperty(prefix = "cop.file", name = {"adapter"}, havingValue = "impl.org.dows.file.client.RestTemplateFileAccessImpl")
    @Bean
    public FileAccess restTemplateFileAccess() {
        return new RestTemplateFileAccessImpl(fileClientProperties);
    }

    @ConditionalOnProperty(prefix = "cop.file", name = {"adapter"}, havingValue = "impl.org.dows.file.client.WebClientFileAccessImpl")
    @Bean
    public FileAccess webClientFileAccess() {
        return new WebClientFileAccessImpl(fileClientProperties);
    }

    /**
     * fileClient隔离池
     *
     * @param fileClientProperties
     * @return
     */
    @Bean("restFileClientPool")
    public Map<String, RestTemplate> fileConectionPool(FileClientProperties fileClientProperties) {
        Map<String, RestTemplate> fileConectionPoolMap = new HashMap<>(8);
        Set<String> keys = fileClientProperties.getIsolatePools().keySet();
        for (String key : keys) {
            FileClientProperties.FileConectionPool fileConectionPool = fileClientProperties.getIsolatePools().get(key);
            HttpClient httpClient = HttpClientUtil.httpClient(fileConectionPool);
            ClientHttpRequestFactory clientHttpRequestFactory = clientHttpRequestFactory(httpClient, fileConectionPool);
            fileConectionPoolMap.put(key, createRestTemplate(clientHttpRequestFactory, fileConectionPool));
        }
        return fileConectionPoolMap;
    }


    /**
     * fileClient非隔离池
     */
    @Bean("restFileClient")
    public RestTemplate restTemplate() {
        FileClientProperties.FileConectionPool pool = fileClientProperties.getPool();
        HttpClient httpClient = HttpClientUtil.httpClient(pool);
        ClientHttpRequestFactory clientHttpRequestFactory = clientHttpRequestFactory(httpClient, pool);
        return createRestTemplate(clientHttpRequestFactory, pool);
    }


    /**
     * 初始化支持异步的RestTemplate,并加入spring的Bean工厂，由spring统一管理,如果用不到异步，则无须创建该对象
     * 这个也已经过时，用webclient
     * @return
     */
/*  @Bean(name = "asyncRestTemplate")
  @ConditionalOnMissingBean(AsyncRestTemplate.class)
  public AsyncRestTemplate asyncRestTemplate(RestTemplate restTemplate) {
    final Netty4ClientHttpRequestFactory factory = new Netty4ClientHttpRequestFactory();
    factory.setConnectTimeout(this.connectionTimeout);
    factory.setReadTimeout(this.readTimeout);
    return new AsyncRestTemplate(factory, restTemplate);
  }*/

    /**
     * 创建HTTP客户端工厂
     */
    private ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient, FileClientProperties.FileConectionPool pool) {
        /**
         *  maxTotalConnection 和 maxConnectionPerRoute 必须要配
         */
        if (pool.getMaxTotalConnect() <= 0) {
            throw new IllegalArgumentException("invalid maxTotalConnection: " + pool.getMaxTotalConnect());
        }
        if (pool.getMaxConnectPerRoute() <= 0) {
            throw new IllegalArgumentException("invalid maxConnectionPerRoute: " + pool.getMaxConnectPerRoute());
        }
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(pool.getConnectTimeout());
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(pool.getReadTimeout());
        // 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(pool.getConnectionRequestTimeout());
        return clientHttpRequestFactory;
    }

    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory, FileClientProperties.FileConectionPool pool) {
        RestTemplate restTemplate = new RestTemplate(factory);

        //我们采用RestTemplate内部的MessageConverter
        //重新设置StringHttpMessageConverter字符集，解决中文乱码问题
        modifyDefaultCharset(restTemplate, pool);

        //设置错误处理器
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
           /* @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
                //只要重写此方法，不去抛出HttpClientErrorException异常即可
                HttpStatus statusCode = clientHttpResponse.getStatusCode();
                log.info("错误码 = {}" , statusCode);
            }*/
        });

        return restTemplate;
    }

    /**
     * 修改默认的字符集类型为utf-8
     *
     * @param restTemplate
     * @param pool
     */
    private void modifyDefaultCharset(RestTemplate restTemplate, FileClientProperties.FileConectionPool pool) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        Charset defaultCharset = Charset.forName(pool.getCharset());
        converterList.add(1, new StringHttpMessageConverter(defaultCharset));
    }
}
