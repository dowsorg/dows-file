package org.dows.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.file.entity.FileStatistics;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 文件统计(FileStatistics)
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:27
 */
@Mapper
public interface FileStatisticsMapper extends MybatisCrudMapper<FileStatistics> {

}
