package org.dows.file.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.file.api.dto.FileInfo;
import org.dows.file.api.model.FileTransportProtocol;
import org.dows.file.biz.FileConfigBiz;
import org.dows.file.biz.FileInfoBiz;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/12/2022
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@Api(tags = "下载管理")
@RequestMapping("download")
public class DownloadRest {

    private final FileInfoBiz fileInfoBiz;
    private final FileConfigBiz fileConfigBiz;

    /**
     * 根文件信息获取，获取文件传输协议
     *
     * @param fileInfo
     * @return
     */
    @ApiOperation("根文件信息获取，获取文件传输协议")
    public Response getFileTransportProtocol(FileInfo fileInfo) {
        FileTransportProtocol fileTransportProtocol = new FileTransportProtocol();
        // 获取文件传输协议,通知客户端的上传下载策略
        fileTransportProtocol = fileConfigBiz.getTransportProtocol(fileInfo);
        return Response.ok(fileTransportProtocol);
    }


}
