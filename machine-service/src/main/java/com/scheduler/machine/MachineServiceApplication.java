package com.scheduler.machine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MachineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MachineServiceApplication.class, args);
    }
}