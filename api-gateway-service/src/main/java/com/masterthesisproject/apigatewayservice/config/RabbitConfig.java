package com.masterthesisproject.apigatewayservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /*
    @Bean
    public Queue createAccountQueue() {
        return new Queue("govote.createAccount.requests");
    }

    @Bean
    public DirectExchange exchangeCreateAccount() {
        return new DirectExchange("auth.createAccount");
    }

    @Bean
    public Binding bindingCreateAccount(DirectExchange exchangeCreateAccount, Queue createAccountQueue) {
        return BindingBuilder.bind(createAccountQueue)
                .to(exchangeCreateAccount)
                .with("govote.createAccount");
    }

    @Bean
    public DirectExchange exchangeSendMeeting() {
        return new DirectExchange("govote.sendMeeting");
    }
    */
}
