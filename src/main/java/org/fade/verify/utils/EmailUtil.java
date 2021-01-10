package org.fade.verify.utils;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件工具类
 * @author fade
 * */
public class EmailUtil {

    //用于参数配置
    private static Properties props;

    //用于创建会话对象
    private static Session session;

    //发送者邮箱
    private static String fromEmail = "976050219@qq.com";

    //发送者密码
    private static String fromEmailPw = "kwezlraefvuybfej";

    //发送邮箱服务器
    private static String myEmailSMTPHost = "smtp.qq.com";

    /**
     * 初始化
     * */
    public static void init(){
        props = new Properties();
        //设置发送协议为smtp
        props.setProperty("mail.transport.protocol", "smtp");
        //设置发送邮箱服务器
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        //设置为需要认证
        props.setProperty("mail.smtp.auth", "true");
        session = Session.getInstance(props);
        //设置为debug模式，可查看log
        session.setDebug(true);
    }

    /**
     * 创建MIME类型邮件
     * @param toEmail 接收人邮件地址
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     * */
    public static MimeMessage creatMimeMail(String toEmail) throws UnsupportedEncodingException, MessagingException {
        MimeMessage msg = new MimeMessage(session);
        //发件人
        msg.setFrom(new InternetAddress(fromEmail,"验证码发送系统", "UTF-8"));
        //接件人
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(toEmail));
        //邮件主题
        msg.setSubject("验证码","UTF-8");
        //生成验证码
        String verificationCode = VerificationCodeUtil.generateVerificationCode(4);
        //邮件正文
        msg.setContent("您好，您的验证码是："+verificationCode+"。", "text/html;charset=UTF-8");
        //发送时间
        msg.setSentDate(new Date());
        //保存设置
        msg.saveChanges();
        return msg;
    }

    /**
     * 发送邮件
     * @param toEmail 接收人邮件地址
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * */
    public static void sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {
        Transport transport = session.getTransport();
        transport.connect(fromEmail,fromEmailPw);
        MimeMessage msg = creatMimeMail(toEmail);
        transport.sendMessage(msg,msg.getAllRecipients());
        System.out.println("邮件发送成功");
        transport.close();
    }

}
