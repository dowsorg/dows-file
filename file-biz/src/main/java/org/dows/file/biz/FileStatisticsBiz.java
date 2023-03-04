package org.dows.file.biz;

import lombok.RequiredArgsConstructor;
import org.dows.file.service.FileStatisticsService;
import org.springframework.stereotype.Service;

/**
 * 文件统计(FileStatistics)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:27
 */
@RequiredArgsConstructor
@Service
public class FileStatisticsBiz {
    private final FileStatisticsService service;

}
