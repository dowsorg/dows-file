package org.dows.file.rest;

import io.swagger.annotations.Api;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lait.zhang@gmail.com
 * @description: TODO
 * @weixin SH330786
 * @date 3/14/2022
 */
@RestController
@Api("上传管理")
@RequestMapping("/upload")
public class UploadRest {

    public Response testUpload(HttpServletRequest httpServletRequest) {

        return Response.ok();
    }

}
