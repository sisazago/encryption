package com.ecrypt.test.ecryption;

import com.ecrypt.test.ecryption.util.EncryptionUtil;
import org.junit.Assert;
import org.junit.Test;

public class DesKeyCreationTest {

    private static final String TEST_VALUE = "4544762222222234";

    @Test
    public void createKeyTest()throws Exception{

        String createdKey = EncryptionUtil.desEdeKeyCreation();

        String encryptedValue = EncryptionUtil.desedeEncryptHex(TEST_VALUE, createdKey);

        String decryptedValue = EncryptionUtil.desedeDecryptHex(encryptedValue, createdKey);

        Assert.assertTrue(TEST_VALUE.equals(decryptedValue));
    }
}
