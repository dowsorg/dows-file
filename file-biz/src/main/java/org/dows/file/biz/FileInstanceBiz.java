package org.dows.file.biz;

import lombok.RequiredArgsConstructor;
import org.dows.file.service.FileInstanceService;
import org.springframework.stereotype.Service;

/**
 * 文件实例(FileInstance)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:18
 */
@RequiredArgsConstructor
@Service
public class FileInstanceBiz {
    private final FileInstanceService service;

}
