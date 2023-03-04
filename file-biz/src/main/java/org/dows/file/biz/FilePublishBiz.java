package org.dows.file.biz;

import lombok.RequiredArgsConstructor;
import org.dows.file.service.FilePublishService;
import org.springframework.stereotype.Service;

/**
 * 文件发布(FilePublish)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:19
 */
@RequiredArgsConstructor
@Service
public class FilePublishBiz {
    private final FilePublishService service;

}
