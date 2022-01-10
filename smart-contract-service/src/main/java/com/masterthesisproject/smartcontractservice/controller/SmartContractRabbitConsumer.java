package com.masterthesisproject.smartcontractservice.controller;

import com.google.gson.Gson;
import com.masterthesisproject.smartcontractservice.model.WorkTimeEntry;
import com.masterthesisproject.smartcontractservice.service.TimeKeepingContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SmartContractRabbitConsumer {

    private final Logger log = LoggerFactory.getLogger(SmartContractRabbitConsumer.class);

    private final TimeKeepingContractService timeKeepingContractService;

    public SmartContractRabbitConsumer(TimeKeepingContractService timeKeepingContractService) {
        this.timeKeepingContractService = timeKeepingContractService;
    }

    @RabbitListener(queues = "smartContract.writeWorkTimeEntry.requests")
    public void receiveWorkTimeEntryRequests(String entryObject) {
        log.info("Received request on writeWorkTimeEntry queue");
        timeKeepingContractService.storeEntryToBlockchain(new Gson().fromJson(entryObject, WorkTimeEntry.class));
    }
}
