package top.luoqiz.sms.platform;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import top.luoqiz.sms.platform.aliyun.SendMsgResponseCodeEnum;
import top.luoqiz.sms.platform.tencent.TencentSendMsgResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luoqiz
 */
@Component
public class TencentSmsManage implements SmsPlatformManage {
    private static final String secretId = "AKIDmLgwQhWDMHzT3ad9qIUQ7ZZJq1tWKpc6";
    private static final String secretKey = "OYIYYR3wQdZGta090ysWtynMgjqgMluv";
    //    private static final String appid = "1304936149";
    private static final String appid = "1400495444";
    private static final String sign = "罗强争知识库";
    private static final String templateID = "895688";
//    private static final String templateCode = "1";

    @Override
    public boolean support(String platform) {
        return SmsPlatformEnum.TENG_XUN.equals(SmsPlatformEnum.valueOf(platform));
    }

    @Override
    public String sendMsg(String phone, String username, String code) {
        return null;
    }

    /**
     * 发送短信
     *
     * @param phones
     * @return
     */
    @Override
    public SendMsgResponseCodeEnum sendMsg(String phones, String code) {
        SendSmsResponse res = null;
        try {
            SmsClient client = createSmsClient();
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 你可以直接查询SDK源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用IDE进行开发，可以方便的跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();

            /* 填充请求参数,这里request对象的成员变量即对应接口的入参
             * 你可以通过官网接口文档或跳转到request对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台: https://console.cloud.tencent.com/sms/smslist
             * sms helper: https://cloud.tencent.com/document/product/382/3773 */

            /* 短信应用ID: 短信SdkAppid在 [短信控制台] 添加应用后生成的实际SdkAppid，示例如1400006666 */
            req.setSmsSdkAppid(appid);

            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
            req.setSign(sign);

            /* 国际/港澳台短信 senderid: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
//            String senderid = "xxx";
//            req.setSenderId(senderid);

            /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
//            String session = "xxx";
//            req.setSessionContext(session);

            /* 短信码号扩展号: 默认未开通，如需开通请联系 [sms helper] */
//            String extendcode = "xxx";
//            req.setExtendCode(extendcode);

            /* 模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看 */
//            String templateID = Constants.SMSTEMPLATEID;
            req.setTemplateID(templateID);

            /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
             * 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
//            String[] phoneNumbers = {"+8621212313123", "+8612345678902", "+8612345678903"};
            String[] split = phones.split(",");
            String phoneNum = "";
            for (int i = 0; i < split.length; i++) {
                if (i == split.length - 1) {
                    phoneNum += "+86" + split[i];
                } else {
                    phoneNum += "+86" + split[i] + ",";
                }
            }
            String[] phoneNumbers = phoneNum.split(",");

            req.setPhoneNumberSet(phoneNumbers);
            /* 模板参数: 若无模板参数，则设置为空*/
            String[] templateParams = {code};
            req.setTemplateParamSet(templateParams);
            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            res = client.SendSms(req);
            TencentSendMsgResponse response = new TencentSendMsgResponse();
            BeanUtils.copyProperties(res, response);
            // 输出 JSON 格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));
            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return SendMsgResponseCodeEnum.OK;
    }

    /**
     * 拉取短信下发状态
     *
     * @param limit 设置拉取最大条数，最多100条
     */
    public static PullSmsSendStatusByPhoneNumberResponse pullSmsSendStatus(Long limit, String startTime, String endTime, String phoneNum) {
        PullSmsSendStatusByPhoneNumberResponse res = null;
        try {
            SmsClient client = createSmsClient();
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 您可以直接查询 SDK 源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用 IDE 进行开发，可以方便地跳转查阅各个接口和数据结构的文档说明 */
            PullSmsSendStatusByPhoneNumberRequest req = new PullSmsSendStatusByPhoneNumberRequest();
            /* 填充请求参数，这里 request 对象的成员变量即对应接口的入参
             * 您可以通过官网接口文档或跳转到 request 对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台：https://console.cloud.tencent.com/smsv2
             * sms helper：https://cloud.tencent.com/document/product/382/3773 */
            /* 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 */
            req.setSmsSdkAppid(appid);
            // 设置拉取最大条数，最多100条
//            Long limit = 5L;
            req.setLimit(limit);
            req.setOffset(0L);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = simpleDateFormat.parse(startTime);
//            Date end = simpleDateFormat.parse(endTime);
            System.out.println(start.getTime());
            req.setSendDateTime(start.getTime());
//            req.setEndDateTime(end.getTime());
            req.setPhoneNumber(phoneNum);
            /* 通过 client 对象调用 PullSmsSendStatus 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 PullSmsSendStatusResponse 类的实例，与请求对象对应 */
            res = client.PullSmsSendStatusByPhoneNumber(req);
            // 输出 JSON 格式的字符串回包
            System.out.println(PullSmsSendStatusResponse.toJsonString(res));
        } catch (TencentCloudSDKException | ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 统计短信发送数据
     *
     * @param limit     设置拉取最大条数，最多100条
     * @param startTime 需要拉取的开始时间，精确到小时 yyyymmddhh
     * @param endTime   需要拉取的截止时间，精确到小时 yyyymmddhh
     */
    public static SendStatusStatisticsResponse sendStatusStatistics(Long limit, String startTime, String endTime) {
        SendStatusStatisticsResponse response = null;
        try {
            SmsClient client = createSmsClient();
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 您可以直接查询 SDK 源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用 IDE 进行开发，可以方便地跳转查阅各个接口和数据结构的文档说明 */
            SendStatusStatisticsRequest req = new SendStatusStatisticsRequest();
            /* 填充请求参数，这里 request 对象的成员变量即对应接口的入参
             * 您可以通过官网接口文档或跳转到 request 对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台：https://console.cloud.tencent.com/smsv2
             * sms helper：https://cloud.tencent.com/document/product/382/3773 */
            /* 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 */
            req.setSmsSdkAppid(appid);
            // 设置拉取最大条数，最多100条
            req.setLimit(limit);
            /* 偏移量，目前固定设置为0 */
            Long offset = 0L;
            req.setOffset(offset);
            /* 开始时间，yyyymmddhh 需要拉取的起始时间，精确到小时 */
            long startdatetime = Long.valueOf(startTime);
            req.setStartDateTime(startdatetime);
            /* 结束时间，yyyymmddhh 需要拉取的截止时间，精确到小时
             * 注：EndDataTime 必须大于 StartDateTime */
            long enddatatime = Long.valueOf(endTime);
            req.setEndDataTime(enddatatime);
            /* 通过 client 对象调用 SendStatusStatistics 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendStatusStatisticsResponse 类的实例，与请求对象对应 */
            response = client.SendStatusStatistics(req);
            // 输出 JSON 格式的字符串回包
            System.out.println(SendStatusStatisticsResponse.toJsonString(response));
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return response;
    }


    public static SmsClient createSmsClient() {
        /* 必要步骤：
         * 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
         * 这里采用的是从环境变量读取的方式，需要在环境变量中先设置这两个值。
         * 你也可以直接在代码中写死密钥对，但是小心不要将代码复制、上传或者分享给他人，
         * 以免泄露密钥对危及你的财产安全。
         * CAM密匙查询: https://console.cloud.tencent.com/cam/capi*/
        Credential cred = new Credential(secretId, secretKey);

        // 实例化一个http选项，可选，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        // 设置代理
//            httpProfile.setProxyHost("host");
//            httpProfile.setProxyPort(port);
        /* SDK默认使用POST方法。
         * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
        httpProfile.setReqMethod("POST");
        /* SDK有默认的超时时间，非必要请不要进行调整
         * 如有需要请在代码中查阅以获取最新的默认值 */
        httpProfile.setConnTimeout(60);
        /* SDK会自动指定域名。通常是不需要特地指定域名的，但是如果你访问的是金融区的服务
         * 则必须手动指定域名，例如sms的上海金融区域名： sms.ap-shanghai-fsi.tencentcloudapi.com */
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        /* 非必要步骤:
         * 实例化一个客户端配置对象，可以指定超时时间等配置 */
        ClientProfile clientProfile = new ClientProfile();
        /* SDK默认用TC3-HMAC-SHA256进行签名
         * 非必要请不要修改这个字段 */
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);
        /* 实例化要请求产品(以sms为例)的client对象
         * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量 */
        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
        return client;
    }
}
