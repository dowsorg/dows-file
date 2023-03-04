package org.dows.file.client.adapter;


import org.dows.file.api.FileAccess;
import org.dows.file.api.model.ChunkFile;
import org.dows.file.api.model.NormalFile;
import org.dows.file.api.model.RandomFile;
import org.dows.framework.api.Response;

import java.io.File;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/12/2022
 */
public abstract class FileAccessAdapter implements FileAccess {
    private FileAccess fileAccess;

    public FileAccessAdapter() {
    }

    public FileAccessAdapter(FileAccess fileAccess) {
        this.fileAccess = fileAccess;
    }

    @Override
    public Response upload(NormalFile file) {
        return fileAccess.upload(file);
    }

    @Override
    public Response upload(File file, String fileName) {
        return fileAccess.upload(file, fileName);
    }

    @Override
    public Response upload(ChunkFile file) {
        return fileAccess.upload(file);
    }

    @Override
    public Response upload(byte[] data, String fileName, String md5, String chunkMd5, int chunkIndex, int chunkCount) {
        return fileAccess.upload(data, fileName, md5, chunkMd5, chunkIndex, chunkCount);
    }

    @Override
    public Response uploadAndWrite(RandomFile file) {
        return fileAccess.uploadAndWrite(file);
    }

    @Override
    public Response uploadAndWrite(File file, String fileName) {
        return fileAccess.uploadAndWrite(file, fileName);
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, int offset, int length) {
        return fileAccess.uploadAndWrite(file, fileName, offset, length);
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName) {
        return fileAccess.uploadAndWrite(data, fileName);
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, int offset, int length) {
        return fileAccess.uploadAndWrite(data, fileName, offset, length);
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, long skip) {
        return fileAccess.uploadAndWrite(data, fileName, skip);
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, long skip) {
        return fileAccess.uploadAndWrite(file, fileName, skip);
    }

    @Override
    public Response uploadAndWrite(byte[] data, String fileName, int offset, int length, long skip) {
        return fileAccess.uploadAndWrite(data, fileName, offset, length, skip);
    }

    @Override
    public Response uploadAndWrite(File file, String fileName, int offset, int length, long skip) {
        return fileAccess.uploadAndWrite(file, fileName, offset, length, skip);
    }

    @Override
    public Response download(String fileName, File target) {
        return fileAccess.download(fileName, target);
    }

    @Override
    public Response download(String fileName, String filePath) {
        return fileAccess.download(fileName, filePath);
    }
}
