package org.dows.file.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.file.api.dto.FileInfo;
import org.dows.file.biz.FileBiz;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/13/2022
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@Api(tags = "文件管理")
public class FileRest {

    private final FileBiz fileBiz;

    @GetMapping("/searchFile")
    @ApiOperation("查询文件")
    public Response SearchFile(HttpServletRequest request) {
        return Response.ok();
    }


    /**
     * 查询列表
     *
     * @param folderNo
     * @return
     */
    @GetMapping("/fileList")
    @ApiOperation("查询列表")
    public Response fileList(String folderNo) {
        // todo biz
        return Response.ok();
    }

    /**
     * 删除
     *
     * @param fileInfo
     * @return
     */
    @DeleteMapping("/deleteFile")
    @ApiOperation("删除")
    public Response deleteFile(@RequestBody FileInfo fileInfo) {
        fileBiz.delFile(fileInfo);
        return Response.ok();
    }
}
