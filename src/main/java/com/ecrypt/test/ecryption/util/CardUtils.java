package com.ecrypt.test.ecryption.util;

import org.apache.commons.validator.routines.CreditCardValidator;

public class CardUtils {

    /**
     * Method used to validate the credit card number
     * <p>
     * @param creditCardNumber
     * @return true when the credit card number is valid
     */
    public static boolean isValidCreditCardNumber(String creditCardNumber) {
        CreditCardValidator validator = new CreditCardValidator();
        boolean isValidCard = validator.isValid(creditCardNumber);

        return isValidCard;
    }
}
