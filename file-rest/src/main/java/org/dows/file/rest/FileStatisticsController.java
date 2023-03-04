package org.dows.file.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.file.biz.FileStatisticsBiz;
import org.dows.file.entity.FileStatistics;
import org.dows.file.form.FileStatisticsForm;
import org.dows.file.service.FileStatisticsService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件统计(FileStatistics)表控制层
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:26
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class FileStatisticsController implements MybatisCrudRest<FileStatisticsForm, FileStatistics, FileStatisticsService>, FileStatisticsRest {
    @Getter
    private final FileStatisticsService service;
    private final FileStatisticsBiz fileStatisticsBiz;
}
