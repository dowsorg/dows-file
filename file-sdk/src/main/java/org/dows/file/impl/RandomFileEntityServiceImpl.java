package org.dows.file.impl;

import org.dows.file.adapter.FileEntityServiceApdater;
import org.dows.file.api.model.RandomFileEntity;
import org.springframework.stereotype.Service;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/13/2022
 */
@Service
public class RandomFileEntityServiceImpl extends FileEntityServiceApdater<RandomFileEntity> {
    @Override
    protected boolean verifyParam(RandomFileEntity entity) {
        if (super.verifyParam(entity)) {
            if (entity.getFile().isEmpty()) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
