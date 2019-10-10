package com.ecrypt.test.ecryption;

import com.ecrypt.test.ecryption.common.PropertiesConstants;
import com.ecrypt.test.ecryption.util.EncryptionUtil;
import org.junit.Test;

public class DecryptCardsTest {

    @Test
    public void decryptCarsAndDates() throws Exception {

        String encryptedCard = "FE2F5D51430CC36C";
        String encryptedDate = "5E06684BF4FD9E1F";

        decryptInformation(encryptedCard, encryptedDate);
    }

    private void decryptInformation (String encryptedCard, String encryptedDate){
        try{
            System.out.println("------------------------------------------------------");
            System.out.println("Card number : "+EncryptionUtil.desedeDecryptHex(encryptedCard, PropertiesConstants.DESEDE_KEY));
            System.out.println("Date : "+EncryptionUtil.desedeDecryptHex(encryptedDate, PropertiesConstants.DESEDE_KEY));
            System.out.println("------------------------------------------------------");
        }catch (Exception e){

        }
    }

}
