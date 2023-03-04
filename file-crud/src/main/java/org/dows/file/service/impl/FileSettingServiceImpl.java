package org.dows.file.service.impl;

import org.dows.file.entity.FileSetting;
import org.dows.file.mapper.FileSettingMapper;
import org.dows.file.service.FileSettingService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件设置(FileSetting)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:24
 */
@Service
public class FileSettingServiceImpl extends MybatisCrudServiceImpl<FileSettingMapper, FileSetting> implements FileSettingService {

}
