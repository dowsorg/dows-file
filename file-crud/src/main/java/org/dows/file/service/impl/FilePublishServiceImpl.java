package org.dows.file.service.impl;

import org.dows.file.entity.FilePublish;
import org.dows.file.mapper.FilePublishMapper;
import org.dows.file.service.FilePublishService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件发布(FilePublish)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:20
 */
@Service
public class FilePublishServiceImpl extends MybatisCrudServiceImpl<FilePublishMapper, FilePublish> implements FilePublishService {

}
