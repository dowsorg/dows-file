package org.dows.file.biz;

import lombok.RequiredArgsConstructor;
import org.dows.file.api.dto.FileInfo;
import org.dows.file.api.model.FileTransportProtocol;
import org.springframework.stereotype.Component;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/14/2022
 */
@Component
@RequiredArgsConstructor
public class FileConfigBiz {
    public FileTransportProtocol getTransportProtocol(FileInfo fileInfo) {
        return null;
    }
}
