package com.zhouxinghang.study.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by zhouxinghang on 2018/4/26.
 */
public class BoardIpFind {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        for(int i = 10; i <= 14; i ++) {
            HttpResponse response = null;
//            try {
//                response = httpClient.execute(getRequest(i));
//            } catch (IOException e) {
//                System.out.println(i+ "======" + "没有个这个ip");
//                continue;
//            }
//            System.out.println(i+ "======" + EntityUtils.toString(response.getEntity()));
            response = httpClient.execute(getRequest(234));
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }

    private static HttpUriRequest getRequest(int n) {
        String url = "http://172.30.44."+String.valueOf(n) +":8080";
        HttpUriRequest request = RequestBuilder.get(url)
            .setConfig(getRequestConfig())
            .build();
        return request;
    }

    private static RequestConfig getRequestConfig() {
        RequestConfig config = RequestConfig.custom()

            .setConnectionRequestTimeout(0)//从数据库取连接超时时间
            //连接超时时间  毫秒
            //java.net.SocketTimeoutException: connect timed out
            .setConnectTimeout(5000)
            //请求超时时间
            //java.net.SocketTimeoutException: Read timed out
            .setSocketTimeout(5000)
            .build();
        return config;
    }
}
