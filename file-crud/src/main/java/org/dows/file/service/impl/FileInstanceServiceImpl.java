package org.dows.file.service.impl;

import org.dows.file.entity.FileInstance;
import org.dows.file.mapper.FileInstanceMapper;
import org.dows.file.service.FileInstanceService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件实例(FileInstance)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:18
 */
@Service
public class FileInstanceServiceImpl extends MybatisCrudServiceImpl<FileInstanceMapper, FileInstance> implements FileInstanceService {

}
