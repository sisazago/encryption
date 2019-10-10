package com.ecrypt.test.ecryption.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.ecrypt.test.ecryption.common.PropertiesConstants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class EncryptionUtil {


    private static final String AES = "AES";
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static String desedeEncryptHex(String planText, String key) throws Exception{

        try {
            final byte[] secretKeyAsBytes = Hex.decodeHex(key.toCharArray());
            KeySpec keySpec = new DESedeKeySpec(secretKeyAsBytes);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PropertiesConstants.DESEDE_TRANSFORMATION);
            Cipher cipher = Cipher.getInstance(PropertiesConstants.DESEDE_CIPHER);
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
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PropertiesConstants.DESEDE_TRANSFORMATION);
            Cipher cipher = Cipher.getInstance(PropertiesConstants.DESEDE_CIPHER);
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

    /**
     * AES 256 encrypt plaintext with provided key and IV
     *
     * @param plainText - text to be encrypted
     * @param key       - Base64 encoded key
     * @param iv        - Base64 endoded IV
     * @return
     */
    public static String aesEncrypt(String plainText, String key, String iv) {

        final byte[] secretKey = DatatypeConverter.parseHexBinary(key);
        final byte[] initVector = DatatypeConverter.parseHexBinary(iv);

        Cipher cipher;
        String encrypted = "";
        try {
            cipher = Cipher.getInstance(AES_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey, AES), new IvParameterSpec(initVector, 0, cipher.getBlockSize()));
            encrypted = Base64.encodeBase64String(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));

            return encrypted;

        } catch (Exception e) {
            e.printStackTrace();;
        }

        return encrypted;
    }

    public static String aesDecrypt(String encrypted, String key, String iv) {

        InputStream cipherInputStream = null;
        String decryptedValue = "";
        try {
            final byte[] secretKey = javax.xml.bind.DatatypeConverter.parseHexBinary(key);
            final byte[] initVector = javax.xml.bind.DatatypeConverter.parseHexBinary(iv);
            final Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey, AES), new IvParameterSpec(initVector, 0, cipher.getBlockSize()));

            byte[] decodedValue = new Base64().decode(encrypted);
            byte[] decValue = cipher.doFinal(decodedValue);
            decryptedValue = new String(decValue);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cipherInputStream != null) {
                try {
                    cipherInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return decryptedValue;
    }
}
