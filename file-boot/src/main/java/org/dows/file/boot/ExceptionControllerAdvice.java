package org.dows.file.boot;

import org.dows.file.api.constants.FileStatusCode;
import org.dows.file.api.exception.FileException;
import org.dows.framework.api.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = FileException.class)
    public ResponseEntity handleFileServerException(FileException ex) {
        ex.printStackTrace();
        return ResponseEntity.ok(Response.failed(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.ok(Response.failed(FileStatusCode.SERVER_ERROR));
    }
}
