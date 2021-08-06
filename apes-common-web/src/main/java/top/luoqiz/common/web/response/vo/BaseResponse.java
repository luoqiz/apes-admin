package top.luoqiz.common.web.response.vo;

import java.io.Serializable;

/**
 * 基础返回结果 实体
 *
 * @author luoqiz
 * @date 2021/01/27
 */

public class BaseResponse implements Serializable {

    /**
     * 系统业务代码
     */
    protected int code = ResponseCode.SUCCESS.getCode();
    /**
     * 信息描述
     */
    protected String msg = ResponseCode.SUCCESS.getMessage();

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}