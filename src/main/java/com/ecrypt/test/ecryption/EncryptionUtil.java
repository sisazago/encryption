package com.ecrypt.test.ecryption;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;

public class EncryptionUtil {

    private static final String DESEDE_TRANSFORMATION = "DESede";
    private static final String DESEDE_CIPHER = "DESede/ECB/NoPadding";


    public static String desedeEncryptHex(String planText, String key) throws Exception{

        try {
            final byte[] secretKeyAsBytes = Hex.decodeHex(key.toCharArray());
            KeySpec keySpec = new DESedeKeySpec(secretKeyAsBytes);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DESEDE_TRANSFORMATION);
            Cipher cipher = Cipher.getInstance(DESEDE_CIPHER);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] plainTextBytes = Hex.decodeHex(planText.toCharArray());
            byte[] encryptedTextBytes = cipher.doFinal(plainTextBytes);

            String encryptedText = Hex.encodeHexString(encryptedTextBytes);

            return encryptedText.toUpperCase();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static String desedeDecryptHex(String encryptedText, String key) throws Exception {

        try {
            final byte[] secretKeyBytes = Hex.decodeHex(key.toCharArray());
            KeySpec keySpec = new DESedeKeySpec(secretKeyBytes);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DESEDE_TRANSFORMATION);
            Cipher cipher = Cipher.getInstance(DESEDE_CIPHER);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decodeHexBytes = Hex.decodeHex(encryptedText.toLowerCase().toCharArray());
            byte[] decryptedBytes = cipher.doFinal(decodeHexBytes);
            String decryptedText = new String(Hex.encodeHex(decryptedBytes));

            return decryptedText.toUpperCase();
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
