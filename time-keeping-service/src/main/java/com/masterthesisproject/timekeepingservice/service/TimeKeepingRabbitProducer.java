package com.masterthesisproject.timekeepingservice.service;

import com.google.gson.Gson;
import com.masterthesisproject.timekeepingservice.domain.WorkTime;
import com.masterthesisproject.timekeepingservice.model.BlockchainEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeKeepingRabbitProducer {

    private final Logger log = LoggerFactory.getLogger(TimeKeepingRabbitProducer.class);

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchangeCreateWorkTimeEntry;

    public void send(DirectExchange directExchange, String routingKey, String message){
        try{
            template.convertAndSend(directExchange.getName(), routingKey, message);
        } catch (Exception e){
            e.getMessage();
        }
    }

    public void sendToSmartContract(BlockchainEntry blockchainEntry) {
        String routingKey = "smartContract.createWorkTimeEntry";
        String message = new Gson().toJson(blockchainEntry);
        send(exchangeCreateWorkTimeEntry, routingKey, message);
    }
}
