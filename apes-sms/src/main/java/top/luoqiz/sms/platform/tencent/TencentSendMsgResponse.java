package top.luoqiz.sms.platform.tencent;

import java.util.List;

public class TencentSendMsgResponse {
    private String RequestId;

    private List<SendStatusSet> sendStatusSet;

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public List<SendStatusSet> getSendStatusSet() {
        return sendStatusSet;
    }

    public void setSendStatusSet(List<SendStatusSet> sendStatusSet) {
        this.sendStatusSet = sendStatusSet;
    }

    @Override
    public String toString() {
        return "TencentSendMsgResponse{" +
                "RequestId='" + RequestId + '\'' +
                ", sendStatusSet=" + sendStatusSet +
                '}';
    }
}
