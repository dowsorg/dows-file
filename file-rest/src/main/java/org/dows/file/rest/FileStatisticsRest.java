package org.dows.file.rest;

import io.swagger.annotations.Api;
import org.dows.file.api.FileStatisticsApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 文件统计(FileStatistics)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:26
 */
@Api(tags = "文件统计")
@RequestMapping("fileStatistics")
public interface FileStatisticsRest extends FileStatisticsApi {


}
