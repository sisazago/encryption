package com.ecrypt.test.ecryption;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class EncryptCardTest {

    private static final String DESEDE_KEY = "50146911B88D22537B6560D272BE3C6A716305F9E49965EB";

    private static final Long MAX_CARD_VALUE = Long.valueOf("9999999999");

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
        String value = "547514";

        String cardToValidate = "";
        Long cardNumber = Long.valueOf(0);
        while (cardNumber < MAX_CARD_VALUE ){
            String card = StringUtils.leftPad(cardNumber.toString(), 10, '0');
            cardToValidate = value + card;

            if(CardUtils.isValidCreditCardNumber(cardToValidate)){
                String encrypted = EncryptionUtil.desedeEncryptHex(cardToValidate, DESEDE_KEY);
                System.out.println(encrypted);
                String decrypt = EncryptionUtil.desedeDecryptHex(encrypted, DESEDE_KEY);
                System.out.println(decrypt);
                Assert.assertTrue(decrypt.equals(cardToValidate));
            }else{
                System.out.println("Not valid: "+ cardToValidate);
            }

            cardNumber++;
        }

    }
}
