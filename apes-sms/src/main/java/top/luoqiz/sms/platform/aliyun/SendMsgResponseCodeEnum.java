package top.luoqiz.sms.platform.aliyun;

public enum SendMsgResponseCodeEnum {
    OK("OK", "短信发送成功"),
    SMS_SIGNATURE_SCENE_ILLEGAL("SMS_SIGNATURE_SCENE_ILLEGAL", "短信所使用签名场景非法"),
    EXTEND_CODE_ERROR("EXTEND_CODE_ERROR", "扩展码使用错误，相同的扩展码不可用于多个签名"),
    DOMESTIC_NUMBER_NOT_SUPPORTED("DOMESTIC_NUMBER_NOT_SUPPORTED", "国际/港澳台消息模板不支持发送境内号码"),
    DENY_IP_RANGE("DENY_IP_RANGE", "源IP地址所在的地区被禁用"),
    DAY_LIMIT_CONTROL("DAY_LIMIT_CONTROL", "触发日发送限额"),
    SMS_CONTENT_ILLEGAL("SMS_CONTENT_ILLEGAL", "短信内容包含禁止发送内容"),
    SMS_SIGN_ILLEGAL("SMS_SIGN_ILLEGAL", "签名禁止使用"),
    RAM_PERMISSION_DENY("RAM_PERMISSION_DENY", "RAM权限DENY"),
    OUT_OF_SERVICE("OUT_OF_SERVICE", "业务停机,余额不足"),
    PRODUCT_UN_SUBSCRIPT("PRODUCT_UN_SUBSCRIPT", "未开通云通信产品的阿里云客户"),
    PRODUCT_UNSUBSCRIBE("PRODUCT_UNSUBSCRIBE", "产品未开通"),
    ACCOUNT_NOT_EXISTS("ACCOUNT_NOT_EXISTS", "账户不存在"),
    ACCOUNT_ABNORMAL("ACCOUNT_ABNORMAL", "账户异常"),
    SMS_TEMPLATE_ILLEGAL("SMS_TEMPLATE_ILLEGAL", "短信模版不合法,短信模板不存在，或未经审核通过"),
    SMS_SIGNATURE_ILLEGAL("SMS_SIGNATURE_ILLEGAL", "短信签名不合法,签名不存在，或未经审核通过"),
    INVALID_PARAMETERS("INVALID_PARAMETERS", "参数异常,参数格式不正确"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统错误"),
    MOBILE_NUMBER_ILLEGAL("MOBILE_NUMBER_ILLEGAL", "非法手机号"),
    MOBILE_COUNT_OVER_LIMIT("MOBILE_COUNT_OVER_LIMIT", "手机号码数量超过限制,请将手机号码数量限制在1000个以内"),
    TEMPLATE_MISSING_PARAMETERS("TEMPLATE_MISSING_PARAMETERS", "模版缺少变量,参数TemplateParam中，变量未全部赋值"),
    BUSINESS_LIMIT_CONTROL("BUSINESS_LIMIT_CONTROL", "短信发送频率超限"),
    INVALID_JSON_PARAM("INVALID_JSON_PARAM", "JSON参数不合法，只接受字符串值"),
    BLACK_KEY_CONTROL_LIMIT("BLACK_KEY_CONTROL_LIMIT", "黑名单管控"),
    PARAM_LENGTH_LIMIT("PARAM_LENGTH_LIMIT", "参数超出长度限制"),
    PARAM_NOT_SUPPORT_URL("PARAM_NOT_SUPPORT_URL", "不支持URL"),
    AMOUNT_NOT_ENOUGH("AMOUNT_NOT_ENOUGH", "账户余额不足"),
    TEMPLATE_PARAMS_ILLEGAL("TEMPLATE_PARAMS_ILLEGAL", "模版变量里包含非法关键字"),
    SignatureDoesNotMatch("SignatureDoesNotMatch", "签名（Signature）加密错误"),
    InvalidTimeStamp("InvalidTimeStamp.Expired", "般由于时区差异造成时间戳错误，发出请求的时间和服务器接收到请求的时间不在15分钟内,请使用GMT时间"),
    SignatureNonceUsed("SignatureNonceUsed", "唯一随机数重复，SignatureNonce为唯一随机数，用于防止网络重放攻击"),
    InvalidVersion("InvalidVersion", "版本号（Version）错误"),
    InvalidAction("InvalidAction.NotFound", "参数Action中指定的接口名错误"),
    SIGN_COUNT_OVER_LIMIT("SIGN_COUNT_OVER_LIMIT", "一个自然日中申请签名数量超过限制"),
    TEMPLATE_COUNT_OVER_LIMIT("TEMPLATE_COUNT_OVER_LIMIT", "一个自然日中申请模板数量超过限制"),
    SIGN_NAME_ILLEGAL("SIGN_NAME_ILLEGAL", "签名名称不符合规范"),
    SIGN_FILE_LIMIT("SIGN_FILE_LIMIT", "签名认证材料附件大小超过限制"),
    SIGN_OVER_LIMIT("SIGN_OVER_LIMIT", "签名的名称或申请说明的字数超过限制"),
    TEMPLATE_OVER_LIMIT("TEMPLATE_OVER_LIMIT", "模板的名称、内容或申请说明的字数超过限制"),
    SIGNATURE_BLACKLIST("SIGNATURE_BLACKLIST", "签名黑名单"),
    SHORTURL_OVER_LIMIT("SHORTURL_OVER_LIMIT", "一天创建短链数量超过限制"),
    NO_AVAILABLE_SHORT_URL("NO_AVAILABLE_SHORT_URL", "无有效短链"),
    SHORTURL_NAME_ILLEGAL("SHORTURL_NAME_ILLEGAL", "短链名称非法"),
    SOURCEURL_OVER_LIMIT("SOURCEURL_OVER_LIMIT", "原始链接字符数量超过限制"),
    SHORTURL_TIME_ILLEGAL("SHORTURL_TIME_ILLEGAL", "短链有效期期限超过限制"),
    PHONENUMBERS_OVER_LIMIT("PHONENUMBERS_OVER_LIMIT", "上传手机号个数超过上限"),
    SHORTURL_STILL_AVAILABLE("SHORTURL_STILL_AVAILABLE", "原始链接生成的短链仍在有效期内"),
    SHORTURL_NOT_FOUND("SHORTURL_NOT_FOUND", "没有可删除的短链"),
    ERROR_SIGN_NOT_MODIFY("ERROR_SIGN_NOT_MODIFY", "签名不支持修改");

    private String code;
    private String message;

    SendMsgResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public static SendMsgResponseCodeEnum getCode(String code) {
//         Arrays.stream(SendMsgResponseCodeEnum.values()).filter(value-> value.code().equalsIgnoreCase(code)).findFirst();

        for (SendMsgResponseCodeEnum msg : SendMsgResponseCodeEnum.values()) {
            if(msg.code().equalsIgnoreCase(code)){
                return msg;
            }
        }
        return null;
    }

    public String message() {
        return message;
    }

    public static String getMessage(String code) {
        for (SendMsgResponseCodeEnum msg : SendMsgResponseCodeEnum.values()) {
            if(msg.code().equalsIgnoreCase(code)){
                return msg.message;
            }
        }
        return null;
    }
}
