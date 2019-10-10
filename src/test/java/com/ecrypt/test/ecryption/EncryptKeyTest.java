package com.ecrypt.test.ecryption;

import com.ecrypt.test.ecryption.common.PropertiesConstants;
import com.ecrypt.test.ecryption.util.EncryptionUtil;
import org.junit.Assert;
import org.junit.Test;

public class EncryptKeyTest {

    private final static String key = "86c6eeb45d6bbb7216288a8bce556511987ff0432c61c9a80436f7fefe223a2a";
    private final static String iv = "462c74eb31104ff584c109c13a0c073b";

    @Test
    public void encryptDesKey(){

        String encryptValue = EncryptionUtil.aesEncrypt(PropertiesConstants.DESEDE_KEY, key, iv);
        //String encryptValue ="kXuem5yatQN23AH2EpoOCs+qk+fLRBYgEgDIMS2jdpfAQYb8HrQXdx3nL4yoeU2d4Pln3HDPxwOaZduKXLHQYw==";
        System.out.println(encryptValue);

        String decrypt = EncryptionUtil.aesDecrypt(encryptValue, key, iv);

        //Assert.assertTrue(PropertiesConstants.DESEDE_KEY.equals(decrypt));

        System.out.println(decrypt);

    }
}
