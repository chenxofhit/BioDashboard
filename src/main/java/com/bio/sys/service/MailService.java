package com.bio.sys.service;


import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.bio.common.domain.MailBean;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author lance(ZYH)
 * @function 邮件发送工具类
 * @date 2018-07-07 13:14
 */

//#邮箱配置
//mail:
//  host: smtp.qq.com
//  username: chenxofhit@qq.com
//  #QQ邮箱的授权码
//  password: coptfizwrqambbgf
//  port: 465
//  default-encoding: UTF-8
//  properties:
//    mail.smtp.socketFactory.fallback : true
//    mail.smtp.starttls.enable: true

@Component
public class MailService {
	
	
    @Value("${biodashboard.mail.sender}")
    private String MAIL_SENDER; //邮件发送者

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration; //freemarker

    private Logger logger = LoggerFactory.getLogger(MailService.class);

    /**
     * 发送一个简单格式的邮件
     *
     * @param mailBean
     */
    public void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom(MAIL_SENDER);
            //邮件接收人
            simpleMailMessage.setTo(mailBean.getRecipient());
            //邮件主题
            simpleMailMessage.setSubject(mailBean.getSubject());
            //邮件内容
            simpleMailMessage.setText(mailBean.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("邮件发送失败", e.getMessage());
        }
    }
//
//    /**
//     * 发送一个HTML格式的邮件
//     *
//     * @param mailBean
//     */
//    public void sendHTMLMail(MailBean mailBean) {
//        MimeMessage mimeMailMessage = null;
//        try {
//            mimeMailMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//            mimeMessageHelper.setFrom(MAIL_SENDER);
//            mimeMessageHelper.setTo(mailBean.getRecipient());
//            mimeMessageHelper.setSubject(mailBean.getSubject());
//            StringBuilder sb = new StringBuilder();
//            sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
//                    .append("\"<p style='color:#F00'>你是真的太棒了！</p>")
//                    .append("<p style='text-align:right'>右对齐</p>");
//            mimeMessageHelper.setText(sb.toString(), true);
//            javaMailSender.send(mimeMailMessage);
//        } catch (Exception e) {
//            logger.error("邮件发送失败", e.getMessage());
//        }
//    }
//
//    /**
//     * 发送带附件格式的邮件
//     *
//     * @param mailBean
//     */
//    public void sendAttachmentMail(MailBean mailBean) {
//        MimeMessage mimeMailMessage = null;
//        try {
//            mimeMailMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//            mimeMessageHelper.setFrom(MAIL_SENDER);
//            mimeMessageHelper.setTo(mailBean.getRecipient());
//            mimeMessageHelper.setSubject(mailBean.getSubject());
//            mimeMessageHelper.setText(mailBean.getContent());
//            //文件路径
//            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
//            mimeMessageHelper.addAttachment("mail.png", file);
//
//            javaMailSender.send(mimeMailMessage);
//        } catch (Exception e) {
//            logger.error("邮件发送失败", e.getMessage());
//        }
//    }
//
//    /**
//     * 发送带静态资源的邮件
//     *
//     * @param mailBean
//     */
//    public void sendInlineMail(MailBean mailBean) {
//        MimeMessage mimeMailMessage = null;
//        try {
//            mimeMailMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//            mimeMessageHelper.setFrom(MAIL_SENDER);
//            mimeMessageHelper.setTo(mailBean.getRecipient());
//            mimeMessageHelper.setSubject(mailBean.getSubject());
//            mimeMessageHelper.setText("<html><body>带静态资源的邮件内容，这个一张IDEA配置的照片:<img src='cid:picture' /></body></html>", true);
//            //文件路径
//            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
//            mimeMessageHelper.addInline("picture", file);
//
//            javaMailSender.send(mimeMailMessage);
//        } catch (Exception e) {
//            logger.error("邮件发送失败", e.getMessage());
//        }
//    }

    /**
     * 发送基于Freemarker模板的邮件
     *
     * @param mailBean
     */
    public void sendTemplateMail(MailBean mailBean, String templateName,  Map<String, Object> model) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(MAIL_SENDER,"noreply@biodashboard.com"));
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());

            Template template = configuration.getTemplate(templateName);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeMessageHelper.setText(text, true);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("邮件发送失败", e.getMessage());
        }

    }


}