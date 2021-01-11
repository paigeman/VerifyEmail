package org.fade.verify;

import org.fade.verify.utils.EmailUtil;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * 发送验证邮件测试类
 * @author fade
 * */
public class SendVerifyEmailTest {

    /**
     * 测试发送验证码邮件
     * */
    @Test
    public void testSendVerifyEmail() {
        //测试地址一(国内)
//        String toEmail = "@foxmail.com";
        //测试地址三(国内网易)
        String toEmail = "@163.com";
        //测试地址二(国外)
//        String toEmail = "@gmail.com";
        try {
            EmailUtil.init();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        try{
            EmailUtil.sendEmail(toEmail);
//            System.setProperty("http.proxyHost", "127.0.0.1");
//            System.setProperty("http.proxyPort", "7890");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
