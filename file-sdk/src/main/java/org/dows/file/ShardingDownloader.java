package org.dows.file;

import org.dows.file.api.dto.FileInfo;
import org.dows.framework.oss.api.S3OssClient;
import org.springframework.stereotype.Component;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/13/2022
 */
@Component
public class ShardingDownloader implements Downloader {

    /**
     * todo 这个目前先写死,后面需要根据协议动态获取
     */
    //@Resource(name = TencentOssConfiguration.DEFAULT_BEAN_NAME)
    private S3OssClient ossClient;

    @Override
    public AccessTyp getAccessTyp() {
        return AccessTyp.SHARDING;
    }

    @Override
    public void download(FileInfo fileInfo) {
        // todo 目前，这个调用的是腾讯的下载，ossClinet 后期是要活的
        //ossClient.downLoadCheckPoint();

    }


}
