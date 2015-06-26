package io.pivotal.fe.gpdb.broker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.cloudfoundry.community.servicebroker.model.BrokerApiVersion;

import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan(basePackages = "io.pivotal.fe.gpdb.broker")
public class BrokerConfig {

  @Bean
  public BrokerApiVersion brokerApiVersion() {
    return new BrokerApiVersion();
  }

}
