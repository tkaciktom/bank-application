package cz.strancice.ttkacik.cardmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CardManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardManagementServiceApplication.class, args);
    }

}
