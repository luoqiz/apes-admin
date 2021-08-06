package top.luoqiz.sms.platform.aliyun;

public class SendMessageResponse {
    // 发送回执ID
    private String BizId;

    // 请求状态码
    private String Code;

    // 状态码的描述
    private String Message;

    // 请求ID
    private String RequestId;

    public String getBizId() {
        return BizId;
    }

    public void setBizId(String bizId) {
        BizId = bizId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    @Override
    public String toString() {
        return "SendMessageResponse{" +
                "BizId='" + BizId + '\'' +
                ", Code='" + Code + '\'' +
                ", Message='" + Message + '\'' +
                ", RequestId='" + RequestId + '\'' +
                '}';
    }
}
