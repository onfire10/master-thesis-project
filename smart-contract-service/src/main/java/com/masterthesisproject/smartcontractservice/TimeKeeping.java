package com.masterthesisproject.smartcontractservice;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class TimeKeeping extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b5061087d806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806375a76f85146100465780637aa1e1781461017a578063f8756c01146102cc575b600080fd5b6100ff6004803603602081101561005c57600080fd5b810190808035906020019064010000000081111561007957600080fd5b82018360208201111561008b57600080fd5b803590602001918460018302840111640100000000831117156100ad57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929050505061046c565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561013f578082015181840152602081019050610124565b50505050905090810190601f16801561016c5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6102ca6004803603604081101561019057600080fd5b81019080803590602001906401000000008111156101ad57600080fd5b8201836020820111156101bf57600080fd5b803590602001918460018302840111640100000000831117156101e157600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561024457600080fd5b82018360208201111561025657600080fd5b8035906020019184600183028401116401000000008311171561027857600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929050505061057a565b005b610385600480360360208110156102e257600080fd5b81019080803590602001906401000000008111156102ff57600080fd5b82018360208201111561031157600080fd5b8035906020019184600183028401116401000000008311171561033357600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050610639565b604051808060200180602001838103835285818151815260200191508051906020019080838360005b838110156103c95780820151818401526020810190506103ae565b50505050905090810190601f1680156103f65780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561042f578082015181840152602081019050610414565b50505050905090810190601f16801561045c5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b60606001826040518082805190602001908083835b602083106104a45780518252602082019150602081019050602083039250610481565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206001018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561056e5780601f106105435761010080835404028352916020019161056e565b820191906000526020600020905b81548152906001019060200180831161055157829003601f168201915b50505050509050919050565b6040518060400160405280838152602001828152506001836040518082805190602001908083835b602083106105c557805182526020820191506020810190506020830392506105a2565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008201518160000190805190602001906106149291906107a3565b5060208201518160010190805190602001906106319291906107a3565b509050505050565b600181805160208101820180518482526020830160208501208183528095505050505050600091509050806000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106fb5780601f106106d0576101008083540402835291602001916106fb565b820191906000526020600020905b8154815290600101906020018083116106de57829003601f168201915b505050505090806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107995780601f1061076e57610100808354040283529160200191610799565b820191906000526020600020905b81548152906001019060200180831161077c57829003601f168201915b5050505050905082565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106107e457805160ff1916838001178555610812565b82800160010185558215610812579182015b828111156108115782518255916020019190600101906107f6565b5b50905061081f9190610823565b5090565b61084591905b80821115610841576000816000905550600101610829565b5090565b9056fea265627a7a72315820a1c35fbab59b57b48ad7072e3f898caecd3ac657037c4057457ff73e1455d11664736f6c63430005100032";

    public static final String FUNC_ENTRIES = "entries";

    public static final String FUNC_ADDENTRYHASH = "addEntryHash";

    public static final String FUNC_GETENTRYHASH = "getEntryHash";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected TimeKeeping(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TimeKeeping(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TimeKeeping(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TimeKeeping(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple2<String, String>> entries(String param0) {
        final Function function = new Function(FUNC_ENTRIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple2<String, String>>(function,
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> addEntryHash(String entryId, String newEntryHash) {
        final Function function = new Function(
                FUNC_ADDENTRYHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(entryId), 
                new org.web3j.abi.datatypes.Utf8String(newEntryHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getEntryHash(String entryID) {
        final Function function = new Function(
                FUNC_GETENTRYHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(entryID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TimeKeeping load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TimeKeeping(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TimeKeeping load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TimeKeeping(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TimeKeeping load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TimeKeeping(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TimeKeeping load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TimeKeeping(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TimeKeeping> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TimeKeeping.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TimeKeeping> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TimeKeeping.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TimeKeeping> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TimeKeeping.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TimeKeeping> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TimeKeeping.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
