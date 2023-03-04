package org.dows.file.client;

import org.dows.file.api.FileAccess;
import org.dows.file.client.adapter.FileAccessAdapter;


/**
 * @author lait.zhang@gmail.com
 * @description: 文件上传客户端
 * @weixin SH330786
 * @date 3/12/2022
 */
public class FileClient extends FileAccessAdapter {
    private static FileClient instance = null;

    public FileClient(FileAccess fileAccess) {
        super(fileAccess);
    }

    private FileClient() {
    }

    public static FileClient build(FileAccess fileAccess) {
        // 两层判空，第一层是为了避免不必要的同步
        // 第二层是为了在null的情况下创建实例
        if (instance == null) {
            synchronized (FileClient.class) {
                if (instance == null) {
                    instance = new FileClient(fileAccess);
                }
            }
        }
        return instance;
    }

    public static FileClient getInstance() {
        return FileClientHolder.instance;
    }

    /**
     * 静态内部类
     */
    private static class FileClientHolder {
        private static FileClient instance = new FileClient();
    }

}
