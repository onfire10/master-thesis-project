package com.masterthesisproject.timekeepingservice.service;

import com.masterthesisproject.timekeepingservice.domain.WorkTime;
import com.masterthesisproject.timekeepingservice.model.WorkTimeTransfer;
import com.masterthesisproject.timekeepingservice.repository.WorkTimeRepository;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class TimeKeepingService {

    private final Logger log = LoggerFactory.getLogger(TimeKeepingService.class);

    private final WorkTimeRepository workTimeRepository;

    public TimeKeepingService(WorkTimeRepository workTimeRepository) {
        this.workTimeRepository = workTimeRepository;
    }

    public WorkTime save(WorkTime workTime) {
        log.info("Saving Work Time Object");
        return workTimeRepository.save(workTime);
    }

    public WorkTime convertToDomainObject(WorkTimeTransfer workTimeTransfer) throws NoSuchAlgorithmException {
        WorkTime workTime = new WorkTime();
        workTime.setEmployeeId(workTimeTransfer.getEmployeeId());
        workTime.setProjectId(workTimeTransfer.getProjectId());
        workTime.setFromTimestamp(workTimeTransfer.getFromTimestamp());
        workTime.setToTimestamp(workTimeTransfer.getToTimestamp());
        workTime.setInfo(workTimeTransfer.getInfo());
        workTime.setHash(calculateHash(workTimeTransfer));

        return workTime;
    }

    private String calculateHash(WorkTimeTransfer workTimeTransfer) throws NoSuchAlgorithmException {
        byte[] workTimeBinary = SerializationUtils.serialize(workTimeTransfer);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(workTimeBinary);
        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash){
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for(int i = 0; i < hash.length; i++){
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
