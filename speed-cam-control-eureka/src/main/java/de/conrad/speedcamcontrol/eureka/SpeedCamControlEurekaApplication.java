package de.conrad.speedcamcontrol.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpeedCamControlEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedCamControlEurekaApplication.class, args);
    }
}
