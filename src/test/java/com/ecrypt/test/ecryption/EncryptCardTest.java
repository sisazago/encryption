package com.ecrypt.test.ecryption;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Hex;

public class EncryptCardTest {

    private static final String DESEDE_KEY = "50146911B88D22537B6560D272BE3C6A716305F9E49965EB";
    private static final String DESEDE_TRANSFORMATION = "DESede";
    private static final String DESEDE_CIPHER = "DESede/ECB/Nopadding";

    @Test
    public void encryptCardOrDate() throws Exception {
        /**
         * Para encriptar una fecha se debe de seguir el siguiente patron: mes representado con dos dígitos MM,
         * sigue el valor 0 y finalmente se pone el valor del año representado con dos dígitos YY, como este debe
         * ser un valor Hexadecimal se completa con FFFFFFFFFFF,
         * Ejemplo: si la fecha de expiración es 11/23 el valor a encriptar es: 11023FFFFFFFFFFF.
         *
         * Para encriptar las tarjetas se debe de tener en cuenta lo siguiente, estas deben de ser de 16 dígitos,
         * si se quiere encriptar mas de una tarjeta estas deben de estar separadas por comas y sin espacios
         * Ejemplo: 5587842453817293,5546272453817293,5512762453817293
         * */
        String value = "12020FFFFFFFFFFF";

        List<String> cards = Arrays.asList(value.split(","));

        for(String card : cards){
            String encrypted = desedeEncryptHex(card, DESEDE_KEY);
            System.out.println(encrypted);
        }
    }

    private static String desedeEncryptHex(String planText, String key) throws Exception {

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
            throw new Exception(e);
        }
    }
}
