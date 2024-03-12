package cz.strancice.ttkacik.productbundlemanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductBundleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductBundleServiceApplication.class, args);
    }

}
