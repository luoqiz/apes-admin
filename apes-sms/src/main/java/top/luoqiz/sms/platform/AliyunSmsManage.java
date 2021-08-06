package top.luoqiz.sms.platform;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import top.luoqiz.sms.platform.aliyun.QuerySendDetails;
import top.luoqiz.sms.platform.aliyun.SendMessageResponse;
import top.luoqiz.sms.platform.aliyun.SendMsgResponseCodeEnum;
import top.luoqiz.sms.platform.aliyun.SmsSendDetailDTO;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luoqiz
 */
@Component
public class AliyunSmsManage implements SmsPlatformManage {
    private static final String accessKeyId = "LTAIveKPRHoTCGmm";
    private static final String accessSecret = "pvYmTWJa7OpYAaVRpQcwlDM4JQousM";
    private static final String signName = "老罗网站";
    private static final String templateCode = "SMS_16740513";
    private static final String domain = "dysmsapi.aliyuncs.com";


    /**
     * 查询某天某手机号的接收信息
     *
     * @param currentPage 当前页码
     * @param pageSize    页大小Max=50
     * @param phoneNumber 手机号
     * @param sendDate    短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
     * @return
     * @throws Exception
     */
    public static QuerySendDetails querySendDetails(Long currentPage, Long pageSize, String phoneNumber, String sendDate) throws Exception {
        String url = getquerySendDetailsReqUrl(currentPage, pageSize, phoneNumber, sendDate);
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(result, JSONObject.class);
        JSONObject smsSendDetailDTOs = jsonObject.getJSONObject("SmsSendDetailDTOs");
        JSONArray list = smsSendDetailDTOs.getJSONArray("SmsSendDetailDTO");
        List<SmsSendDetailDTO> smsSendDetailDTOList = list.toList(SmsSendDetailDTO.class);
        QuerySendDetails details = JSON.parseObject(result, QuerySendDetails.class);
        details.setSmsSendDetailDTOs(smsSendDetailDTOList);
        return details;
    }

    /**
     * 获取阿里云发送短信请求
     *
     * @param phones 手机号(多个用,隔开)
     * @param name
     * @param number
     * @return
     * @throws Exception
     */
    public static String getSendMsgReqUrl(String phones, String name, String number) {
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));// 这里一定要设置GMT时区

        Map<String, String> paras = new java.util.HashMap<String, String>();
        // 1. 系统参数
        paras.put("SignatureMethod", "HMAC-SHA1");
        paras.put("SignatureNonce", java.util.UUID.randomUUID().toString());
        paras.put("AccessKeyId", accessKeyId);
        paras.put("SignatureVersion", "1.0");
        paras.put("Timestamp", df.format(new java.util.Date()));
        paras.put("Format", "json");

        // 2. 业务API参数
        paras.put("Action", "SendSms");
        paras.put("Version", "2017-05-25");
        paras.put("RegionId", "cn-hangzhou");
        paras.put("PhoneNumbers", phones);
        paras.put("SignName", signName);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        map.put("number", number);
        String sendMsg = JSONUtil.parse(map).toString();
        paras.put("TemplateParam", sendMsg);
        paras.put("TemplateCode", templateCode);
//        paras.put("OutId", "123");

        // 3. 去除签名关键字Key
        if (paras.containsKey("Signature")) {
            paras.remove("Signature");
        }
        // 4. 参数KEY排序
        java.util.TreeMap<String, String> sortParas = new java.util.TreeMap<String, String>();
        sortParas.putAll(paras);

        // 5. 构造待签名的字符串
        java.util.Iterator<String> it = sortParas.keySet().iterator();
        StringBuilder sortQueryStringTmp = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key)));
        }
        String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号

        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append("GET").append("&");
        stringToSign.append(specialUrlEncode("/")).append("&");
        stringToSign.append(specialUrlEncode(sortedQueryString));

        String sign = sign(accessSecret + "&", stringToSign.toString());
        // 6. 签名最后也要做特殊URL编码
        String signature = specialUrlEncode(sign);

