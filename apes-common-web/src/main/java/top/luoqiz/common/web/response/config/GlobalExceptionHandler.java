package top.luoqiz.common.web.response.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.luoqiz.common.utils.ThrowableUtil;
import top.luoqiz.common.web.response.vo.ResponseCode;
import top.luoqiz.common.web.response.vo.RestResponse;

import java.util.Objects;


/**
 * @author luoqiz
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有接口参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public RestResponse handleBindException(BindException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String msg = str[1] + ":参数有误";
        return RestResponse.build(ResponseCode.GLOBAL_PARAM_ERROR.getCode(), msg, message);
    }


    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String msg = str[1] + ":参数有误";
        return RestResponse.build(ResponseCode.GLOBAL_PARAM_ERROR.getCode(), String.format("[%s] %s", msg, message));
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public RestResponse badRequestException(BusinessException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return RestResponse.fail(e);
    }


    /**
     * 处理方法不支持异常
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RestResponse handleException(HttpRequestMethodNotSupportedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return RestResponse.fail(ResponseCode.NOT_SUPPORT_METHOD);
    }

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler({Throwable.class})
    public RestResponse handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return RestResponse.fail(ResponseCode.FAIL);
    }

}
