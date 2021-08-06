package top.luoqiz.storage.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.luoqiz.common.web.annotation.AnonymousAccess;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author luoqiz
 * @description: 文件上传接口
 */
@Tag(name = "文件上传接口", description = "文件上传接口")
@RequestMapping("storage")
@RestController
public class UploadController {

    @Value("${aliyun.oss.accessKey.id:''}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKey.secret:''}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucket-name:''}")
    private String bucketName;

    @Value("${aliyun.oss.endpoint:''}")
    private String endpoint;

    @Operation(summary = "图片上传")
    @PostMapping(value = "/upload/img")
    public String fileUpload(MultipartFile file) throws Exception {

        byte[] content = file.getBytes();

        String bucketName = "ldd-ping";
        OSS ossClient = new OSSClientBuilder().build("http://oss-cn-beijing.aliyuncs.com", accessKeyId, accessKeySecret);
        String url = "";
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectMetadata.setContentType("image/jpg");

            String fileName = "images/" + RandomStringUtils.randomAlphanumeric(15) + ".jpg";
            ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(content), objectMetadata);
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            url = ossClient.generatePresignedUrl(bucketName, fileName, expiration).toString();
            url = url.substring(0, url.indexOf("?"));
        } finally {
            ossClient.shutdown();
        }
        return url;
    }

    @Operation(summary = "客户端获取oss上传权限")
    @GetMapping(value = "/aliyun/permission")
    public Map<String, String> getPermission(String path) {

        // host的格式为 bucket-name.endpoint
        String host = "http://" + bucketName + "." + endpoint;
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = "http://88.88.88.88:8888";
        // 用户上传文件时指定的前缀。
        String dir = path == null || path.trim().length() == 0 ? "" : path;
        dir = dir.startsWith(File.separator) || dir.startsWith("/") ? dir.substring(1) : dir;
        dir = dir.endsWith(File.separator) || dir.endsWith("/") ? dir : dir + "/";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        long expireTime = 60;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<>();
        respMap.put("accessid", accessKeyId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));

        return respMap;

    }
}
