package com.masterthesisproject.timekeepingservice.service;

import com.google.gson.Gson;
import com.masterthesisproject.timekeepingservice.domain.WorkTime;
import com.masterthesisproject.timekeepingservice.model.BlockchainEntry;
import com.masterthesisproject.timekeepingservice.model.WorkTimeTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class TimeKeepingRabbitConsumer {

    private final Logger log = LoggerFactory.getLogger(TimeKeepingRabbitConsumer.class);

    private final TimeKeepingService timeKeepingService;
    private final TimeKeepingRabbitProducer timeKeepingRabbitProducer;

    public TimeKeepingRabbitConsumer(TimeKeepingService timeKeepingService, TimeKeepingRabbitProducer timeKeepingRabbitProducer) {
        this.timeKeepingService = timeKeepingService;
        this.timeKeepingRabbitProducer = timeKeepingRabbitProducer;
    }

    @RabbitListener(queues = "timeKeeping.createWorkTime.requests")
    public String receiveWorkTimeRequests (String workTimeObject) throws NoSuchAlgorithmException {
        WorkTime workTime = timeKeepingService.convertToDomainObject(new Gson().fromJson(workTimeObject, WorkTimeTransfer.class));
        workTime = timeKeepingService.save(workTime);

        log.info("Placing write request to blockchain into queue");
        timeKeepingRabbitProducer.sendToSmartContract(createEntryObject(workTime));

        log.info("Returning created Work Time Object: {}", workTime.getId());
        return new Gson().toJson(workTime);
    }

    private BlockchainEntry createEntryObject(WorkTime workTime){
        BlockchainEntry entry = new BlockchainEntry();
        entry.setEntryId(workTime.getId());
        entry.setHash(workTime.getHash());
        return entry;
    }
}
