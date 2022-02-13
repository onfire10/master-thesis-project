package com.masterthesisproject.examplecustomer.service;

import com.masterthesisproject.examplecustomer.TimeKeeping;
import com.masterthesisproject.examplecustomer.domain.ServiceProviderWorkTime;
import com.masterthesisproject.examplecustomer.model.BlockchainEntry;
import com.masterthesisproject.examplecustomer.repository.ServiceProviderWorkTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProviderWorkTimeService {

    private final Logger log = LoggerFactory.getLogger(ServiceProviderWorkTimeService.class);
    private final ServiceProviderWorkTimeRepository serviceProviderWorkTimeRepository;

    public ServiceProviderWorkTimeService(ServiceProviderWorkTimeRepository serviceProviderWorkTimeRepository) {
        this.serviceProviderWorkTimeRepository = serviceProviderWorkTimeRepository;
    }

    public void saveList(List<ServiceProviderWorkTime> workTimeList) {
        for(ServiceProviderWorkTime workTime : workTimeList){
            prepareIdsForSave(workTime);
        }
    }

    public void save(ServiceProviderWorkTime workTime){
        serviceProviderWorkTimeRepository.save(workTime);
    }

    public boolean validateHashes() throws Exception {
        List<ServiceProviderWorkTime> workTimes = serviceProviderWorkTimeRepository.findAll();

        for(ServiceProviderWorkTime workTime : workTimes){
            log.info("EntryID for comparison: {}", workTime.getServiceProviderEntityId());
            if(!compareHash(workTime)){
                return false;
            }
        }
        return true;
    }

    public List<BlockchainEntry> getEntriesFromBlockchain() throws Exception {
        TimeKeeping timeKeeping = BlockchainService.getInstance().getContract();
        List<ServiceProviderWorkTime> workTimes = serviceProviderWorkTimeRepository.findAll();
        List<BlockchainEntry> entryList = new ArrayList<>();
        for(ServiceProviderWorkTime workTime : workTimes){
            BlockchainEntry entry = new BlockchainEntry();
            entry.setEntryId(workTime.getServiceProviderEntityId());
            entry.setHash(timeKeeping.entries(workTime.getServiceProviderEntityId()).send().component2());
            entryList.add(entry);
        }
        return entryList;
    }

    private void prepareIdsForSave(ServiceProviderWorkTime workTime){
        workTime.setServiceProviderEntityId(workTime.getId());
        workTime.setId(null);
        workTime.setServiceProvider("Example-Service-Provider");
        save(workTime);
    }

    private boolean compareHash(ServiceProviderWorkTime workTime) throws Exception {
        TimeKeeping timeKeeping = BlockchainService.getInstance().getContract();
        String blockchainHash = timeKeeping.entries(workTime.getServiceProviderEntityId()).send().component2();
        log.info("Block Hash: {}", blockchainHash);
        log.info("Entry Hash: {}", workTime.getHash());
        log.info("Result of comparison: {}", blockchainHash.equals(workTime.getHash()));
        return blockchainHash.equals(workTime.getHash());
    }
}
