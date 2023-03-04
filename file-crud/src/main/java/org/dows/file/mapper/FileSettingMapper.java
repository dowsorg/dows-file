package org.dows.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.file.entity.FileSetting;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 文件设置(FileSetting)
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:25
 */
@Mapper
public interface FileSettingMapper extends MybatisCrudMapper<FileSetting> {

}
