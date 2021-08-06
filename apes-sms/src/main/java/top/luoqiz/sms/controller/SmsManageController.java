package top.luoqiz.sms.controller;

import com.tencentcloudapi.sms.v20190711.models.PullSmsSendStatusByPhoneNumberResponse;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatusStatisticsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.luoqiz.sms.platform.AliyunSmsManage;
import top.luoqiz.sms.platform.TencentSmsManage;
import top.luoqiz.sms.platform.aliyun.QuerySendDetails;
import top.luoqiz.sms.service.SmsManageService;

/**
 * @author luoqiz
 * @description: 短信管理接口
 */
@Tag(name = "短信管理接口", description = "短信管理接口")
@RequestMapping("sms")
@RestController
public class SmsManageController {


    @Autowired
    private SmsManageService smsManageService;

    @Operation(summary = "发送注册短信")
    @PostMapping(value = "/send")
    public Boolean sendSmsMsg(String phone, String username) {
        return smsManageService.sendSmsMsg(phone, username);
    }

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
    @Operation(summary = "查询某天某手机号的接收信息")
    @GetMapping("/querySendDetails")
    public QuerySendDetails querySendDetails(String currentPage, String pageSize, String phoneNumber, String sendDate) throws Exception {
        return AliyunSmsManage.querySendDetails(Long.valueOf(currentPage), Long.valueOf(pageSize), phoneNumber, sendDate);
    }

    /**
     * 发送短信
     *
     * @param phones
     * @return
     */
    @GetMapping("/send")
    public SendSmsResponse sendMsg(String phones) {
//        String[] phoneList = phones.split(".");
//        for (String phone : phoneList) {
//            SmsCodeModel smsCode = smsRepository.createSmsCode(phone);
//            TencentSendMsgUtil.sendMsg(smsCode.getPhone(), smsCode.getCode());
//        }
        return null;
    }

    /**
     * 拉取回执状态
     *
     * @param limit
     */
    @GetMapping("/pullSmsSendStatus")
    public PullSmsSendStatusByPhoneNumberResponse pullSmsSendStatus(String limit, String startTime, String endTime, String phoneNum) {
        return TencentSmsManage.pullSmsSendStatus(Long.valueOf(limit), startTime, endTime, phoneNum);
    }

    /**
     * 统计短信发送数据
     *
     * @param limit     设置拉取最大条数，最多100条
     * @param startTime 需要拉取的开始时间，精确到小时 yyyymmddhh
     * @param endTime   需要拉取的截止时间，精确到小时 yyyymmddhh
     */
    @GetMapping("/sendStatusStatistics")
    public SendStatusStatisticsResponse sendStatusStatistics(String limit, String startTime, String endTime) {
        return TencentSmsManage.sendStatusStatistics(Long.valueOf(limit), startTime, endTime);
    }


}
