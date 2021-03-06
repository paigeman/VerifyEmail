package org.fade.verify.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
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
    //TODO: use your own email
    private static String fromEmail = "@qq.com";
//    private static String fromEmail = "@163.com";
//    private static String fromEmail = "@gmail.com";

    //发送者密码
    //TODO: use your own password
    private static String fromEmailPw = "";
//    private static String fromEmailPw = "";
//    private static String fromEmailPw = "";

    //发送邮箱服务器
    private static String myEmailSMTPHost = "smtp.qq.com";
//    private static String myEmailSMTPHost = "smtp.gmail.com";

    /**
     * 初始化
     * @throws GeneralSecurityException
     * */
    public static void init() throws GeneralSecurityException {
        props = new Properties();
        //设置发送协议为smtp
        props.setProperty("mail.transport.protocol", "smtp");
        //设置发送邮箱服务器
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        //设置为需要认证
        props.setProperty("mail.smtp.auth", "true");
        //设置开启SSL,Gmail必须开启,QQ或网易可以设置不开启
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
//        props.setProperty("mail.smtp.socketFactory.port", "465");
        //Gmail请设置代理，需魔法上网，
        // 相应服务器地址、端口请更换为自己的代理服务器地址和端口
//        props.setProperty("mail.smtp.socks.host","127.0.0.1");
//        props.setProperty("mail.smtp.socks.port","7890");
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
