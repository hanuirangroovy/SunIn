package com.ssafy.sunin.config;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JasyptConfigTest {
//    @Test
//    void jasypt(){
//        String url = "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";
//        String username = "B2jNek8jz2";
//        String password = "N1OAubO2IS0B6enFwFSCpt1FXVFXCnsO";
//        String secret = "ssafy";
//
//        String encryptUrl = jasyptEncrypt(url);
//        String encryptUsername = jasyptEncrypt(username);
//        String encryptPassword = jasyptEncrypt(password);
//        String encryptSecret = jasyptEncrypt(secret);
//
//        System.out.println("encryptUrl : " + encryptUrl);
//        System.out.println("encryptUsername : " + encryptUsername);
//        System.out.println("encryptPassword : " + encryptPassword);
//        System.out.println("encryptSecret : " + encryptSecret);
//
//        String decryptUrl = jasyptDecryt(encryptUrl);
//        String decryptUsername = jasyptDecryt(encryptUsername);
//        String decryptPassword = jasyptDecryt(encryptPassword);
//        String decryptSecret = jasyptDecryt(encryptSecret);
//
//        System.out.println("decryptUrl : " + decryptUrl);
//        System.out.println("decryptUsername : " + decryptUsername);
//        System.out.println("decryptPassword : " + decryptPassword);
//        System.out.println("decryptSecret : " + decryptSecret);
//
//        Assertions.assertThat(url).isEqualTo(jasyptDecryt(encryptUrl));
//    }
//
//    private String jasyptEncrypt(String input) {
//        String key = "ssafy";
//        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//        encryptor.setAlgorithm("PBEWithMD5AndDES");
//        encryptor.setPassword(key);
//        return encryptor.encrypt(input);
//    }
//
//    private String jasyptDecryt(String input){
//        String key = "ssafy";
//        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//        encryptor.setAlgorithm("PBEWithMD5AndDES");
//        encryptor.setPassword(key);
//        return encryptor.decrypt(input);
//    }
}