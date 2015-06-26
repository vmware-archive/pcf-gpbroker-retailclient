package io.pivotal.fe.retaildata.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.cloudfoundry.CloudFoundryConnector;

@SpringBootApplication
public class RetaildataClientApplication {

    public static void main(String[] args) {
    	
    	CloudFoundryConnector cfConnector = new CloudFoundryConnector();
        if (cfConnector.isInMatchingCloud()) {        	
            System.setProperty("spring.profiles.active", "cloud");            
        }else{
        	System.setProperty("spring.profiles.active", "default");
        }

        SpringApplication.run(RetaildataClientApplication.class, args);
    }
}
