package org.dows.file.rest;
import io.swagger.annotations.Api;
import org.dows.file.api.FileSettingApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 文件设置(FileSetting)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:23
 */
@Api(tags = "文件设置")
@RequestMapping("fileSetting")
public interface FileSettingRest extends FileSettingApi {


}
