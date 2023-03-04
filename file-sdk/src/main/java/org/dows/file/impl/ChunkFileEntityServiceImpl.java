package org.dows.file.impl;

import org.dows.file.adapter.FileEntityServiceApdater;
import org.dows.file.api.model.ChunkFileEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/13/2022
 */
@Service
public class ChunkFileEntityServiceImpl extends FileEntityServiceApdater<ChunkFileEntity> {
    @Override
    protected boolean verifyParam(ChunkFileEntity entity) {
        if (super.verifyParam(entity)) {
            if (entity.getFile().isEmpty()) {
                return false;
            }
            if (StringUtils.isEmpty(entity.getMd5())) {
                return false;
            }
            if (StringUtils.isEmpty(entity.getChunkMd5())) {
                return false;
            }
            if (entity.getChunkCount() < 0) {
                return false;
            }
            if (entity.getChunkIndex() < 0) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
