/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.https;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Logger;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 安全的Restful服务访问请求封装处理
 * 
 * @author min.weixm
 * @version $Id: HttpsRestTemplateTools.java, v 0.1 Nov 1, 2017 8:32:30 PM min.weixm Exp $
 */
public class HttpsRestTemplateTools {

    /** 日志 */
    private static final Logger                 logger    = Logger.getLogger(HttpClientUtils.class);

    /** 单实例 */
    private static final HttpsRestTemplateTools _instance = new HttpsRestTemplateTools();

    public static HttpsRestTemplateTools getInstance() {
        return _instance;
    }

    /**
     * 请求指定地址的restful服务
     * 
     * @param url
     * @param obj
     * @return
     */
    public Object postDefaultRestfulService(String url, Object obj) {

        if (obj == null) {
            logger.warn("入参对象不可用，不做请求。 obj is null!");
            return null;
        }

        RestTemplate template = buildDefaultRestTemplate();
        return template.postForObject(url, obj, obj.getClass());
    }

    /**
     * 初始化证书支持的CloseableHttpClient
     * 
     * @throws KeyStoreException        证书存储异常
     * @throws NoSuchAlgorithmException 算法异常
     * @throws KeyManagementException   证书管理异常
     * @return                          CloseableHttpClient
     */
    private static CloseableHttpClient acceptsDefaultUntrustedCertsHttpClient() throws KeyStoreException,
                                                                                NoSuchAlgorithmException,
                                                                                KeyManagementException {

        //不做任何处理，默认相信所有证书
        SSLContext sslContext = new SSLContextBuilder()
            .loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] arg0,
                                         String arg1) throws CertificateException {
                    return true;
                }
            }).build();

        //将对证书的认可配置加入上下文
        HttpClientBuilder b = HttpClientBuilder.create();
        b.setSSLContext(sslContext);

        //对HostName不做校验
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

        //注册请求方式
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
            hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
            .<ConnectionSocketFactory> create()
            .register("http", PlainConnectionSocketFactory.getSocketFactory())
            .register("https", sslSocketFactory).build();

        //将请求方式加入连接管理器（支持多线程）
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(
            socketFactoryRegistry);
        connMgr.setMaxTotal(200);
        connMgr.setDefaultMaxPerRoute(100);
        b.setConnectionManager(connMgr);

        return b.build();
    }

    /**
     * 构建RestTemplate实例
     * 
     * @return  RestTemplate
     */
    private static RestTemplate buildDefaultRestTemplate() {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = acceptsDefaultUntrustedCertsHttpClient();
        } catch (KeyManagementException e) {
            logger.error("证书关键字异常：" + e.getMessage(), e);
        } catch (KeyStoreException e) {
            logger.error("证书存储异常：" + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("算法执行异常：" + e.getMessage(), e);
        }
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

}
