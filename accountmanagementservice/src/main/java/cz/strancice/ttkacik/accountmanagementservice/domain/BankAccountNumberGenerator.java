package cz.strancice.ttkacik.accountmanagementservice.domain;

import java.util.Random;

public class BankAccountNumberGenerator {

    private static final Random RANDOM = new Random();
    private static final int BANK_CODE = 100;

    public static String randomAccountNumber() {
        String prefix = RANDOM.nextInt(10) > 5 ? String.format("%06d", RANDOM.nextInt(1000000)) : "";
        String accountNumber = String.format("%010d", RANDOM.nextInt(1000000000));
        return String.format("%s-%s/%04d", prefix, accountNumber, BANK_CODE);
    }

}
