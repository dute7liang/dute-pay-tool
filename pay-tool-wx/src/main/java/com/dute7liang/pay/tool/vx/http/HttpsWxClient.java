package com.dute7liang.pay.tool.vx.http;

import com.dute7liang.pay.tool.common.http.client.common.AbstractSSLHttpClient;
import com.dute7liang.pay.tool.vx.config.WxPayConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

/**
 * 
 * 微信支付专用HTTP SSL客户端
 * @author 杨元
 * 
 */
public class HttpsWxClient extends AbstractSSLHttpClient {
    
    private static transient HttpsWxClient CLIENT = null;

    private HttpsWxClient(){}

    @Setter
    @Getter
    private WxPayConfig config;

    public static HttpsWxClient getInstance(WxPayConfig config){
        if(CLIENT != null){
            return CLIENT;
        }
        synchronized (HttpsWxClient.class){
            if(CLIENT != null){
                return CLIENT;
            }
            CLIENT = new HttpsWxClient();
            CLIENT.setConfig(config);
            return CLIENT;
        }
    }

    
    @Override
    protected SSLConnectionSocketFactory getSSLSocketFactory() {
        FileInputStream fis = null;
        try {
            /**
             * 加载本地的证书
             * 不同的第三方接口，需要使用不同的证书
             */
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            fis = new FileInputStream(new File(config.getCertPath()));
            keyStore.load(fis, config.getCertPassword().toCharArray());
            
            /**
             * 构造SSL套接字工厂
             */
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, config.getCertPassword().toCharArray())
                    .build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());
            
            return sslsf;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    fis = null;
                }
            }
        }
    }
}
