package top.luoqiz.common.web.response.config;

import top.luoqiz.common.web.response.vo.ResponseCode;

/**
 * <p>Title: BusinessException</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 业务异常
 * @date 2021/2/26 15:38
 * @since 1.0
 */
public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException(ResponseCode responseCode, Throwable throwable) {
        super(responseCode.getMessage(), throwable);
        this.code = responseCode.getCode();
    }

    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = ResponseCode.FAIL.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
