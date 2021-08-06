package top.luoqiz.pay.config;

import com.egzosn.pay.common.bean.CertStoreType;
import com.egzosn.pay.common.http.HttpConfigStorage;
import com.egzosn.pay.spring.boot.core.PayServiceConfigurer;
import com.egzosn.pay.spring.boot.core.configurers.MerchantDetailsServiceConfigurer;
import com.egzosn.pay.spring.boot.core.configurers.PayMessageConfigurer;
import com.egzosn.pay.spring.boot.core.merchant.PaymentPlatform;
import com.egzosn.pay.spring.boot.core.provider.merchant.platform.AliPaymentPlatform;
import com.egzosn.pay.spring.boot.core.provider.merchant.platform.PaymentPlatforms;
import com.egzosn.pay.spring.boot.core.provider.merchant.platform.WxPaymentPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;
import top.luoqiz.pay.config.handlers.AliPayMessageHandler;
import top.luoqiz.pay.config.handlers.WxPayMessageHandler;
import top.luoqiz.pay.config.interceptor.AliPayMessageInterceptor;

import javax.sql.DataSource;

/**
 * 支付服务配置
 *
 * @author egan
 * email egzosn@gmail.com
 * date 2019/5/26.19:25
 */
@Configuration
public class MerchantPayServiceConfigurer implements PayServiceConfigurer {

    //    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AutowireCapableBeanFactory spring;

    @Autowired
    private AliPayMessageHandler aliPayMessageHandler;

    @Autowired
    private DataSource dataSource;

