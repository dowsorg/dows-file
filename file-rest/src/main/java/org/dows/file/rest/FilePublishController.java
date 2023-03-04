package org.dows.file.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.file.biz.FilePublishBiz;
import org.dows.file.entity.FilePublish;
import org.dows.file.form.FilePublishForm;
import org.dows.file.service.FilePublishService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件发布(FilePublish)表控制层
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:19
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class FilePublishController implements MybatisCrudRest<FilePublishForm, FilePublish, FilePublishService>, FilePublishRest {
    @Getter
    private final FilePublishService service;
    private final FilePublishBiz filePublishBiz;
}
