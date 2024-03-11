package cz.strancice.ttkacik.cardmanagementservice.domain;

import java.util.Random;

public class CardNumberGenerator {

    private static final Random RANDOM = new Random();
    private static final int BANK_CODE = 100;

    public static String randomCardNumber() {
        String prefix = RANDOM.nextInt(10) > 5 ? String.format("%06d", RANDOM.nextInt(1000000)) : "";
        String accountNumber = String.format("%010d", RANDOM.nextInt(1000000000));
        return prefix.isEmpty()
                ? String.format("%s/%04d", accountNumber, BANK_CODE)
                : String.format("%s-%s/%04d", prefix, accountNumber, BANK_CODE);
    }

}
