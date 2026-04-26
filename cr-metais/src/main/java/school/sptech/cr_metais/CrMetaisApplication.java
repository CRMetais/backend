package school.sptech.cr_metais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrMetaisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrMetaisApplication.class, args);
    }

}