    /**
     * 商户配置
     *
     * @param merchants 商户配置
     */
    @Override
    public void configure(MerchantDetailsServiceConfigurer merchants) {
//        数据库文件存放 /doc/sql目录下
//        merchants.jdbc().template(jdbcTemplate);
        //微信请求配置，详情参考https://gitee.com/egzosn/pay-java-parent项目中的使用
        HttpConfigStorage wxHttpConfigStorage = new HttpConfigStorage();
        wxHttpConfigStorage.setKeystore("http://www.egzosn.com/certs/ssl 退款证书");
        wxHttpConfigStorage.setCertStoreType(CertStoreType.URL);
        wxHttpConfigStorage.setStorePassword("ssl 证书对应的密码， 默认为商户号");
//        merchants.jdbc(dataSource);
        //内存Builder方式
        merchants.inMemory()
                .ali()
                .detailsId("1")
                .appid("2021001145627229")
                .keyPrivate("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCFShIO8apl14gU01Yy5qn8p+7NginHs5Eh/I7b+evud68m36pNcWB6Cjzz9nmiw538+b+AVMRjiTywcbPFiONmil0ncCR/3O8L+8PnoLqCLJ/IOmBZ4zpEysE5hTaiaVpQ5ecZ92y3ysvkGhTwww3CnEa75fc9acUW8n4SGs8Z2bZB4kI5KT5vq3NRswQ5vXT8jSFkcZppasATIsWYzkWNZ0DNSVvb5yYwiKMRluNFoDJvgIE3HayDMhT/dW4o3I8qruXkIR6iU/zz5XfilrsilaPTi7cnAgEfWq2lXJEIZj3yoRNMjUIHEJ7CpCZpSFv9l06PJKsrWzZfK9Ksx31LAgMBAAECggEAENPjJ8PW8Xiek5KC5XY9q8o/2e4+/Bltj07lclxBLyApGs1OIOzZc07rlxldDfGIdrHJPZh2GuLuqJASVDQOEZoSXLJJyWLJQ0gL2ahvI5QisBK+q1qGtryqXGq3FRYb8u6zmJGmRiXyS+AHGMZnbFRykbJc3oIGAfmndP2xe9/R3G72sDrtif6DZcoA/G6UNXml5BtSF68t+L3NnfBe+fYpk/tHF831Nb+XDmXoZOHSZJxXuVn/txFf1oXu4r/AmIljktu66nwSuFSHyVnpaLpICN/8mGTFKAR074BiNpLp0xW4rXPSQb8pOFRyfLNr+okY9StiMrgNg8JOhmhoEQKBgQD5yo6S6nhVcESlXBxuTeM5im2hlRN3ipFqrzfb0DjZGUrwf7Ik1qas40ApPIJTmMIZVb76/U2u+c67c6MOd05snIJRBEGn/RyvyDa9Nbrau14EYdXqhaejY5SPb+RFltyD1QHJA/ZeniF3IjcJpq24ctRx8vKvYZxQJTcBS3MRVwKBgQCImjO4Pj4g+JfVMKQNNq33vys23ENly1E096IxQ3qZ3RYzpd4ooXfDgXxOlAOU2UqJB3p5nJUb4xTLmGP/QzPPQVGlBIca3qwoF3iEqDoouMkUnDixc/PINKN/TQFuUIVSN9+nQPZw4d3sDSkkZdkOKeJ/mGjz/OnGfMr6UrJ3LQKBgCUS8kxN7qRHsHiWjkW027fLmu/zj1sbvu+FqBXA9t8KCPxfB5K+JSjd5cT5DSLZNl3KQzk/ZUtGGm/p/taD0KkAJbtelVwYjixs2+sDUYNrcc3Ws4hGZBY1DnSnl8rYVExgzg4Gv4dVkwP6mQ6epYQNzwhGlcyAAVJH470WdLKrAoGAIogQoPejDwSopSzpm6mB6sn5q6GcrAPsoF4lMxzixjh8X0wDz/OZaeO2v8ps6VjQnbk1Pb7Wdrk9zYXVaXbchvz1Haj6WlGF5ZChV6+LHDYyJrKjJkCwlKCx4AFgZjmWqkUTlZ03byu5xO1CxD+20fzGlAi8J0l88z8itIrpMHUCgYEAshS3nZ5BvVtG5l6nN0x/jGNzAOoofqoD0sdSrlHgoHsDe9QFTFhpwGZR6yrkMU93ffhCPfEJdXqSZMnEkgk6fXx+78wplY7jLi8zH86QDkesM18iGNThQKjv4wNqLLC79qoGrgQ7tzQiNfXmGtjFtiig70JGbQm9vXNSUAw5k8I=")
//                .keyPublic("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhUoSDvGqZdeIFNNWMuap/KfuzYIpx7ORIfyO2/nr7nevJt+qTXFgego88/Z5osOd/Pm/gFTEY4k8sHGzxYjjZopdJ3Akf9zvC/vD56C6giyfyDpgWeM6RMrBOYU2omlaUOXnGfdst8rL5BoU8MMNwpxGu+X3PWnFFvJ+EhrPGdm2QeJCOSk+b6tzUbMEOb10/I0hZHGaaWrAEyLFmM5FjWdAzUlb2+cmMIijEZbjRaAyb4CBNx2sgzIU/3VuKNyPKq7l5CEeolP88+V34pa7IpWj04u3JwIBH1qtpVyRCGY98qETTI1CBxCewqQmaUhb/ZdOjySrK1s2XyvSrMd9SwIDAQAB")
                // 支付平台公钥(签名校验使用)
                .keyPublic("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArQJVX+PdJ2WiIc3UDUMLAg4RVS1cth3jkeGRH1YKtbqjfwx8LMHipLJ9XRxIOiTjIg294VLVZ3/qHHle6eGAnQPW/L0ErkDD+GRCmKWrFvOxSQzsfvaLURTISWxHWTwOACV+ZzsgI2jgc0rtDC34Paq9UxTFTjkQd7vPfnPt13ZIquCIOsKs1K4AAb/mIv/h6AgdCWnWYnyBOlH05nBH9dZfo5wNkZyB8zZcEitTOdfMwj3aueol42XLoS0Ib6RT5vTv2WxiKovIpQN+3jSZbnBsnBRGOH7NunM+WkB715+yu/ZswNn4KMZd+d1U0Pk6B4AHH12kkv9DfU3cG+xVpwIDAQAB")
                .inputCharset("utf-8")
                //异步回调地址
                .notifyUrl("http://1.202.244.82:9600/payBack1.json")
                //同步回调地址，支付完成后展示的页面
                .returnUrl("http://1.202.244.82:9600/payBack1.json")
                .pid("2088802878463743")
                // 收款账号
                .seller("2088802878463743")
                .signType("RSA2")
//                .test(true)
                .and()
                .wx()
                .detailsId("2")
                .appid("wx3344f4aed352deae")
                // 合作者id（商户号）
                .mchId("1608186370")
                .secretKey("991ded080***************f7fc61095")
                .notifyUrl("http://pay.egzosn.com/payBack2.json")
                .returnUrl("http://pay.egzosn.com/payBack2.json")
                .inputCharset("utf-8")
                .signType("MD5")
                //设置请求相关的配置
                .httpConfigStorage(wxHttpConfigStorage)
                .test(true)
                .and()
        ;

      /*  //------------内存手动方式------------------
        UnionMerchantDetails unionMerchantDetails = new UnionMerchantDetails();
        unionMerchantDetails.detailsId("3");
        //内存方式的时候这个必须设置
        unionMerchantDetails.setCertSign(true);
        unionMerchantDetails.setMerId("700000000000001");
        //公钥，验签证书链格式： 中级证书路径;
        unionMerchantDetails.setAcpMiddleCert("D:/certs/acp_test_middle.cer");
        //公钥，根证书路径
        unionMerchantDetails.setAcpRootCert("D:/certs/acp_test_root.cer");
        //私钥, 私钥证书格式： 私钥证书路径
        unionMerchantDetails.setKeyPrivateCert("D:/certs/acp_test_sign.pfx");
        //私钥证书对应的密码
        unionMerchantDetails.setKeyPrivateCertPwd("000000");
        //证书的存储方式
        unionMerchantDetails.setCertStoreType(CertStoreType.PATH);
        unionMerchantDetails.setNotifyUrl("http://127.0.0.1/payBack4.json");
        // 无需同步回调可不填  app填这个就可以
        unionMerchantDetails.setReturnUrl("http://127.0.0.1/payBack4.json");
        unionMerchantDetails.setInputCharset("UTF-8");
        unionMerchantDetails.setSignType("RSA2");
        unionMerchantDetails.setTest(true);
        //手动加入商户容器中
        merchants.inMemory().addMerchantDetails(unionMerchantDetails);*/
    }

    /**
     * 商户配置
     *
     * @param configurer 支付消息配置
     */
    @Override
    public void configure(PayMessageConfigurer configurer) {
        PaymentPlatform aliPaymentPlatform = PaymentPlatforms.getPaymentPlatform(AliPaymentPlatform.platformName);
        configurer.addHandler(aliPaymentPlatform, aliPayMessageHandler);
        configurer.addInterceptor(aliPaymentPlatform, spring.getBean(AliPayMessageInterceptor.class));
        configurer.addHandler(PaymentPlatforms.getPaymentPlatform(WxPaymentPlatform.platformName), new WxPayMessageHandler());
    }
}
