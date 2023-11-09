package com.spongzi.tool.util;

import javafx.util.Pair;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * HTTP实用程序
 *
 * @author spong
 * @date 2023/11/09
 */
public class HttpUtils {

    static CloseableHttpClient httpClient;

    static {
        // 注册register
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        // 创建连接池
        try (PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry)) {
            connectionManager.setMaxTotal(500);
            connectionManager.setDefaultMaxPerRoute(500);
            connectionManager.setDefaultSocketConfig(
                    SocketConfig.custom()
                            .setSoTimeout(15, TimeUnit.SECONDS)
                            .setTcpNoDelay(true)
                            .build()
            );
            connectionManager.setValidateAfterInactivity(TimeValue.ofSeconds(15));

            // 针对requestConfig进行优化
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(Timeout.ofSeconds(1))
                    .setConnectionRequestTimeout(Timeout.ofSeconds(1))
                    .setResponseTimeout(Timeout.ofSeconds(1))
                    .build();

            httpClient = HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig)
                    .disableAutomaticRetries()
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String post(String url, List<Pair<String, String>> pairList, Map<String, String> headerMap) throws Exception {
        url = url + "?" + buildParam(pairList);
        HttpPost httpPost = new HttpPost(url);
        if (Objects.nonNull(headerMap) && !headerMap.isEmpty()) {
            headerMap.forEach(httpPost::addHeader);
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } finally {
            if (null != response) {
                EntityUtils.consume(response.getEntity());
            }
        }
    }

    /**
     * 构建参数
     *
     * @param pairList 配对列表
     * @return {@link String}
     */
    private static String buildParam(List<Pair<String, String>> pairList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Pair<String, String> pair : pairList) {
            stringBuilder.append(pair.getKey())
                    .append("=")
                    .append(pair.getValue())
                    .append("&");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
