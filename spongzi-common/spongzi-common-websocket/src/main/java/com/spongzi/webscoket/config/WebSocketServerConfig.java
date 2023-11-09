package com.spongzi.webscoket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

/**
 * Web套接字服务器配置
 *
 * @author spong
 * @date 2023/11/09
 */
public class WebSocketServerConfig extends ServerEndpointConfig.Configurator {

    /**
     * 检查原点
     *
     * @param originHeaderValue 原产地标头值
     * @return boolean
     */
    @Override
    public boolean checkOrigin(String originHeaderValue) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 实现校验逻辑
        return true;
    }

    /**
     * 修改握手
     *
     * @param sec      秒
     * @param request  请求
     * @param response 响应
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        Map<String, List<String>> parameterMap = request.getParameterMap();
        List<String> erpList = parameterMap.get("erp");
        if (!CollectionUtils.isEmpty(erpList)) {
            sec.getUserProperties().put("erp", erpList.get(0));
        }

    }
}
