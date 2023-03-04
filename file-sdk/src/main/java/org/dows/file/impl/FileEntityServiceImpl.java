package org.dows.file.impl;

import org.dows.file.adapter.FileEntityServiceApdater;
import org.dows.file.api.model.FileEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/13/2022
 */
@Service
public class FileEntityServiceImpl extends FileEntityServiceApdater<FileEntity> {
    @Override
    protected boolean verifyParam(FileEntity entity) {
        if (super.verifyParam(entity)) {
            if (entity.getFile().isEmpty()) {
                return false;
            }
            if (StringUtils.isEmpty(entity.getMd5())) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
