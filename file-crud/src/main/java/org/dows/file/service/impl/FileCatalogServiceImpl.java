package org.dows.file.service.impl;

import org.dows.file.entity.FileCatalog;
import org.dows.file.mapper.FileCatalogMapper;
import org.dows.file.service.FileCatalogService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件目录(FileCatalog)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:17
 */
@Service
public class FileCatalogServiceImpl extends MybatisCrudServiceImpl<FileCatalogMapper, FileCatalog> implements FileCatalogService {

}
