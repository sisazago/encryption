package com.ecrypt.test.ecryption;

import com.ecrypt.test.ecryption.common.PropertiesConstants;
import com.ecrypt.test.ecryption.util.CardUtils;
import com.ecrypt.test.ecryption.util.EncryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class EncryptCardTest {

    private static final Long MAX_CARD_VALUE = Long.valueOf("9999999999");

    private static final String DES_DATE_COMPLEMENT = "FFFFFFFFFFF";

    private static final String DES_STATIC_DATE = "12021";

    @Test
    public void encryptCardOrDate() throws Exception {

        String cardDate = DES_STATIC_DATE+DES_DATE_COMPLEMENT;

        String encryptedDate = EncryptionUtil.desedeEncryptHex(cardDate, PropertiesConstants.DESEDE_KEY);
        System.out.println("Encrypted Date: "+encryptedDate);
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
        String value = "454476";

        int numberOfCards = 0;

        String cardToValidate = "";
        Long cardNumber = Long.valueOf(30);
        while (cardNumber < MAX_CARD_VALUE ){
            String card = StringUtils.leftPad(cardNumber.toString(), 10, '2');
            cardToValidate = value+ card;

            if(CardUtils.isValidCreditCardNumber(cardToValidate)){
                System.out.println("------------------------------------------------------");
                String encrypted = EncryptionUtil.desedeEncryptHex(cardToValidate, PropertiesConstants.DESEDE_KEY);
                System.out.println("Test value ----> " + encrypted);
                String decrypt = EncryptionUtil.desedeDecryptHex(encrypted, PropertiesConstants.DESEDE_KEY);
                System.out.println("Card that was submit ---> " + decrypt);
                Assert.assertTrue(decrypt.equals(cardToValidate));
                System.out.println("------------------------------------------------------");

                if(numberOfCards == 20){
                    break;
                }else{
                    numberOfCards ++;
                }
            }

            cardNumber++;
        }

    }
}
