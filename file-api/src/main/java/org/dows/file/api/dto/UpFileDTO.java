package org.dows.file.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :yangxh
 * @Title: UpFileDTO
 * @ProjectName it-cop
 * @Description: TODO
 * @date 2022/3/1518:21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpFileDTO {
    private String taskId; // 上传文件ID
    private Long sliceNum; // 分片次数
    private Integer size = 1024; // 每片大小 字节
    private String fileMd5; // 文件MD5值
    private String fileName; // 文件名称
    private Long fileSize; // 文件大小

    private String transportProtocolId; //系统设置参数
    private String sliceNo;


}
