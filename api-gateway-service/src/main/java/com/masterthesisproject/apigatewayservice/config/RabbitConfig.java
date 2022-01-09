package com.masterthesisproject.apigatewayservice.config;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange exchangeCreateWorkTime() {
        return new DirectExchange("rest.createWorkTime");
    }

}
