package com.masterthesisproject.smartcontractservice.service;

import com.masterthesisproject.smartcontractservice.TimeKeeping;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.math.BigInteger;

@Service
public class BlockchainService {

    private Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));

    private TimeKeeping contract;

    private static BlockchainService blockchainService;

    public static BlockchainService getInstance(){
        if(blockchainService == null){
            blockchainService = new BlockchainService();
        }
        return blockchainService;
    }

    private BlockchainService(){
        try {
            File pathname =  new File("smart-contract-service/src/main/java/com/masterthesisproject/smartcontractservice");
            String filename = WalletUtils.generateNewWalletFile("PASSWORD", pathname);

            Credentials credentials =
                    WalletUtils.loadCredentials(
                            "PASSWORD",
                            pathname + "/" + filename);

            contract = TimeKeeping.deploy(
                    web3j, credentials,
                    BigInteger.valueOf(0), BigInteger.valueOf(6721975)).send();
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }

    public TimeKeeping getContract() {
        return contract;
    }
}
