package org.dows.file.rest;

import io.swagger.annotations.Api;
import org.dows.file.api.FileCatalogApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 文件目录(FileCatalog)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:16
 */
@Api(tags = "文件目录")
@RequestMapping("fileCatalog")
public interface FileCatalogRest extends FileCatalogApi {


}
