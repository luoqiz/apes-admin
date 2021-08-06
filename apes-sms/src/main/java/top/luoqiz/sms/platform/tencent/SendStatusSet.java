package top.luoqiz.sms.platform.tencent;

public class SendStatusSet {
    // 短信请求错误码描述
    private String message;

    private String phoneNumber;

    // 发送流水号
    private String serialNo;

    // 计费条数
    private Integer fee;

    // 用户Session内容
    private String sessionContext;

    // 短信请求错误码
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(String sessionContext) {
        this.sessionContext = sessionContext;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SendStatusSet{" +
                "message='" + message + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", fee=" + fee +
                ", sessionContext='" + sessionContext + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
