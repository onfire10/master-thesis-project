package com.masterthesisproject.timekeepingservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //=================RECEIVING FROM REST================

    @Bean
    public Queue createWorkTimeQueue() {
        return new Queue("timeKeeping.createWorkTime.requests");
    }

    @Bean
    public DirectExchange exchangeCreateWorkTime() {
        return new DirectExchange("rest.createWorkTime");
    }

    @Bean
    public Binding bindingWorkTime(DirectExchange exchangeCreateWorkTime, Queue createWorkTimeQueue) {
        return BindingBuilder.bind(createWorkTimeQueue)
                .to(exchangeCreateWorkTime)
                .with("timeKeeping.createWorkTime");
    }

    //=================SENDING WRITES TO BLOCKCHAIN================

    @Bean
    public DirectExchange exchangeCreateWorkTimeEntry() {
        return new DirectExchange("timeKeeping.createWorkTimeEntry");
    }
}
