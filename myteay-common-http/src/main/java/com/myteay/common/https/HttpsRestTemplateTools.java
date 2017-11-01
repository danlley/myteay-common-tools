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
 * ��ȫ��Restful������������װ����
 * 
 * @author min.weixm
 * @version $Id: HttpsRestTemplateTools.java, v 0.1 Nov 1, 2017 8:32:30 PM min.weixm Exp $
 */
public class HttpsRestTemplateTools {

    /** ��־ */
    private static final Logger                 logger    = Logger.getLogger(HttpClientUtils.class);

    /** ��ʵ�� */
    private static final HttpsRestTemplateTools _instance = new HttpsRestTemplateTools();

    public static HttpsRestTemplateTools getInstance() {
        return _instance;
    }

    /**
     * ����ָ����ַ��restful����
     * 
     * @param url
     * @param obj
     * @return
     */
    public Object postDefaultRestfulService(String url, Object obj) {

        if (obj == null) {
            logger.warn("��ζ��󲻿��ã��������� obj is null!");
            return null;
        }

        RestTemplate template = buildDefaultRestTemplate();
        return template.postForObject(url, obj, obj.getClass());
    }

    /**
     * ��ʼ��֤��֧�ֵ�CloseableHttpClient
     * 
     * @throws KeyStoreException        ֤��洢�쳣
     * @throws NoSuchAlgorithmException �㷨�쳣
     * @throws KeyManagementException   ֤������쳣
     * @return                          CloseableHttpClient
     */
    private static CloseableHttpClient acceptsDefaultUntrustedCertsHttpClient() throws KeyStoreException,
                                                                                NoSuchAlgorithmException,
                                                                                KeyManagementException {

        //�����κδ���Ĭ����������֤��
        SSLContext sslContext = new SSLContextBuilder()
            .loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] arg0,
                                         String arg1) throws CertificateException {
                    return true;
                }
            }).build();

        //����֤����Ͽ����ü���������
        HttpClientBuilder b = HttpClientBuilder.create();
        b.setSSLContext(sslContext);

        //��HostName����У��
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

        //ע������ʽ
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
            hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
            .<ConnectionSocketFactory> create()
            .register("http", PlainConnectionSocketFactory.getSocketFactory())
            .register("https", sslSocketFactory).build();

        //������ʽ�������ӹ�������֧�ֶ��̣߳�
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(
            socketFactoryRegistry);
        connMgr.setMaxTotal(200);
        connMgr.setDefaultMaxPerRoute(100);
        b.setConnectionManager(connMgr);

        return b.build();
    }

    /**
     * ����RestTemplateʵ��
     * 
     * @return  RestTemplate
     */
    private static RestTemplate buildDefaultRestTemplate() {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = acceptsDefaultUntrustedCertsHttpClient();
        } catch (KeyManagementException e) {
            logger.error("֤��ؼ����쳣��" + e.getMessage(), e);
        } catch (KeyStoreException e) {
            logger.error("֤��洢�쳣��" + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("�㷨ִ���쳣��" + e.getMessage(), e);
        }
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

}
