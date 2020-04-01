package com.dute7liang.pay.tool.vx.core.notify;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 支付异步通知代金券详细.
 *
 * author: zl
 * Date: 2020/4/1
 */
@Data
@NoArgsConstructor
public class WxPayOrderNotifyCoupon implements Serializable {
    private static final long serialVersionUID = -4165343733538156097L;

    private String couponId;
    private String couponType;
    private Integer couponFee;
}
