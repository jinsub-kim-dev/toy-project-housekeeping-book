package com.jinsub.housekeeping.base.exception;

import com.jinsub.housekeeping.base.exception.enums.HouseKeepingErrorType;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import com.jinsub.housekeeping.base.model.CodeResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public CodeResponse handleException(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Exception exception) {

        HouseKeepingErrorType errorType;

        if (exception instanceof HouseKeepingException) {
            errorType = ((HouseKeepingException) exception).getErrorType();
        } else {
            errorType = HouseKeepingErrorType.UNKOWN;
        }

        return CodeResponse.failCodeMessage(errorType.getErrorCode(), errorType.getMessage());
    }
}
