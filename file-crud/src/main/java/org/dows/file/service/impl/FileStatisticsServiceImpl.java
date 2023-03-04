package org.dows.file.service.impl;

import org.dows.file.entity.FileStatistics;
import org.dows.file.mapper.FileStatisticsMapper;
import org.dows.file.service.FileStatisticsService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件统计(FileStatistics)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:27
 */
@Service
public class FileStatisticsServiceImpl extends MybatisCrudServiceImpl<FileStatisticsMapper, FileStatistics> implements FileStatisticsService {

}
