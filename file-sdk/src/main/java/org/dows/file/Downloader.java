package org.dows.file;

import org.dows.file.api.dto.FileInfo;

/**
 * @author lait.zhang@gmail.com
 * @description: 不同的协议  不同的规则
 * @weixin SH330786
 * @date 3/13/2022
 */
public interface Downloader {
    //获取到文件协议/规则 进行处理

    AccessTyp getAccessTyp();

    void download(FileInfo fileInfo);
}
