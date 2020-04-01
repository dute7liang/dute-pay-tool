package com.dute7liang.pay.tool.vx.core.trade;

import com.dute7liang.pay.tool.common.bean.Trade;
import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 统一下单接口的订单数据
 * <br/>
 * author: zl
 * Date: 2020/3/27 19:44
 */
@Getter
@Setter
@Builder(builderMethodName = "newBuilder")
public class WxUnifiedOrderTrade extends WxBaseTrade implements Trade,Serializable {

    /**
     * 终端设备编号
     */
    private String deviceInfo;

    /**
     * 商品描述,必填
     */
    private String body;

    /**
     * 商品描述
     */
    private String detail;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 商品订单号 必填
     */
    private String outTradeNo;

    /**
     * 货币类型
     */
    private String feeType;

    /**
     * 支付金额，必填，单位：分
     */
    private Integer totalFee;

    /**
     * 终端ip，必填
     */
    private String spbillCreateIp;

    /**
     * 交易起始时间 yyyyMMddHHmmss
     */
    private String timeStart;

    /**
     * 交易结束时间 yyyyMMddHHmmss
     */
    private String timeExpire;

    /**
     * 订单优惠标记
     */
    private String goodsTag;

    /**
     * 通知地址 必填
     */
    private String notifyUrl;

    /**
     * 交易类型 交易类型，必填
     */
    private String tradeType;

    /**
     * 指定支付方式
     */
    private String limitPay;

    /**
     * 开发票入口开放标识
     */
    private String receipt;


    @Override
    protected void storeMap(Map<String, String> map) {
        map.put("device_info", deviceInfo);
        map.put("body", body);
        map.put("detail", detail);
        map.put("attach", attach);
        map.put("out_trade_no", outTradeNo);
        map.put("fee_type", feeType);
        map.put("total_fee", totalFee.toString());
        map.put("spbill_create_ip", spbillCreateIp);
        map.put("time_start", timeStart);
        map.put("time_expire", timeExpire);
        map.put("goods_tag", goodsTag);
        map.put("notify_url", notifyUrl);
        map.put("trade_type", tradeType);
        map.put("limit_pay", limitPay);
        map.put("receipt", receipt);
    }

    @Override
    public void checkAndSign(WxPayConfig config) {
        if (StringUtils.isBlank(this.getNotifyUrl())) {
            this.setNotifyUrl(config.getNotifyURL());
        }
        super.checkAndSign(config);
    }



}
