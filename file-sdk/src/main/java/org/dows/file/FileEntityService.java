package org.dows.file;


import org.dows.file.api.model.BaseFileEntity;


/**
 * @author lait.zhang@gmail.com
 * @description: 文件服务
 * @weixin SH330786
 * @date 3/13/2022
 */
public interface FileEntityService<T extends BaseFileEntity> {
    /**
     * 校验文件
     *
     * @param t
     */
    void executeVerify(T t);
}
