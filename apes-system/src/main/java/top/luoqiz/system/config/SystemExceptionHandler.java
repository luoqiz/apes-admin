package top.luoqiz.system.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.luoqiz.common.utils.ThrowableUtil;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.common.web.response.vo.RestResponse;


/**
 * @author luoqiz
 */
@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class SystemExceptionHandler {
    static Logger log = LoggerFactory.getLogger(SystemExceptionHandler.class);

    /**
     * 处理自定义异常
     */
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public RestResponse badRequestException(AccessDeniedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return RestResponse.fail(ResponseCode.NO_AUTHENTICATION);
    }


}
