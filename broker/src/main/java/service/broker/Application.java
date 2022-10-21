package service.broker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        if (args.length>=1 ) {
            LocalBrokerService.services = Arrays.asList(args[0].split(","));//setting the arguments passed as the services
        }
        SpringApplication.run(Application.class, args
        );
    }
}
