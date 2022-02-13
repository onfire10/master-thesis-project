package com.masterthesisproject.examplecustomer.controller;

import com.masterthesisproject.examplecustomer.domain.ServiceProviderWorkTime;
import com.masterthesisproject.examplecustomer.model.BlockchainEntry;
import com.masterthesisproject.examplecustomer.service.ServiceProviderWorkTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/example-customer-api")
public class WorkEntryController {

    private final ServiceProviderWorkTimeService serviceProviderWorkTimeService;

    public WorkEntryController(ServiceProviderWorkTimeService serviceProviderWorkTimeService) {
        this.serviceProviderWorkTimeService = serviceProviderWorkTimeService;
    }

    @PostMapping("/service-provider-work-time")
    public ResponseEntity<Void> updateServiceProviderWorkTimes(@RequestBody List<ServiceProviderWorkTime> workTimeList) {
        serviceProviderWorkTimeService.saveList(workTimeList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateWithBlockchain() throws Exception {
        boolean validation = serviceProviderWorkTimeService.validateHashes();
        return new ResponseEntity<>(validation, HttpStatus.OK);
    }

    @GetMapping("/blockchain-entries")
    public ResponseEntity<List<BlockchainEntry>> getAllEntries() throws Exception {
        List<BlockchainEntry> entries = serviceProviderWorkTimeService.getEntriesFromBlockchain();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }
}
