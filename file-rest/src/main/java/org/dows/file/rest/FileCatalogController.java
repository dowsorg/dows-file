package org.dows.file.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.file.biz.FileCatalogBiz;
import org.dows.file.entity.FileCatalog;
import org.dows.file.form.FileCatalogForm;
import org.dows.file.service.FileCatalogService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件目录(FileCatalog)表控制层
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:16
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class FileCatalogController implements MybatisCrudRest<FileCatalogForm, FileCatalog, FileCatalogService>, FileCatalogRest {
    @Getter
    private final FileCatalogService service;
    private final FileCatalogBiz fileCatalogBiz;
}
