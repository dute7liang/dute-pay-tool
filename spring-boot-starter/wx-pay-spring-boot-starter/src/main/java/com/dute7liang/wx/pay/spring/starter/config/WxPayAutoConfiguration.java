package com.dute7liang.wx.pay.spring.starter.config;

import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import com.dute7liang.pay.tool.vx.service.WxPayServiceI;
import com.dute7liang.pay.tool.vx.service.WxPayServiceImpl;
import com.dute7liang.wx.pay.spring.starter.properties.WxPayProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  微信支付自动配置
 *
 * @author: zl
 * @Date: 2020-4-1 11:50
 */
@Configuration
@EnableConfigurationProperties(WxPayProperties.class)
@ConditionalOnClass(WxPayServiceI.class)
@ConditionalOnProperty(value = "duteliang.wx.pay.enabled", havingValue = "true", matchIfMissing = true)
public class WxPayAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(WxPayServiceI.class)
    public WxPayServiceI wxPayService(WxPayProperties payProperties) {
        WxPayServiceI wxPayService = new WxPayServiceImpl();
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setPayBaseUrl(StringUtils.trimToNull(payProperties.getPayBaseUrl()));
        payConfig.setAppid(StringUtils.trimToNull(payProperties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(payProperties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(payProperties.getMchKey()));
        payConfig.setNotifyURL(StringUtils.trimToNull(payProperties.getNotifyUrl()));
        payConfig.setUseSandboxEnv(payProperties.isUseSandboxEnv());
        payConfig.setCertPath(StringUtils.trimToNull(payProperties.getCertPath()));
        payConfig.setCertPassword(StringUtils.trimToNull(payProperties.getCertPassword()));
        payConfig.setSignType(StringUtils.trimToNull(payProperties.getSignType()));
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }


}
