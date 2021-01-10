package org.fade.verify.utils;

import java.util.Random;

/**
 * 生成验证码工具类
 * @author fade
 * */
public class VerificationCodeUtil {

    /**
     * 生成n位验证码
     * @param length 验证码长度
     * */
    public static String generateVerificationCode(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i<length;i++){
            //type为当前位类型：小写字母(2)、数字(0)、大写字母(1)
            int type = random.nextInt(3);
            int content = 0;
            switch (type){
                case 0:
                    content = random.nextInt(10) + 48;
                    break;
                case 1:
                    content = random.nextInt(26) + 65;
                    break;
                case 2:
                    content = random.nextInt(26) + 97;
                    break;
                default:
                    break;
            }
            sb.append((char)content);
        }
        return sb.toString();
    }

}
