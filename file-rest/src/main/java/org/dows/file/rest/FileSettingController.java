package org.dows.file.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.file.biz.FileSettingBiz;
import org.dows.file.entity.FileSetting;
import org.dows.file.form.FileSettingForm;
import org.dows.file.service.FileSettingService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件设置(FileSetting)表控制层
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:24
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class FileSettingController implements MybatisCrudRest<FileSettingForm, FileSetting, FileSettingService>, FileSettingRest {
    @Getter
    private final FileSettingService service;
    private final FileSettingBiz fileSettingBiz;
}
