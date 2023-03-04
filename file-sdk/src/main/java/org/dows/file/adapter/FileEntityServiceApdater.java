package org.dows.file.adapter;

import lombok.extern.slf4j.Slf4j;
import org.dows.file.FileEntityService;
import org.dows.file.api.constants.FileStatusCode;
import org.dows.file.api.exception.FileException;
import org.dows.file.api.model.BaseFileEntity;
import org.dows.file.api.model.FileType;
import org.dows.file.api.utils.FileNameParser;
import org.springframework.util.StringUtils;

@Slf4j
public abstract class FileEntityServiceApdater<T extends BaseFileEntity> implements FileEntityService<T> {
    protected boolean verifyParam(T entity) {
        if (StringUtils.isEmpty(entity.getFileName())) {
            return false;
        }
        return true;
    }

    @Override
    public void executeVerify(T t) {
        if (!verifyParam(t)) {
            log.error("请求参数错误");
            throw new FileException(FileStatusCode.BAD_PARAM);
        }

        String fileName = t.getFileName();
        FileNameParser parser = FileNameParser.getInstance();
        if (!parser.verify(fileName)) {
            log.error("文件名格式错误,{}", fileName);
            throw new FileException(FileStatusCode.BAD_FILENAME_FORMAT);
        }

        //校验文件类型
        String key = parser.getFileTypeField(fileName);
        FileType fileType = AppConfig.getFileType(key);
        if (fileType == null) {
            log.error("文件类型错误,{}", fileName);
            throw new FileException(FileStatusCode.BAD_FILE_TYPE);
        }
    }
}
