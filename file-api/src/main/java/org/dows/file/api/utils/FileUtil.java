package org.dows.file.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.dows.file.api.dto.ChunkInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.*;

@Slf4j
public final class FileUtil {

    public static String generatePath(String uploadFolder, ChunkInfo chunk) {
        StringBuilder sb = new StringBuilder();
        sb.append(uploadFolder).append("/").append(chunk.getIdentifier());
        //判断uploadFolder/identifier 路径是否存在，不存在则创建
        if (!Files.isWritable(Paths.get(sb.toString()))) {
            log.info("path not exist,create path: {}", sb.toString());
            try {
                Files.createDirectories(Paths.get(sb.toString()));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

        return sb.append("/")
                .append(chunk.getFilename())
                .append("-")
                .append(chunk.getChunkNumber()).toString();
    }

    /**
     * 文件合并
     *
     * @param targetFile
     * @param folder
     */
    public static String merge(String file, String folder, String filename) {
        //默认合并成功
        String rlt = "200";

        try {
            //先判断文件是否存在
            if (fileExists(file)) {
                //文件已存在
                rlt = "300";
            } else {
                //不存在的话，进行合并
                Files.createFile(Paths.get(file));

                Files.list(Paths.get(folder))
                        .filter(path -> !path.getFileName().toString().equals(filename))
                        .sorted((o1, o2) -> {
                            String p1 = o1.getFileName().toString();
                            String p2 = o2.getFileName().toString();
                            int i1 = p1.lastIndexOf("-");
                            int i2 = p2.lastIndexOf("-");
                            return Integer.valueOf(p2.substring(i2)).compareTo(Integer.valueOf(p1.substring(i1)));
                        })
                        .forEach(path -> {
                            try {
                                //以追加的形式写入文件
                                Files.write(Paths.get(file), Files.readAllBytes(path), StandardOpenOption.APPEND);
                                //合并后删除该块
                                Files.delete(path);
                            } catch (IOException e) {
                                log.error(e.getMessage(), e);
                            }
                        });
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            //合并失败
            rlt = "400";
        }

        return rlt;
    }

    /**
     * 根据文件的全路径名判断文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean fileExists(String file) {
        boolean fileExists = false;
        Path path = Paths.get(file);
        fileExists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        return fileExists;
    }

    /**
     * 构建目录路径
     *
     * @param paths
     * @return
     */
    public static String buildPath(String... paths) {
        StringBuilder sb = new StringBuilder();
        for (String path : paths) {
            sb.append(path).append(File.separator);
        }
        return sb.toString();
    }

    /**
     * 分块读取文件
     *
     * @param file
     * @param chunkIndex 分片索引
     * @param chunkCount 分片总大小
     * @param chunkSize  分片大小
     * @return
     */
    public static byte[] readChunk(File file, int chunkIndex, int chunkCount, int chunkSize) {
        assert chunkCount > 0;
        int offset = (chunkIndex - 1) * chunkSize;
        int len = chunkSize;
        if (chunkIndex == chunkCount) {
            len = (int) file.length() - offset;
        }
        byte[] buf = new byte[len];
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            raf.seek(offset);
            raf.read(buf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(raf);
        }
        return buf;
    }

    public static byte[] readBytes(File file, int offset, int length) {
        byte[] buf = new byte[length];
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            raf.seek(offset);
            raf.read(buf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(raf);
        }
        return buf;
    }

    /**
     * 从一个byte[]数组中截取一部分
     *
     * @param data
     * @param offset
     * @param length
     * @return
     */
    public static byte[] subBytes(byte[] data, int offset, int length) {
        byte[] buffer = new byte[length];
        for (int i = offset; i < offset + length; i++) buffer[i - offset] = data[i];
        return buffer;
    }

    /**
     * 在文件任意位置插入数据
     *
     * @param file
     * @param skip
     * @param data
     * @throws IOException
     */
    public static void insert(File file, long skip, byte[] data) {
        RandomAccessFile raf = null;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        File tmp = null;
        try {
            tmp = File.createTempFile("tmp", null, file.getParentFile());
            raf = new RandomAccessFile(file, "rw");
            tmpOut = new FileOutputStream(tmp);
            tmpIn = new FileInputStream(tmp);
            raf.seek(skip);
            byte[] buf = new byte[1024];
            int hasRead = 0;
            while ((hasRead = raf.read(buf)) > 0) {
                tmpOut.write(buf, 0, hasRead);
            }

            raf.seek(skip);
            raf.write(data);

            while ((hasRead = tmpIn.read(buf)) > 0) {
                raf.write(buf, 0, hasRead);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(raf);
            IOUtils.closeQuietly(tmpIn);
            IOUtils.closeQuietly(tmpOut);
            tmp.delete();
        }
    }

    /**
     * 文件下载时用于写文件头部信息
     *
     * @param request  http请求
     * @param response http响应
     * @param fileName 文件名
     */
    public static void setFileDownloadHeader(HttpServletRequest request,
                                             HttpServletResponse response, String fileName) {
        try {
            String encodedFileName = null;
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE")) {
                encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
                encodedFileName = new String(fileName.getBytes("UTF-8"),
                        "iso-8859-1");
            } else {
                encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            }

            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + encodedFileName + "\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
