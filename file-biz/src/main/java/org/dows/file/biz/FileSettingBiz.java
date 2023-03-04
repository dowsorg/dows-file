package org.dows.file.biz;

import lombok.RequiredArgsConstructor;
import org.dows.file.service.FileSettingService;
import org.springframework.stereotype.Service;

/**
 * 文件设置(FileSetting)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:24
 */
@RequiredArgsConstructor
@Service
public class FileSettingBiz {
    private final FileSettingService service;

}
