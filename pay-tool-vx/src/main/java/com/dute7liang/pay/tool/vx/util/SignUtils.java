package com.dute7liang.pay.tool.vx.util;

import com.dute7liang.pay.tool.vx.constant.WxConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 签名相关工具类.
 */
@Slf4j
public class SignUtils {

    /**
     * 签名的时候不携带的参数
     */
    private static List<String> NO_SIGN_PARAMS = Arrays.asList("sign", "key", "xmlString", "xmlDoc", "couponList");

    /**
     * 请参考并使用 {@link #createSign(Map, String, String, String[])} .
     *
     * @param params  the params
     * @param signKey the sign key
     * @return the string
     */
    public static String createSign(Map<String, String> params, String signKey) {
        return createSign(params, WxConstant.SignType.MD5, signKey, new String[0]);
    }

    /**
     * 微信支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3).
     *
     * @param params        参数信息
     * @param signType      签名类型，如果为空，则默认为MD5
     * @param signKey       签名Key
     * @param ignoredParams 签名时需要忽略的特殊参数
     * @return 签名字符串 string
     */
    public static String createSign(Map<String, String> params, String signType, String signKey, String[] ignoredParams) {
        SortedMap<String, String> sortedMap = new TreeMap<>(params);

        StringBuilder toSign = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = params.get(key);
            boolean shouldSign = false;
            if (StringUtils.isNotEmpty(value) && !ArrayUtils.contains(ignoredParams, key)
                    && !NO_SIGN_PARAMS.contains(key)) {
                shouldSign = true;
            }
            if (shouldSign) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }
        toSign.append("key=").append(signKey);
        if (WxConstant.SignType.HMAC_SHA256.equals(signType)) {
//      return me.chanjar.weixin.common.util.SignUtils.createHmacSha256Sign(toSign.toString(), signKey);
            throw new RuntimeException("暂时只支持MD5签名方式！");
        } else {
            return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
        }
    }

    /**
     * 校验签名是否正确.
     *
     * @param params   需要校验的参数Map
     * @param signType 签名类型，如果为空，则默认为MD5
     * @param signKey  校验的签名Key
     * @return true - 签名校验成功，false - 签名校验失败
     */
    public static boolean checkSign(Map<String, String> params, String signType, String signKey) {
        String sign = createSign(params, signType, signKey, new String[0]);
        return sign.equals(params.get("sign"));
    }

}