//        System.out.println(paras.get("SignatureNonce"));
//        System.out.println("\r\n=========\r\n");
//        System.out.println(paras.get("Timestamp"));
//        System.out.println("\r\n=========\r\n");
//        System.out.println(sortedQueryString);
//        System.out.println("\r\n=========\r\n");
//        System.out.println(stringToSign.toString());
//        System.out.println("\r\n=========\r\n");
//        System.out.println(sign);
//        System.out.println("\r\n=========\r\n");
//        System.out.println(signature);
//        System.out.println("\r\n=========\r\n");
        // 最终打印出合法GET请求的URL
//        System.out.println("http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp);
        String url = "http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp;
        return url;
    }

    /**
     * 获取查询发送记录请求
     *
     * @param currentPage 当前页
     * @param pageSize    每页显示数量
     * @param phoneNumber 接收短信手机号
     * @param sendDate    短信发送日期（yyyyMMdd）
     * @return
     * @throws Exception
     */
    public static String getquerySendDetailsReqUrl(Long currentPage, Long pageSize, String phoneNumber, String sendDate) throws Exception {
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        // 这里一定要设置GMT时区
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));

        Map<String, Object> paras = new java.util.HashMap<>();
        // 1. 系统参数
        paras.put("SignatureMethod", "HMAC-SHA1");
        paras.put("SignatureNonce", java.util.UUID.randomUUID().toString());
        paras.put("AccessKeyId", accessKeyId);
        paras.put("SignatureVersion", "1.0");
        paras.put("Timestamp", df.format(new java.util.Date()));
        paras.put("Format", "json");

        // 2. 业务API参数
        paras.put("Action", "QuerySendDetails");
        paras.put("Version", "2017-05-25");
        paras.put("CurrentPage", currentPage);
        paras.put("PageSize", pageSize);
        paras.put("PhoneNumber", phoneNumber);
        paras.put("SendDate", sendDate);

        // 3. 去除签名关键字Key
        if (paras.containsKey("Signature")) {
            paras.remove("Signature");
        }
        // 4. 参数KEY排序
        java.util.TreeMap<String, Object> sortParas = new java.util.TreeMap<>();
        sortParas.putAll(paras);

        // 5. 构造待签名的字符串
        java.util.Iterator<String> it = sortParas.keySet().iterator();
        StringBuilder sortQueryStringTmp = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key).toString()));
        }
        // 去除第一个多余的&符号
        String sortedQueryString = sortQueryStringTmp.substring(1);

        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append("GET").append("&");
        stringToSign.append(specialUrlEncode("/")).append("&");
        stringToSign.append(specialUrlEncode(sortedQueryString));

        String sign = sign(accessSecret + "&", stringToSign.toString());
        // 6. 签名最后也要做特殊URL编码
        String signature = specialUrlEncode(sign);

        // 最终打印出合法GET请求的URL
//        System.out.println("http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp);
        String url = "http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp;
        return url;
    }

    public static String specialUrlEncode(String value) {
        return java.net.URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    public static String sign(String accessSecret, String stringToSign) {
        javax.crypto.Mac mac = null;
        try {
            mac = javax.crypto.Mac.getInstance("HmacSHA1");
            mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA1"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signData);
    }

    @Override
    public boolean support(String platform) {
        return SmsPlatformEnum.ALI.equals(SmsPlatformEnum.valueOf(platform));
    }

    /**
     * 发送短信
     *
     * @param phone    手机号
     * @param username 模板参数
     * @param code     模板参数
     * @return
     * @throws Exception
     */
    @Override
    public String sendMsg(String phone, String username, String code) {
        if (StringUtils.isBlank(username)) {
            username = phone;
        }
        String reqUrl = getSendMsgReqUrl(phone, username, code);
        String result = HttpUtil.get(reqUrl);
        SendMessageResponse sendMessageResponse = JSON.parseObject(result, SendMessageResponse.class);
        SendMsgResponseCodeEnum resCode = SendMsgResponseCodeEnum.getCode(sendMessageResponse.getCode());
        return resCode.message();
    }

    @Override
    public SendMsgResponseCodeEnum sendMsg(String phone, String code) {
        String reqUrl = getSendMsgReqUrl(phone, phone, code);
        String result = HttpUtil.get(reqUrl);
        SendMessageResponse sendMessageResponse = JSON.parseObject(result, SendMessageResponse.class);
        return SendMsgResponseCodeEnum.getCode(sendMessageResponse.getCode());
    }

}
