package org.dows.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.file.entity.FilePublish;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 文件发布(FilePublish)
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:20
 */
@Mapper
public interface FilePublishMapper extends MybatisCrudMapper<FilePublish> {

}
