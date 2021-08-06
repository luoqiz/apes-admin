package top.luoqiz.sms.platform.aliyun;

public class SendMessageRequest {
    // 请求签名
    private String Signature;

    // 访问密钥 ID
    private String AccessKeyId;
    // API 的名称
    private String Action;
    // 返回参数的语言类型。取值范围：json | xml。默认值：json
    private String Format;
    // API支持的RegionID，如短信API的值为：cn-hangzhou
    private String RegionId;
    // 签名方式。取值范围：HMAC-SHA1
    private String SignatureMethod;
    // 签名唯一随机数。用于防止网络重放攻击，建议您每一次请求都使用不同的随机数
    private String SignatureNonce;
    // 签名算法版本。取值范围：1.0
    private String SignatureVersion;
    // 请求的时间戳。按照ISO8601 标准表示，并需要使用UTC时间，格式为yyyy-MM-ddTHH:mm:ssZ
    private String Timestamp;
    // API 的版本号，格式为 YYYY-MM-DD。取值范围：2017-05-25
    private String Version;
}
