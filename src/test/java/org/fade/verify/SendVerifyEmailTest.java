package org.fade.verify;

import org.fade.verify.utils.EmailUtil;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

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
//        String toEmail = "usingname@foxmail.com";
        //测试地址二(国外)
        String toEmail = "fadepi666@gmail.com";
        EmailUtil.init();
        try{
            EmailUtil.sendEmail(toEmail);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
