package org.dows.file.impl;

import lombok.extern.slf4j.Slf4j;
import org.dows.file.Uploader;
import org.dows.file.api.dto.UpFileDTO;
import org.dows.file.api.model.FileTransportProtocol;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/13/2022
 */
@Slf4j
@Component
public class ShardingUploader implements Uploader {

    private static final Map<String, UpFileDTO> fileMap = new ConcurrentHashMap<>();
    private final String tmpPath = "D:\\data\\fileTmp";
    private ExecutorService executorService;
    @Autowired
    private RestTemplate restTemplate;

    public static void sliceFile(File file, int size, BiConsumer<Long, byte[]> consumer) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        long length = file.length();
        long count = length / size;
        if (file.length() % count != 0) {
            count++;
        }
        long sum = 0;
        for (long i = 0; i < count; i++) {
            try {
                byte[] bytes;
                if (i + 1 == count) {
                    bytes = new byte[(int) (length - sum)];
                    randomAccessFile.read(bytes, 0, bytes.length);
                } else {
                    bytes = new byte[size];
                    sum += size;
                    randomAccessFile.read(bytes, 0, size);
                    randomAccessFile.seek(sum);
                }
                consumer.accept(i, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public UpFileDTO setFileSliceTask(File file) throws IOException {
        FileTransportProtocol fileTransportProtocol = new FileTransportProtocol();
        Path path = Paths.get(tmpPath);
        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long fileSize = file.length();
        String fileName = file.getName();
        String fileMd5 = DigestUtils.md5DigestAsHex((new FileInputStream(file)));
        UpFileDTO upFileDTO = buildUpFile(new FileTransportProtocol());
        long sliceNum = fileSize / upFileDTO.getSize();
        if (fileSize % upFileDTO.getSize() != 0) {
            sliceNum += 1;
        }
        upFileDTO.setFileMd5(fileMd5);
        upFileDTO.setTaskId(UUID.randomUUID().toString());
        upFileDTO.setFileName(fileName);
        upFileDTO.setFileSize(fileSize);
        upFileDTO.setSliceNum(sliceNum);
        fileMap.put(upFileDTO.getTaskId(), upFileDTO);
        return upFileDTO;
    }

    public void uploadFile(UpFileDTO upFileDTO, byte[] bytes) throws IOException {

        String tmpPath0 = tmpPath + upFileDTO.getTaskId() + "/";
        Path path = Paths.get(tmpPath0);
        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String tmpFileName = tmpPath0 + upFileDTO.getSliceNo() + ".tmp";
        try {

            FileCopyUtils.copy(bytes, new File(tmpFileName));
            mergeUpFile(upFileDTO.getTaskId());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException("文件上传失败");
        }
    }

    public void mergeUpFile(String taskId) {
        UpFileDTO UpFileDTO = fileMap.get(taskId);
        if (UpFileDTO == null) return;
        try {
            byte[] reduce = Files.list(Paths.get(tmpPath + "/" + taskId + "/")).sorted((s1, s2) -> {
                        String[] split = s1.toString().split("\\\\");
                        String[] split2 = s2.toString().split("\\\\");
                        String[] split1 = split[split.length - 1].split("\\.");
                        String[] split3 = split2[split2.length - 1].split("\\.");
                        return Long.compare(Long.parseLong(split1[0]), (Long.parseLong(split3[0])));
                    })
                    .map(this::readByteByPath).filter(Objects::nonNull)
                    .reduce(new byte[]{}, this::addBytes);
            String s = DigestUtils.md5DigestAsHex(reduce);
            if (!UpFileDTO.getFileMd5().equals(s)) {
                throw new BizException("MD5校验错误");
            }
            FileCopyUtils.copy(reduce, new File(tmpPath + "/" + taskId + UpFileDTO.getFileName()));
            deleteDir(tmpPath + taskId);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteDir(String path) {
        try {
            Files.list(Paths.get(path))
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] readByteByPath(Path i) {
        try {
            return Files.readAllBytes(i);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }

    public UpFileDTO buildUpFile(FileTransportProtocol transportProtocol) {
        return UpFileDTO.builder().build(); //根据设置协议进行处理
    }

    public void upload() throws IOException, InterruptedException {
        File upfile = new File("01.png");
        FileTransportProtocol fileTransportProtocol = new FileTransportProtocol();
        UpFileDTO upFileDTO = setFileSliceTask(upfile);


        sliceFile(upfile, upFileDTO.getSize(), (no, bytes) -> {
            try {
                uploadFile(upFileDTO, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
