package top.luoqiz.sms.platform.aliyun;

import java.util.List;

public class QuerySendDetails {
    /**
     * 请求状态码
     */
    private String Code;

    // 状态码的描述
    private String Message;

    // 请求ID
    private String RequestId;

    // 短信发送明细
    private List<SmsSendDetailDTO> SmsSendDetailDTOs;

    // 短信发送总条数
    private String TotalCount;

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

    public List<SmsSendDetailDTO> getSmsSendDetailDTOs() {
        return SmsSendDetailDTOs;
    }

    public void setSmsSendDetailDTOs(List<SmsSendDetailDTO> smsSendDetailDTOs) {
        SmsSendDetailDTOs = smsSendDetailDTOs;
    }

    public String getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(String totalCount) {
        TotalCount = totalCount;
    }

    @Override
    public String toString() {
        return "QuerySendDetails{" +
                "Code='" + Code + '\'' +
                ", Message='" + Message + '\'' +
                ", RequestId='" + RequestId + '\'' +
                ", SmsSendDetailDTOs=" + SmsSendDetailDTOs +
                ", TotalCount='" + TotalCount + '\'' +
                '}';
    }
}
