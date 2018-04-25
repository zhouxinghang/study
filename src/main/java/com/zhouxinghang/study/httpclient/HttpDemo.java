package com.zhouxinghang.study.httpclient;

import com.ebuy.pojo.TbItem;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxinghang on 2018/4/17.
 */
public class HttpDemo {

    public static void main(String[] args) throws IOException {
        HttpDemo demo = new HttpDemo();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(demo.product());
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        HttpEntity entity1 = new BufferedHttpEntity(entity);
        //byte[] res = demo.toByteArray(entity);
        System.out.println("Response:\n" + demo.ConvertStreamToString(entity1.getContent()));
        System.out.println(EntityUtils.toString(new BufferedHttpEntity(entity1)));
        //System.out.println(EntityUtils.toString(entity));
        System.out.println(response.getStatusLine());
        response.close();
    }

    private HttpUriRequest product() throws UnsupportedEncodingException {
        RequestBuilder builder = build();
        builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        builder.setHeader("Proxy-Connection", "keep-alive");
        builder.setHeader("Connection", "keep-alive");
        builder.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        builder.setConfig(RequestConfig.DEFAULT);
        Map<String, String> map = new HashMap<>();
        /**
         * cid: 560
         title: 粪叉
         sellPoint: 粪叉齐刘海
         priceView: 8999.00
         price: 899900
         num: 999
         barcode: 2g523hy624h5
         image:
         desc: 添加商品测试
         itemParams:
         */
        map.put("cid", "560");
        map.put("title", "粪叉PLUS");
        map.put("sellPoint","齐刘海");
        map.put("priceView", "8999.00");
        map.put("price", "899900");
        map.put("num", "999");
        map.put("barcode","2g523hy624h5");
        map.put("desc", "添加商品测试");

        ByteArrayEntity entity = new ByteArrayEntity("q=listItem".getBytes());
        List<NameValuePair> pairList = new ArrayList<>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        builder.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
        return builder.build();

    }

    private RequestBuilder build() {
        return RequestBuilder.post("http://manager.ebuy.com" + "/item/save");
    }

    private  byte[] toByteArray(HttpEntity entity) throws IOException {
        Args.notNull(entity, "Entity");
        InputStream instream = entity.getContent();
        if (instream == null) {
            return null;
        } else {
            try {
                Args.check(entity.getContentLength() <= 2147483647L, "HTTP entity too large to be buffered in memory");
                int i = (int)entity.getContentLength();
                if (i < 0) {
                    i = 4096;
                }

                ByteArrayBuffer buffer = new ByteArrayBuffer(i);
                byte[] tmp = new byte[4096];

                int l;
                while((l = instream.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }

                byte[] var6 = buffer.toByteArray();
                return var6;
            } finally {
                instream.close();
            }
        }
    }

    private String ConvertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error=" + e.toString());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                System.out.println("Error=" + e.toString());
            }
        }
        return sb.toString();
    }
}
