package org.dows.file.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.file.biz.FileInstanceBiz;
import org.dows.file.entity.FileInstance;
import org.dows.file.form.FileInstanceForm;
import org.dows.file.service.FileInstanceService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件实例(FileInstance)表控制层
 *
 * @author VX:PN15855012581
 * @since 2022-06-03 12:09:18
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class FileInstanceController implements MybatisCrudRest<FileInstanceForm, FileInstance, FileInstanceService>, FileInstanceRest {
    @Getter
    private final FileInstanceService service;
    private final FileInstanceBiz fileInstanceBiz;
}
