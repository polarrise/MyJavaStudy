package com.tuling.mall.user.component.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author：Jinbiao
 * @Date：2023/4/27 14:58
 * @Desc：授权控制规则——来源访问控制（黑白名单）
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser {

    /**
     * 通过request获取来源标识，交给授权规则进行匹配。  如果在sentinel控制面板对资源设置了授权规则-黑名单。 那么如果穿了serviceName就会 提示"授权规则不通过"
     * @param request
     * @return
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 标识字段名称可以自定义
        String origin = request.getParameter("serviceName");
//        if (StringUtil.isBlank(origin)){
//            throw new IllegalArgumentException("serviceName参数未指定");
//        }
        return origin;
    }
}
