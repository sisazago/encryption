package com.ecrypt.test.ecryption;

import org.junit.Assert;
import org.junit.Test;

public class EncryptCardTest {

    private static final String DESEDE_KEY = "50146911B88D22537B6560D272BE3C6A716305F9E49965EB";

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
        String value = "5475142315756318";

        String encrypted = EncryptionUtil.desedeEncryptHex(value, DESEDE_KEY);
        System.out.println(encrypted);
        String decrypt = EncryptionUtil.desedeDecryptHex(encrypted, DESEDE_KEY);
        System.out.println(decrypt);
        Assert.assertTrue(decrypt.equals(value));
    }
}
