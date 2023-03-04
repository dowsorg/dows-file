package org.dows.file.api;

import org.dows.file.api.model.ChunkFile;
import org.dows.file.api.model.NormalFile;
import org.dows.file.api.model.RandomFile;
import org.dows.framework.api.Response;

import java.io.File;

/**
 * 通用文件操作接口
 */
public interface FileAccess {

    /**
     * 上传普通文件
     *
     * @param file 普通文件
     * @return
     */
    Response upload(NormalFile file);

    /**
     * 上传普通文件
     *
     * @param file     普通文件
     * @param fileName 约定文件名
     * @return
     */
    Response upload(File file, String fileName);

    /**
     * 上传分片文件
     *
     * @param file 文件分片
     * @return
     */
    Response upload(ChunkFile file);

    /**
     * 上传分片文件
     *
     * @param data       分片数据
     * @param fileName   文件名
     * @param md5        文件md5
     * @param chunkMd5   分片数据Md5
     * @param chunkIndex 分片索引
     * @param chunkCount 分片总数
     * @return
     */
    Response upload(byte[] data, String fileName, String md5, String chunkMd5, int chunkIndex, int chunkCount);

    /**
     * 上传并写入文件
     *
     * @param file 随机文件
     * @return
     */
    Response uploadAndWrite(RandomFile file);

    /**
     * 上传并写入文件
     *
     * @param file     随机文件
     * @param fileName
     * @return
     */
    Response uploadAndWrite(File file, String fileName);

    /**
     * 上传并写入文件
     *
     * @param file     随机文件
     * @param fileName
     * @param offset
     * @param length
     * @return
     */
    Response uploadAndWrite(File file, String fileName, int offset, int length);

    /**
     * 上传并写入文件
     *
     * @param data
     * @param fileName
     * @return
     */
    Response uploadAndWrite(byte[] data, String fileName);

    /**
     * 上传并写入文件
     *
     * @param data
     * @param fileName
     * @param offset
     * @param length
     * @return
     */
    Response uploadAndWrite(byte[] data, String fileName, int offset, int length);

    /**
     * 上传并写入文件
     *
     * @param data     数据
     * @param fileName 文件名
     * @param skip     跳过字节
     * @return
     */
    Response uploadAndWrite(byte[] data, String fileName, long skip);

    /**
     * 上传并写入文件
     *
     * @param file
     * @param fileName
     * @param skip
     * @return
     */
    Response uploadAndWrite(File file, String fileName, long skip);

    /**
     * 上传并写入文件
     *
     * @param data
     * @param fileName
     * @param offset
     * @param length
     * @param skip
     * @return
     */
    Response uploadAndWrite(byte[] data, String fileName, int offset, int length, long skip);

    /**
     * 上传并写入文件
     *
     * @param file
     * @param fileName
     * @param offset
     * @param length
     * @param skip
     * @return
     */
    Response uploadAndWrite(File file, String fileName, int offset, int length, long skip);

    /**
     * 根据文件名下载文件
     *
     * @param fileName 文件名
     * @param target   保存文件
     * @return
     */
    Response download(String fileName, File target);

    /**
     * 根据文件名下载文件
     *
     * @param fileName 文件名
     * @param filePath 保存文件路径
     * @return
     */
    Response download(String fileName, String filePath);
}
