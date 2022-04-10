package com.masterthesisproject.smartcontractservice.service;

import com.masterthesisproject.smartcontractservice.TimeKeeping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.math.BigInteger;

@Service
public class BlockchainService {
/*
    @Value("${blockchain-util.wallet.filename}")
    private String walletFilename;

    @Value("${blockchain-util.wallet.path}")
    private String walletPath;

    @Value("${blockchain-util.contract.address}")
    private String contractAddress;
*/
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
            /*
            File pathname =  new File("smart-contract-service/src/main/resources");
            String filename = WalletUtils.generateNewWalletFile("PASSWORD", pathname);
            */
            String walletPath = "smart-contract-service/src/main/resources";
            String walletFilename = "UTC--2022-02-13T16-09-00.276895500Z--17a065b8f3f300dbdedbcd2fd6ee7e21fd5ca7e6.json";
            String contractAddress = "0xE397F480C3e62c8f0E79E1A188aC10e33f962833";

            Credentials credentials =
                    WalletUtils.loadCredentials(
                            "PASSWORD",
                            walletPath + "/" + walletFilename);

            /*
            contract = TimeKeeping.deploy(
                    web3j, credentials,
                    BigInteger.valueOf(0), BigInteger.valueOf(6721975)).send();
             */
            contract = TimeKeeping.load(contractAddress, web3j, credentials,BigInteger.valueOf(0), BigInteger.valueOf(6721975));
            System.out.println("Contract Loaded successfully");
        }
        catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    public TimeKeeping getContract() {
        return contract;
    }
}
