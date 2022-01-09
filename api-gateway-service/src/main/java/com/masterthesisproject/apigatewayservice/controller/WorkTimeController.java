package com.masterthesisproject.apigatewayservice.controller;

import com.google.gson.Gson;
import com.masterthesisproject.apigatewayservice.model.WorkTime;
import com.masterthesisproject.apigatewayservice.service.WorkTimeRabbitProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-keeping")
public class WorkTimeController {

    private final Logger log = LoggerFactory.getLogger(WorkTimeController.class);

    private final WorkTimeRabbitProducer workTimeRabbitProducer;

    public WorkTimeController(WorkTimeRabbitProducer workTimeRabbitProducer) {
        this.workTimeRabbitProducer = workTimeRabbitProducer;
    }

    @PostMapping("/work-time")
    public ResponseEntity<WorkTime> createWorkTime(@RequestBody WorkTime worktime) {
        log.info("Received POST request Work Time Object");
        WorkTime createdWorkTime = new Gson().fromJson(workTimeRabbitProducer.createWorkTime(worktime), WorkTime.class);

        return new ResponseEntity<>(createdWorkTime, HttpStatus.OK);
    }
}
