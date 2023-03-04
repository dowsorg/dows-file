package org.dows.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.file.entity.FileInstance;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 文件实例(FileInstance)
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:18
 */
@Mapper
public interface FileInstanceMapper extends MybatisCrudMapper<FileInstance> {

}
