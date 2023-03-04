package org.dows.file.biz;

import lombok.RequiredArgsConstructor;
import org.dows.file.service.FileCatalogService;
import org.springframework.stereotype.Service;

/**
 * 文件目录(FileCatalog)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:17
 */
@RequiredArgsConstructor
@Service
public class FileCatalogBiz {
    private final FileCatalogService service;

}
