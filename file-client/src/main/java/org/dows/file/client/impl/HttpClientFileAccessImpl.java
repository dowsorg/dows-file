package org.dows.file.client.impl;

import cn.hutool.core.bean.BeanUtil;


import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.dows.file.api.FileAccess;
import org.dows.file.api.model.ChunkFile;
import org.dows.file.api.model.NormalFile;
import org.dows.file.api.model.RandomFile;
import org.dows.file.api.model.TimeoutSetting;
import org.dows.file.api.utils.FileUtil;
import org.dows.file.api.utils.HttpUtil;
import org.dows.file.api.utils.Md5Util;
import org.dows.file.client.config.FileClientProperties;
import org.dows.framework.api.Response;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;


/**
 * @author lait.zhang@gmail.com
 * @description: 文件访问接口实现类
 * @weixin SH330786
 * @date 3/12/2022
 */
public class HttpClientFileAccessImpl implements FileAccess {
    private final String NORMAL_UPLOAD_PATH;
    private final String CHUNK_UPLOAD_PATH;
    private final String RADNOM_UPLOAD_PATH;
    private final String DOWNLOAD_PATH;
    private final String host;
    private final TimeoutSetting timeoutSetting;
    private String authorization;

    public HttpClientFileAccessImpl(FileClientProperties fileClientProperties) {
        this.host = fileClientProperties.getHost();
        this.NORMAL_UPLOAD_PATH = fileClientProperties.getEndpoint().getUpNormal();
        this.CHUNK_UPLOAD_PATH = fileClientProperties.getEndpoint().getUpChuck();
        this.RADNOM_UPLOAD_PATH = fileClientProperties.getEndpoint().getDownChuck();
        this.DOWNLOAD_PATH = fileClientProperties.getEndpoint().getDownNormal();
        this.authorization = fileClientProperties.getAuth().getSecretId() + "-" +
                fileClientProperties.getAuth().getSecretKey();
        this.timeoutSetting = BeanUtil.copyProperties(fileClientProperties.getPool(), TimeoutSetting.class);
    }

    @Override
    public Response upload(NormalFile file) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("md5", file.getMd5());
        builder.addTextBody("fileName", file.getFileName());
        builder.addBinaryBody("file", file.getFile(), ContentType.DEFAULT_BINARY, file.getFileName());
        HttpEntity entity = builder.build();
        return HttpUtil.postData(authorization, timeoutSetting, entity, URI.create(host + NORMAL_UPLOAD_PATH));
    }

    @Override
    public Response upload(File file, String fileName) {
        NormalFile normalFile = new NormalFile();
        normalFile.setFile(file);
        normalFile.setFileName(fileName);
        normalFile.setMd5(Md5Util.getMd5Hex(file));
        return upload(normalFile);
    }

    @Override
    public Response upload(ChunkFile file) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("md5", file.getMd5());
        builder.addTextBody("fileName", file.getFileName());
        builder.addTextBody("chunkMd5", file.getChunkMd5());
        builder.addTextBody("chunkCount", String.valueOf(file.getChunkCount()));
        builder.addTextBody("chunkIndex", String.valueOf(file.getChunkIndex()));
        return getResponse(builder, file.getData(), file.getFileName(), CHUNK_UPLOAD_PATH);
    }

    @Override
    public Response upload(byte[] data, String fileName, String md5, String chunkMd5, int chunkIndex, int chunkCount) {
        ChunkFile chunkFile = new ChunkFile();
        chunkFile.setData(data);
        chunkFile.setFileName(fileName);
        chunkFile.setMd5(md5);
        chunkFile.setChunkMd5(chunkMd5);
        chunkFile.setChunkIndex(chunkIndex);
        chunkFile.setChunkCount(chunkCount);
        return upload(chunkFile);
    }

    @Override
    public Response uploadAndWrite(RandomFile file) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("seek", String.valueOf(file.getSeek()));
        builder.addTextBody("append", String.valueOf(file.isAppend()));
        builder.addTextBody("fileName", file.getFileName());
        return getResponse(builder, file.getData(), file.getFileName(), RADNOM_UPLOAD_PATH);
    }

    @Override
    public Response uploadAndWrite(File file, String fileName) {
        try {
            RandomFile randomFile = new RandomFile();
            randomFile.setData(FileUtils.readFileToByteArray(file));
            randomFile.setFileName(fileName);
            randomFile.setAppend(true);
            return uploadAndWrite(randomFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.fail();
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, int offset, int length) {
        RandomFile randomFile = new RandomFile();
        randomFile.setData(FileUtil.readBytes(file, offset, length));
        randomFile.setFileName(fileName);
        randomFile.setAppend(true);
        return uploadAndWrite(randomFile);
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName) {
        RandomFile randomFile = new RandomFile();
        randomFile.setData(data);
        randomFile.setFileName(fileName);
        randomFile.setAppend(true);
        return uploadAndWrite(randomFile);
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, int offset, int length) {
        RandomFile randomFile = new RandomFile();
        randomFile.setData(FileUtil.subBytes(data, offset, length));
        randomFile.setFileName(fileName);
        randomFile.setAppend(true);
        return uploadAndWrite(randomFile);
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, long skip) {
        RandomFile randomFile = new RandomFile();
        randomFile.setData(data);
        randomFile.setFileName(fileName);
        randomFile.setAppend(false);
        randomFile.setSeek(skip);
        return uploadAndWrite(randomFile);
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, long skip) {
        try {
            RandomFile randomFile = new RandomFile();
            randomFile.setData(FileUtils.readFileToByteArray(file));
            randomFile.setFileName(fileName);
            randomFile.setAppend(false);
            randomFile.setSeek(skip);
            return uploadAndWrite(randomFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.fail();
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, int offset, int length, long skip) {
        RandomFile randomFile = new RandomFile();
        randomFile.setData(FileUtil.subBytes(data, offset, length));
        randomFile.setFileName(fileName);
        randomFile.setAppend(false);
        randomFile.setSeek(skip);
        return uploadAndWrite(randomFile);
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, int offset, int length, long skip) {
        RandomFile randomFile = new RandomFile();
        randomFile.setData(FileUtil.readBytes(file, offset, length));
        randomFile.setFileName(fileName);
        randomFile.setAppend(false);
        randomFile.setSeek(skip);
        return uploadAndWrite(randomFile);
    }

    @Override
    public Response download(String fileName, File target) {
        String url = host + DOWNLOAD_PATH + "?fileName=" + fileName;
        return HttpUtil.download(authorization, timeoutSetting, url, target);
    }

    @Override
    public Response download(String fileName, String filePath) {
        return download(fileName, new File(filePath));
    }

    private Response getResponse(MultipartEntityBuilder builder, byte[] data, String fileName, String path) {
        builder.setCharset(StandardCharsets.UTF_8);
        builder.setMode(HttpMultipartMode.RFC6532);
        builder.addBinaryBody("file", data, ContentType.DEFAULT_BINARY, fileName);
        HttpEntity entity = builder.build();
        return HttpUtil.postData(authorization, timeoutSetting, entity, URI.create(host + path));
    }
}
