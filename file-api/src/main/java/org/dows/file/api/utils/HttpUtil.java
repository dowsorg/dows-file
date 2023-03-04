package org.dows.file.api.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dows.file.api.constants.FileStatusCode;
import org.dows.file.api.model.TimeoutSetting;
import org.dows.framework.api.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;


public final class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static Response postData(String authorization, TimeoutSetting timeoutSetting, HttpEntity entity, URI uri) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClientBuilder.create().setRequestExecutor(new HttpRequestExecutorEx()).build();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
            httpPost.addHeader("Authorization", authorization);
            httpPost.setConfig(settingRequestConfig(timeoutSetting));
            httpPost.setEntity(entity);
            BasicHttpContext basicHttpContext = new BasicHttpContext();
            response = client.execute(httpPost, basicHttpContext);
            String hostAddress = (String) basicHttpContext.getAttribute(HttpRequestExecutorEx.HTTP_HOSTADDRESS);
            log.info("server endpoint ip ：{}", hostAddress);
            HttpEntity responseEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return JSON.parseObject(EntityUtils.toString(responseEntity), Response.class, Feature.InitStringFieldAsEmpty);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(client);
        }
        return Response.fail(FileStatusCode.UPLOAD_ERROR, response.toString());
    }

    /**
     * 下载文件
     *
     * @param url    文件路径
     * @param target 文件保存位置
     * @return
     */
    public static Response download(String authorization, TimeoutSetting timeoutSetting, String url, File target) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
            httpGet.addHeader("Authorization", authorization);
            httpGet.setConfig(settingRequestConfig(timeoutSetting));
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (entity.getContentType().getValue().contains("application/json")) {
                    return JSONUtil.toBean(EntityUtils.toString(entity), Response.class);
                } else {
                    if (!target.getParentFile().exists()) {
                        target.getParentFile().mkdirs();
                    }
                    FileUtils.copyInputStreamToFile(entity.getContent(), target);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(response);
            IOUtils.closeQuietly(client);
        }
        return null;
    }

    /**
     * 请求超时设置
     *
     * @param timeoutSetting
     * @return
     */
    private static RequestConfig settingRequestConfig(TimeoutSetting timeoutSetting) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeoutSetting.getConnectionRequestTimeout())
                .setSocketTimeout(timeoutSetting.getSocketTimeout())
                .setConnectTimeout(timeoutSetting.getConnectTimeout())
                .build();
        return requestConfig;
    }
}
