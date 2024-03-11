package cz.strancice.ttkacik.accountmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccountManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagementServiceApplication.class, args);
    }

}
