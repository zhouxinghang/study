package com.zhouxinghang.study.httpclient;

import com.ebuy.common.util.JsonUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;


/**
 * Created by zhouxinghang on 2018/4/18.
 */
public class HttpConfigDemo {
    private static final Logger LOG = LoggerFactory.getLogger(HttpConfigDemo.class);


    /**
     *  .setRetryHandler(new StandardHttpRequestRetryHandler(3, true))
     .setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy())
     .setMaxConnPerRoute(32)
     .setMaxConnTotal(256)
     .setDefaultCookieStore(new BasicCookieStore())
     .setDefaultRequestConfig(requestConfig)
     .setSSLContext(getSSLContext())
     .setSSLHostnameVerifier((s, sslSession) -> true)
     .setDefaultSocketConfig(socketConfig)
     * @return
     */
    private HttpClient getHttpClient() {
        HttpClient httpClient = HttpClients.custom()
            .setConnectionTimeToLive(10L, TimeUnit.SECONDS)//设置连接存活时间
            .setMaxConnTotal(10)
            .setMaxConnPerRoute(20)
            .setRetryHandler(new DefaultHttpRequestRetryHandler(2, true))//请求失败重试
            .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)//长连接配置
            .setSSLContext(getSSLContext())
            .setDefaultRequestConfig(getRequestConfig())
            .build();

        return httpClient;

    }



    /**
     * RequestConfig的默认配置
     * private boolean expectContinueEnabled;
     private HttpHost proxy;
     private InetAddress localAddress;
     private boolean staleConnectionCheckEnabled = false;
     private String cookieSpec;
     private boolean redirectsEnabled = true;
     private boolean relativeRedirectsAllowed = true;
     private boolean circularRedirectsAllowed;
     private int maxRedirects = 50;
     private boolean authenticationEnabled = true;
     private Collection<String> targetPreferredAuthSchemes;
     private Collection<String> proxyPreferredAuthSchemes;
     private int connectionRequestTimeout = -1;
     private int connectTimeout = -1;
     private int socketTimeout = -1;
     private boolean contentCompressionEnabled = true;
     * @return
     */
    private RequestConfig getRequestConfig() {
        RequestConfig config = RequestConfig.custom()

            .setConnectionRequestTimeout(0)//从数据库取连接超时时间
            //连接超时时间  毫秒
            //java.net.SocketTimeoutException: connect timed out
            .setConnectTimeout(500)
            //请求超时时间
            //java.net.SocketTimeoutException: Read timed out
            .setSocketTimeout(500)
            //默认为true
            .setRedirectsEnabled(false)
            //org.apache.http.client.RedirectException: Maximum redirects (1) exceeded
            .setMaxRedirects(2)//最大重定向次数
            .setContentCompressionEnabled(true)//自动gzip压缩
            .build();
        LOG.info("RequestConfig: ", config.toString());
        return config;
    }

    private HttpUriRequest getRequest() {
        HttpUriRequest request = RequestBuilder.get("http://172.30.44.61:8080")
            .setConfig(getRequestConfig())
            .setHeader("Connection", "keep-alive")
            .setEntity(getEntity())
            .build();
        return request;



    }

    private HttpEntity getEntity() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "zxh");
        HttpEntity entity = EntityBuilder.create()
            .setText(JsonUtils.objectToJson(params))
            .setContentType(ContentType.create("application/json", "utf-8"))
            .build();
        return entity;


    }


    private SSLContext getSSLContext() {
        try {
            return SSLContexts.custom().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();
        } catch (Exception e) {
            LOG.error("getSSL.ERROR:", e);
        }
        return null;
    }

    public static void main(String[] args) {
        HttpConfigDemo demo = new HttpConfigDemo();
        HttpClient httpClient = demo.getHttpClient();
        try {
            HttpResponse response = httpClient.execute(demo.getRequest());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
