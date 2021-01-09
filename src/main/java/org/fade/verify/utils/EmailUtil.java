package org.fade.verify.utils;

import javax.mail.Session;
import java.util.Properties;

/**
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
    public void init(){
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

    

}
