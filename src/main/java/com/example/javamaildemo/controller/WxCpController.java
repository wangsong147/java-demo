//package com.example.javamaildemo.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import me.chanjar.weixin.common.error.WxErrorException;
//import me.chanjar.weixin.cp.api.WxCpService;
//import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
//import me.chanjar.weixin.cp.bean.article.NewArticle;
//import me.chanjar.weixin.cp.bean.message.WxCpMessage;
//import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//@Slf4j
//@RestController
//@RequestMapping("wx/cp")
//public class WxCpController {
//    private final String EMPLOYEE_ID = "68821547";
//
//    @GetMapping("sendMsg")
//    public void sendMsg() {
//
//
//        // 创建消息
//        WxCpMessage message = WxCpMessage.NEWS()
//                .toUser(EMPLOYEE_ID)
//                .addArticle(NewArticle.builder()
//                        .title("title-题目")
//                        .picUrl("http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png")
//                        .url("URL")// 点击图片后跳转的链接
//                        .build()).build();
//        // 设置基本请求参数
//        WxCpDefaultConfigImpl configStorage = new WxCpDefaultConfigImpl();
////        configStorage.setCorpId(corpid);// 企业ID
////        configStorage.setCorpSecret(channelEntity.getChannelSecret());// agent_secret
////        configStorage.setAgentId(Integer.valueOf(channelEntity.getChannelAgentId()));// agentId
////        configStorage.setToken(channelEntity.getChannelToken());// 加密签名
////        configStorage.setAesKey(channelEntity.getChannelEncodingAesKey());
//
//        WxCpService wxCpService = new WxCpServiceImpl();
//        wxCpService.setWxCpConfigStorage(configStorage);
//
//        try {
//            wxCpService.getMessageService().send(message);
//        } catch (WxErrorException e) {
//            // 发送失败需要有个日志提示开发人员
//            log.error("消息发送失败 error message:{} error{}",e.getMessage(),e);
//        }
//    }
//
//}
