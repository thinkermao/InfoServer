package net.hashcoding.code.scuinfo.Handler;

import net.hashcoding.code.scuinfo.Result;
import net.hashcoding.code.scuinfo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public Result<String> defaultHandler(Exception e) {
        logger.error("internal server error ---->", e);
        return Result.error(ResultCode.INTERNAL_ERROR, e);
    }

    @ExceptionHandler(value = MessageException.class)
    public Result<String> defaultHandler(MessageException e) {
        logger.error("error with message ----->", e);
        return Result.error(e.getCode(), e.getDetailMessage());
    }
}
