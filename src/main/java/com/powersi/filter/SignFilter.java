package com.powersi.filter;

import com.powersi.common.api.CommonResult;
import com.powersi.common.api.ResultCode;
import com.powersi.model.RequestHeader;
import com.powersi.model.SignRequestWrapper;
import com.powersi.service.ThirdPartyCallService;
import com.powersi.utils.HttpDataUtil;
import com.powersi.utils.RedisUtil;
import com.powersi.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedMap;
/**
 * @Author：Jinbiao
 * @Date：2023/3/7 12:55
 * @Desc：SpringBoot 如何保证接口安全？高手都是这么玩的！   需要验证签名请求的过滤器
 */
@Component
public class SignFilter implements Filter {
    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private ThirdPartyCallService thirdPartyCallService;

    /**
     * 从filter配置中获取sign过期时间
     */
    private Long signMaxTime;

    private static final String NONCE_KEY = "x-nonce-";

    @Override
    public void init(FilterConfig filterConfig) {
        String signTime = filterConfig.getInitParameter("signMaxTime");
        signMaxTime = Long.parseLong(signTime);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        System.out.println(httpRequest.getRequestURI());

        HttpServletRequestWrapper requestWrapper = new SignRequestWrapper(httpRequest);
        //构建请求头
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setNonceStr(httpRequest.getHeader("nonceStr"));
        requestHeader.setSign(httpRequest.getHeader("signature"));
        String header = httpRequest.getHeader("timestamp");
        if (StringUtils.isEmpty(header)) {
            responseFail(httpResponse, ResultCode.FORBIDDEN);
            return;
        }
        requestHeader.setTimestamp(Long.parseLong(header));
        //验证请求头是否存在
        if (StringUtils.isEmpty(requestHeader.getSign()) || ObjectUtils.isEmpty(requestHeader.getTimestamp()) || StringUtils.isEmpty(requestHeader.getNonceStr())) {
            responseFail(httpResponse, ResultCode.FORBIDDEN);
            return;
        }

        /*
         * 1.重放验证
         * 判断timestamp时间戳与当前时间是否超过60s（过期时间根据业务情况设置）,如果超过了就提示签名过期。
         */
        long now = System.currentTimeMillis();

        //时间差:是否大于120s:
        Long timeDiffer = (now - requestHeader.getTimestamp())/1000;
        if (timeDiffer > signMaxTime) {
            responseFail(httpResponse, ResultCode.SIGN_EXPIRED);
            return;
        }

        //2. 判断nonce
        boolean nonceExists = redisUtil.hasKey(NONCE_KEY + requestHeader.getNonceStr());
        if (nonceExists) {
            //signMaxTime内重复调用请求重复
            responseFail(httpResponse, ResultCode.REPLAY_ERROR);
            return;
        } else {
            redisUtil.set(NONCE_KEY + requestHeader.getNonceStr(), requestHeader.getNonceStr(), signMaxTime);
        }


        boolean accept;
        SortedMap<String, String> paramMap;
        switch (httpRequest.getMethod()) {
            case "GET":
                paramMap = HttpDataUtil.getUrlParams(requestWrapper);
                accept = thirdPartyCallService.verifySign(paramMap, requestHeader);
                break;
            case "POST":
                paramMap = HttpDataUtil.getBodyParams(requestWrapper);
                accept = thirdPartyCallService.verifySign(paramMap, requestHeader);
                break;
            default:
                accept = true;
                break;
        }
        if (accept) {
            filterChain.doFilter(requestWrapper, servletResponse);
        } else {
            responseFail(httpResponse, ResultCode.FORBIDDEN);
        }

    }

    @Override
    public void destroy() {

    }

    private void responseFail(HttpServletResponse httpResponse, ResultCode resultCode) {
        CommonResult<Object> resultData = CommonResult.failed(resultCode.getMessage());
        WebUtils.writeJson(httpResponse, resultData);
    }
}