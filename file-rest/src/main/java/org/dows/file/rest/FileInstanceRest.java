package org.dows.file.rest;

import io.swagger.annotations.Api;
import org.dows.file.api.FileInstanceApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 文件实例(FileInstance)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:18
 */
@Api(tags = "文件实例")
@RequestMapping("fileInstance")
public interface FileInstanceRest extends FileInstanceApi {


}
