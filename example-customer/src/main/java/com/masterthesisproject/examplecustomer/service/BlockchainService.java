package com.masterthesisproject.examplecustomer.service;

import com.masterthesisproject.examplecustomer.TimeKeeping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;

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
            String walletPath = "example-customer/src/main/resources";
            String walletFilename = "UTC--2022-02-13T17-12-35.862248200Z--281e4d3dbac621979784834c8740653b94ddd180.json";
            String contractAddress = "0xE397F480C3e62c8f0E79E1A188aC10e33f962833";

            Credentials credentials =
                    WalletUtils.loadCredentials(
                            "PASSWORD",
                            walletPath + "/" + walletFilename);



            System.out.println(contractAddress);
            contract = TimeKeeping.load(contractAddress, web3j, credentials,BigInteger.valueOf(0), BigInteger.valueOf(6721975));
            System.out.println("Contract loaded succesfully");
        }
        catch (Exception e){
            System.out.println("Error loading contract");
        }
    }

    public TimeKeeping getContract() {
        return contract;
    }
}
