package com.masterthesisproject.apigatewayservice.service;

import com.google.gson.Gson;
import com.masterthesisproject.apigatewayservice.model.WorkTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkTimeRabbitProducer {

    private final Logger log = LoggerFactory.getLogger(WorkTimeRabbitProducer.class);
    private String routingKey = "timeKeeping.createWorkTime";

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchangeCreateWorkTime;

    public String send(DirectExchange directExchange, String routingKey, String message){
        String response = "";
        try{
            response = template.convertSendAndReceive(directExchange.getName(), routingKey, message).toString();
            log.info("Got Rabbit response: {}", response);
        } catch (Exception e){
            e.getMessage();
        }
        return response;
    }

    public String createWorkTime(WorkTime workTime) {
        return send(exchangeCreateWorkTime, routingKey, new Gson().toJson(workTime));
    }
}
