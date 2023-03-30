package com.example.javamaildemo.security.controller;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.javamaildemo.common.WxConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("/api/oauth2/wx")
public class OAuth2Controller {
    @Value("${wechat.appid}")
    private String wechatAppId;

    @Value("${wechat.secret}")
    private String wechatSecret;

    @Value("${wechat.redirect_uri}")
    private String wechatRedirectUri;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 返回二维码，用户扫码授权以后会跳转redirect_uri并携带参数code
     *
     * @return {@link String}
     */
    @GetMapping("/login")
    public String getWxCode() {
        // 1.获取code
        String url = String.format("https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=%s" +
                "&state=%s#wechat_redirect", WxConstant.APP_ID, "");
        return "redirect:" + url;
    }

    /**
     * 授权以后
     * 跳转回调url，即当前接口，接收到code
     *
     * @param code  代码
     * @param state 状态
     * @return {@link String}
     */
    @GetMapping("callback")
    public String callback(@RequestParam("code") String code, String state) {
        // 2.获取access_token + openid
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + wechatAppId
                + "&secret="
                + wechatSecret
                + "&code="
                + code
                + "&grant_type=authorization_code";

        String accessTokenResult = restTemplate.getForObject(accessTokenUrl, String.class);
        JSONObject json = JSON.parseObject(accessTokenResult);

        String accessToken = json.getString("access_token");
        String openid = json.getString("openid");

        // 4.添加信息到数据库：openid，nickname，headerimgurl
//        if (findUserByOpenid(openid) == null) {
        // 3. 获取用户信息
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid;
        HashMap userInfoResult = restTemplate.getForObject(userInfoUrl, HashMap.class);
//        JSONObject userJson = JSON.parseObject(userInfoResult);
//        }
        String token = JWTUtil.createToken(userInfoResult, "key".getBytes());

        // 前端拿到token，解析用户信息渲染页面
        return "redirect:" + "http://localhost:3000?token=" + token;
    }
}
