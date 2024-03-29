package top.luoqiz.common.web.response.vo;

import top.luoqiz.common.web.response.config.BusinessException;

/**
 * rest接口统一返回结果
 *
 * @author luoqiz
 * @date 2021/1/27 16:09
 */
public class RestResponse<T> extends BaseResponse {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 无返回值的成功响应
     */
    public static <T> RestResponse<T> success() {
        return build(ResponseCode.SUCCESS);
    }

    /**
     * 包含返回值的成功相应
     */
    public static <T> RestResponse<T> success(T data) {
        return build(ResponseCode.SUCCESS, data);
    }

    /**
     * 未知异常，返回系统繁忙响应<br/>
     * 谨慎使用，接口尽量返回明确的错误信息
     */
    public static <T> RestResponse<T> fail() {
        return build(ResponseCode.FAIL);
    }

    /**
     * 未知异常，返回系统繁忙响应<br/>
     * 谨慎使用，接口尽量返回明确的错误信息
     */
    public static <T> RestResponse<T> fail(BusinessException businessException) {
        return build(businessException.getCode(), businessException.getMessage());
    }

    /**
     * 未知异常，返回系统繁忙响应
     *
     * @param data 错误信息
     */
    public static <T> RestResponse<T> fail(T data) {
        return build(ResponseCode.FAIL, data);
    }

    /**
     * 包含具体错误信息的响应
     *
     * @param respCode
     */
    public static <T> RestResponse<T> fail(ResponseCode respCode) {
        return build(respCode);
    }

    /**
     * 包含数据的错误响应
     *
     * @param respCode
     * @param data
     */
    public static <T> RestResponse<T> fail(ResponseCode respCode, T data) {
        return build(respCode, data);
    }

    /**
     * 构建自定义响应
     *
     * @param respCode
     */
    public static <T> RestResponse<T> build(ResponseCode respCode) {
        return new RestResponse<>(respCode.getCode(), respCode.getMessage());
    }

    /**
     * 构建带返回数据的响应
     *
     * @param respCode
     * @param data
     */
    public static <T> RestResponse<T> build(ResponseCode respCode, T data) {
        return build(respCode.getCode(), respCode.getMessage(), data);
    }

    /**
     * 构建自定义message内容的相应<br/>
     * 比如统一异常处理中的字段校验未通过异常,消息内容为自定义的字段错误信息
     *
     * @param code    状态码
     * @param message 提示信息
     */
    public static <T> RestResponse<T> build(int code, String message) {
        return new RestResponse<T>(code, message, null);
    }

    /**
     * 构建自定义message内容的相应<br/>
     * 比如统一异常处理中的字段校验未通过异常,消息内容为自定义的字段错误信息
     *
     * @param code    状态码
     * @param message 提示信息
     * @param data    返回的数据
     */
    public static <T> RestResponse<T> build(int code, String message, T data) {
        return new RestResponse<T>(code, message, data);
    }

    public boolean isSuccess() {
        return ResponseCode.SUCCESS.getCode() == super.code;
    }

    private RestResponse() {
    }

    public RestResponse(T data) {
        this.code = 200;
        this.data = data;
    }

    private RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private RestResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}