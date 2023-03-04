package org.dows.file.client.impl;


import lombok.extern.slf4j.Slf4j;
import org.dows.file.api.FileAccess;
import org.dows.file.api.model.ChunkFile;
import org.dows.file.api.model.NormalFile;
import org.dows.file.api.model.RandomFile;
import org.dows.file.client.config.FileClientProperties;
import org.dows.framework.api.Response;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;

/**
 * @author lait.zhang@gmail.com
 * @description: webclient 异步上传
 * @weixin SH330786
 * @date 6/3/2022
 */
@Slf4j
public class WebClientFileAccessImpl implements FileAccess {

    private final String host;
    private String authorization;
    private FileClientProperties fileClientProperties;
    private WebClient webClient;


    public WebClientFileAccessImpl(FileClientProperties fileClientProperties) {
        this.fileClientProperties = fileClientProperties;
        this.host = fileClientProperties.getHost();
        this.authorization = fileClientProperties.getAuth().getSecretId() + "-" +
                fileClientProperties.getAuth().getSecretKey();
    }

    @Override
    public Response upload(NormalFile file) {
        return null;
    }

    @Override
    public Response upload(File file, String fileName) {
        return null;
    }

    @Override
    public Response upload(ChunkFile file) {
        return null;
    }

    @Override
    public Response upload(byte[] data, String fileName, String md5, String chunkMd5, int chunkIndex, int chunkCount) {
        return null;
    }

    @Override
    public Response uploadAndWrite(RandomFile file) {
        return null;
    }

    @Override
    public Response uploadAndWrite(File file, String fileName) {
        return null;
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, int offset, int length) {
        return null;
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName) {
        return null;
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, int offset, int length) {
        return null;
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, long skip) {
        return null;
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, long skip) {
        return null;
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, int offset, int length, long skip) {
        return null;
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, int offset, int length, long skip) {
        return null;
    }

    @Override
    public Response download(String fileName, File target) {
        return null;
    }

    @Override
    public Response download(String fileName, String filePath) {
        return null;
    }
}
