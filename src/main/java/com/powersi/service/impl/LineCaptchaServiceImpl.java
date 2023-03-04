package com.powersi.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.alibaba.druid.util.StringUtils;
import com.powersi.common.api.CommonResult;
import com.powersi.common.exception.customException.MethodArgumentException;
import com.powersi.common.service.RedisService;
import com.powersi.controller.LineCaptchaController;
import com.powersi.service.LineCaptchaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author：Jinbiao
 * @Date：2023/3/4 18:25
 * @Desc：生成图形验证码Service
 * 业务场景:app登录，用户输入图形验证码的内容->再输入手机号获取短信验证码->登录。
 * 两种实现方案:
 * 1.基于redis，以key+手机号作为唯一业务标识
 * 2.基于session，在获取图形验证码接口的时候把图形验证码和手机号一并存入session里面，每个用户独立拥有一个 session，下次请求过来都可以从session中拿到当前用户的图形验证码和手机号
 */
@Service
public class LineCaptchaServiceImpl implements LineCaptchaService {

    @Autowired
    RedisService redisService;

    private static final Log logger = LogFactory.getLog(LineCaptchaController.class);


    /**
     * 当前登录获取图形验证码业务唯一key
     */
    private static final String lineCaptchaKey = "loginVerify:lineCaptcha";


    @Override
    public CommonResult<String> generateLineCaptcha1(HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        try {
            String phone = request.getParameter("phone");
            if (StringUtils.isEmpty(phone)) {
                throw new MethodArgumentException("phone不能为空");
            }
            // 取得输出流
            out = response.getOutputStream();
            //定义图形验证码的长、宽、验证码字符数、干扰线宽度
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);

            //图形验证码写出，可以写出到文件，也可以写出到流
            //captcha.write("/Users/sunww/Desktop/shear.png");
            captcha.write(out);

            //验证图形验证码的有效性，返回boolean值
            boolean checkPass = captcha.verify(captcha.getCode());
            logger.info(checkPass);

            //保证业务请求验证码的唯一性:
            redisService.set(lineCaptchaKey+phone,captcha.getCode(),3600);
            // 将缓存中的数据立即强制刷新, 将缓冲区的数据输出到客户端浏览器
            out.flush();
            // 关闭输出流
            out.close();
            return CommonResult.success("图形验证码获取成功!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return CommonResult.failed("图片获取失败");
    }

    @Override
    public CommonResult<String> verifyCode1(HttpServletRequest request) {
        String verifyCode = request.getParameter("verifyCode");
        String phone = request.getParameter("phone");
        if (StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(phone)) {
            throw new MethodArgumentException("验证码或手机号不能为空");
        }
        String truthVerifyCode = (String) redisService.get(lineCaptchaKey + phone);
        if(StringUtils.isEmpty(truthVerifyCode)){
           logger.info("truthVerifyCode为空");
           return CommonResult.failed("验证失败");
        }
        // 验证码不区分大小写
        if (verifyCode.toLowerCase().equals(truthVerifyCode.toLowerCase())) {
            return CommonResult.success("验证成功!");
        }
        return CommonResult.failed("验证失败");
    }


    /**
     * 方案2：基于session实现
     * 引申：
     * Session 技术是依赖 Cookie 技术的服务器端的数据存储技术。
     * 由服务器进行创建默认存储时间为 30分钟，session解决了同一个用户不同请求的数据共享问题。
     * session的作用域：一次会话。浏览器不关闭，session不失效， 则同一用户的任意请求获取的都是同一个session
     * 每个用户独立拥有一个 session
     * 作用：解决了一个用户的不同请求的数据共享问题。
     * @param request
     * @param response
     * @return
     */
    @Override
    public CommonResult<String> generateLineCaptcha2(HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        try {
            String phone = request.getParameter("phone");
            if (StringUtils.isEmpty(phone)) {
                throw new MethodArgumentException("phone不能为空");
            }
            //取得输出流
            out = response.getOutputStream();
            //定义图形验证码的长、宽、验证码字符数、干扰线宽度
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
            captcha.write(out);

            //验证图形验证码的有效性，返回boolean值
            boolean checkPass = captcha.verify(captcha.getCode());
            logger.info(checkPass);

            // 将生成的验证码code以及手机号信息存入sessoin中
            request.getSession().setAttribute("verifyCode", captcha.getCode());
            request.getSession().setAttribute("phone",phone);

            // 将缓存中的数据立即强制刷新, 将缓冲区的数据输出到客户端浏览器
            out.flush();
            // 关闭输出流
            out.close();
            return CommonResult.success("图形验证码获取成功!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return CommonResult.failed("图片获取失败");
    }

    /**
     * 测试:
     * 1.浏览器关闭了，session就失效了，得重新获取图形验证码了
     * 2.在谷歌浏览器上获取图形验证码&登录校验通过。  在Apipost工具上用统一验证码测登录校验，没有session，提示"当前会话失效，请刷新验证码重试"
     * 3.在谷歌浏览器上用手机号a获取图形验证码&登录校验通过。在Microsoft Edge浏览器用手机号b获取图形验证码&登录校验通过， 用谷歌浏览器上a的验证码到Microsoft Edge浏览器测试登录校验，不通过。  测试达到预期结果：session隔离，验证码不能串。
     * @param request
     * @return
     */
    @Override
    public CommonResult<String> verifyCode2(HttpServletRequest request) {
        // 获取存放在session中的验证码
        String verifyCode = request.getParameter("verifyCode");
        String truthVerifyCode = (String) request.getSession().getAttribute("verifyCode");
        String phone = (String) request.getSession().getAttribute("phone");
        if (StringUtils.isEmpty(verifyCode)) {
            throw new MethodArgumentException("验证码或手机号不能为空");
        }
        if (StringUtils.isEmpty(truthVerifyCode) || StringUtils.isEmpty(phone)) {
            throw new MethodArgumentException("当前会话失效，请刷新验证码重试");
        }
        if (verifyCode.equals(truthVerifyCode)) {
            return CommonResult.success("验证成功!");
        }
        return CommonResult.failed("验证失败");
    }
}
