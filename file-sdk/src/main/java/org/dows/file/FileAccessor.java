package org.dows.file;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.dows.file.api.dto.FileInfo;

import java.io.*;
import java.net.URLDecoder;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/13/2022
 */
public class FileAccessor {

    // 每片文件大小  50MB
    protected long PER_PAGE = 1024l * 1024l * 50l;
    protected String DOWNPATH = "F:\\fileItem";
    private long start;
    private long end;
    private long page;
    private String fileName;


    public FileAccessor(long start, long end, long page, String fileName) {
        this.start = start;
        this.end = end;
        this.page = page;
        this.fileName = fileName;
    }


    /**
     * 文件大小  文件名字
     * 探测 获取文件信息
     * 使用多线程分片下载
     * 最后一个分片下载玩 开始合并
     * 结束位置 - 开始位置 = 分片大小
     *
     * @return
     * @throws Exception
     */
    private FileInfo read(long start, long end, long page, String fName) throws Exception {
        File file = new File(DOWNPATH, page + "-" + fName);
        if (file.exists() && page != -1 && file.length() == PER_PAGE) {
            return null;
        }
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/download");
        httpGet.setHeader("Range", "bytes=" + start + "-" + end);

        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();

        String fSize = response.getFirstHeader("fileSize").getValue();
        fName = URLDecoder.decode(response.getFirstHeader("fileName").getValue(), "utf-8");

        FileOutputStream os = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int ch = 0;
        while ((ch = is.read(buffer)) != -1) {
            os.write(buffer, 0, ch);
        }
        is.close();
        os.flush();
        os.close();

        if (end - Long.parseLong(fSize) >= 0) {//最后一个分片
            mergeFile(fName, page);
        }
        return new FileInfo();
    }

    /**
     * @param fName
     * @param page
     * @throws IOException
     * @throws InterruptedException
     */
    protected void mergeFile(String fName, long page) throws IOException, InterruptedException {
        File file = new File(DOWNPATH, fName);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));

        for (int i = 0; i <= page; i++) {
            File tempFile = new File(DOWNPATH, i + "-" + fName);
            while (!tempFile.exists() || (i != page && tempFile.length() < PER_PAGE)) {
                Thread.sleep(100);
            }
            byte[] bytes = FileUtils.readFileToByteArray(tempFile);
            os.write(bytes);
            os.flush();
            //删除零时文件
            tempFile.delete();
        }
        File nullFile = new File(DOWNPATH, -1 + "-null");
        nullFile.delete();
        os.flush();
        os.close();
        //文件子节计算导致文件不完整
        //流未关闭
    }

}
