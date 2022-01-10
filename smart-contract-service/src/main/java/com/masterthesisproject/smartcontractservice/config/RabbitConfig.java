package com.masterthesisproject.smartcontractservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //=================RECEIVING FROM TIMEKEEPING================

    @Bean
    public Queue createWorkTimeEntryQueue() {
        return new Queue("smartContract.writeWorkTimeEntry.requests");
    }

    @Bean
    public DirectExchange exchangeCreateWorkTimeEntry() {
        return new DirectExchange("timeKeeping.createWorkTimeEntry");
    }

    @Bean
    public Binding bindingWorkTime(DirectExchange exchangeCreateWorkTimeEntry, Queue createWorkTimeEntryQueue) {
        return BindingBuilder.bind(createWorkTimeEntryQueue)
                .to(exchangeCreateWorkTimeEntry)
                .with("smartContract.createWorkTimeEntry");
    }
}
