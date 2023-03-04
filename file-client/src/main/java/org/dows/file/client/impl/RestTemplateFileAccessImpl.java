package org.dows.file.client.impl;

import cn.hutool.json.JSONUtil;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.dows.file.api.FileAccess;
import org.dows.file.api.model.ChunkFile;
import org.dows.file.api.model.NormalFile;
import org.dows.file.api.model.RandomFile;
import org.dows.file.api.utils.FileUtil;
import org.dows.file.api.utils.Md5Util;
import org.dows.file.api.utils.UploadStreamResource;
import org.dows.file.client.config.FileClientProperties;
import org.dows.framework.api.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * @author lait.zhang@gmail.com
 * @description: restTemplate文件访问接口实现类
 * @weixin SH330786
 * @date 3/12/2022
 */
@Slf4j
public class RestTemplateFileAccessImpl implements FileAccess {
    private final String NORMAL_UPLOAD_PATH;
    private final String CHUNK_UPLOAD_PATH;
    private final String RADNOM_UPLOAD_PATH;
    private final String DOWNLOAD_PATH;
    private final String host;
    private String authorization;
    private FileClientProperties fileClientProperties;
    private RestTemplate restFileClient;

    public RestTemplateFileAccessImpl(FileClientProperties fileClientProperties) {
        this.fileClientProperties = fileClientProperties;
        this.host = fileClientProperties.getHost();
        this.authorization = fileClientProperties.getAuth().getSecretId() + "-" +
                fileClientProperties.getAuth().getSecretKey();
        this.NORMAL_UPLOAD_PATH = fileClientProperties.getEndpoint().getUpNormal();
        this.CHUNK_UPLOAD_PATH = fileClientProperties.getEndpoint().getUpChuck();
        this.RADNOM_UPLOAD_PATH = fileClientProperties.getEndpoint().getDownChuck();
        this.DOWNLOAD_PATH = fileClientProperties.getEndpoint().getDownNormal();
    }

    @Override
    public Response upload(NormalFile file) {
        log.info("线程：{},上传文件：{},大小：{},MD5：{}", Thread.currentThread(), file.getFileName(), file.getFile().length(), file.getMd5());
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        // 构建请求体
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        try {
            UploadStreamResource uploadStreamResource = new UploadStreamResource(new FileInputStream(file.getFile()),
                    file.getFile().length(), file.getFileName());
            requestBody.add("file", uploadStreamResource);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("文件输入流转换错误");
        }
        // 发送上传请求
        org.springframework.http.HttpEntity<MultiValueMap> requestEntity = new org.springframework.http.HttpEntity<>(requestBody, requestHeaders);
        ResponseEntity<String> responseEntity = restFileClient.postForEntity(host, requestEntity, String.class);
        log.info("返回结果：{}", JSONUtil.toJsonStr(responseEntity.getBody()));
        return null;
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
        return null;
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
        return null;
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
        //return restFileClient.getForEntity(url,Response.class).getBody();
        return null;
    }

    @Override
    public Response download(String fileName, String filePath) {
        return download(fileName, new File(filePath));
    }

    private Response getResponse(MultipartEntityBuilder builder, byte[] data, String fileName, String path) {
        return null;


    }
}
