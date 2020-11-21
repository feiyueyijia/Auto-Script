package com.yfny.yys.script.handler;

import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.util.InvokeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理机制
 * Author auto
 * Date  2020-11-17
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public InvokeResult handException(HttpServletRequest req, Exception e) throws Exception {
        String code = "sys.inner.exception";
        if (e instanceof BusinessException) {
            logger.error("业务异常:" + e.getMessage(), e);
            code = ((BusinessException) e).getCode();
            String[] params = ((BusinessException) e).getParams();
            return InvokeResult.failure(code, params);
        } else {
            logger.error("系统异常:" + e.getMessage(), e);
            return InvokeResult.exception(code, e.getMessage());
        }
    }
}
