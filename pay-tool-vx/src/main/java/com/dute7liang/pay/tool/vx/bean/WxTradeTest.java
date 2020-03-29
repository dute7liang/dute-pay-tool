package com.dute7liang.pay.tool.vx.bean;

import com.dute7liang.pay.tool.common.bean.Trade;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/27 19:44
 */
@Getter
@Setter
@Builder(builderMethodName = "newBuilder")
@XStreamAlias("xml")
public class WxTradeTest extends WxBaseTrade implements Trade,Serializable {

    /**
     * 终端设备编号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 商品描述,必填
     */
    @XStreamAlias("body")
    private String body;

    /**
     * 商品描述
     */
    @XStreamAlias("detail")
    private String detail;

    /**
     * 附加数据
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 商品订单号 必填
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 货币类型
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 支付金额，必填，单位：分
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 终端ip，必填
     */
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;

    /**
     * 交易起始时间 yyyyMMddHHmmss
     */
    @XStreamAlias("time_start")
    private String timeStart;

    /**
     * 交易结束时间 yyyyMMddHHmmss
     */
    @XStreamAlias("time_expire")
    private String timeExpire;

    /**
     * 订单优惠标记
     */
    @XStreamAlias("goods_tag")
    private String goodsTag;

    /**
     * 通知地址 必填
     */
    @XStreamAlias("notify_url")
    private String notifyUrl;

    /**
     * 交易类型 交易类型，必填
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 指定支付方式
     */
    @XStreamAlias("limit_pay")
    private String limitPay;

    /**
     * 开发票入口开放标识
     */
    @XStreamAlias("receipt")
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



}
