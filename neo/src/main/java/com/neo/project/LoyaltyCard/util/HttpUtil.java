package com.neo.project.LoyaltyCard.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.util.logging.Logger;

@Slf4j
public class HttpUtil {

    private Logger logger = (Logger) LoggerFactory.getLogger(HttpUtil.class);

    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_MAX_PER_ROUTE_CONNECTIONS = 50;

    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    @PostConstruct
    private void init(){
        poolingHttpClientConnectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE_CONNECTIONS);
    }

    public static JSONObject sendPostDataByJson(final String url, int socketTimeout, JSONObject json){
        JSONObject jsonResult = null;
        HttpPost httpPost;
        StringEntity entity;
        CloseableHttpClient httpClient;
        CloseableHttpResponse response;
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout).build();

            httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json, text/javascript, */*");
            httpPost.setHeader("Accept-Language", "zh-CN");
            httpPost.setHeader("content-type", "application/json");
            httpPost.setConfig(requestConfig);

            if(StringUtils.isNotEmpty(json.toString()))
            {
                entity = new StringEntity(json.toString(), "UTF-8");
                httpPost.setEntity(entity);
            }

            if(url.substring(0,5).equals("https")){
                httpClient = getHttpsClient(requestConfig);
            } else {

                httpClient = getHttpClient(requestConfig);
            }

            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            long end = System.currentTimeMillis();
            long ms = end - start;

            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                jsonResult = JSONObject.parseObject(result);
            }
            httpClient.close();
            response.close();
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        return jsonResult;
    }

    @Bean
    private static CloseableHttpClient getHttpClient(RequestConfig requestConfig){
        return   HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    @Bean
    private static CloseableHttpClient getHttpsClient(RequestConfig requestConfig) throws Exception {
        SSLContext sslContext = SSLContexts
                .custom()
                .loadKeyMaterial(ResourceUtils.getFile("classpath:keystore.jks"),"allpassword".toCharArray(),"allpassword".toCharArray())
                .loadTrustMaterial(ResourceUtils.getFile("classpath:truststore.jks"),"allpassword".toCharArray())
                .build();

       return HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setSSLContext(sslContext)
                .build();
    }
}
