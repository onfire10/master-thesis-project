package com.masterthesisproject.smartcontractservice.service;

import com.masterthesisproject.smartcontractservice.TimeKeeping;
import com.masterthesisproject.smartcontractservice.model.WorkTimeEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TimeKeepingContractService {

    private final Logger log = LoggerFactory.getLogger(TimeKeepingContractService.class);

    public void storeEntryToBlockchain(WorkTimeEntry entry) {
        log.info("Received Blockchain Write for Entry: {}", entry.getEntryId());
        TimeKeeping timeKeeping = BlockchainService.getInstance().getContract();
        try {
            log.info("Sending entry to blockchain");
            log.info("entry ID: {}, entryHash: {}", entry.getEntryId(), entry.getHash());
            timeKeeping.addEntryHash(entry.getEntryId(), entry.getHash()).send();
            log.info("Successful write to blockchain");
        } catch (Exception exception) {
            log.info("Write to blockchain failed");
            exception.printStackTrace();
        }
    }

    public String getContractAddress() {
        try {
            return BlockchainService.getInstance().getContract().getDeployedAddress("5777");
        }catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
    }
}
