package org.dows.file.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.file.api.dto.FileInfo;
import org.springframework.stereotype.Component;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/14/2022
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class FileBiz {

    /**
     * 删除文件
     *
     * @param fileInfo
     */
    public void delFile(FileInfo fileInfo) {
        // todo 删除逻辑文件，放到FileBiz中

        // todo 删除物理文件，放到FileBiz中
    }
}
