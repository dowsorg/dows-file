package org.dows.file.rest;

import io.swagger.annotations.Api;
import org.dows.file.api.FilePublishApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 文件发布(FilePublish)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:19
 */
@Api(tags = "文件发布")
@RequestMapping("filePublish")
public interface FilePublishRest extends FilePublishApi {


}
