pragma solidity >=0.4.22 <0.9.0;

contract TimeKeeping {

    struct WorkTimeEntry {
        string entryId;
        string hash;
    }

    string[] entryHashes;

    mapping(string => WorkTimeEntry) public entries;

    function addEntryHash(string memory entryId, string memory newEntryHash) public {
        entries[entryId] = WorkTimeEntry(entryId, newEntryHash);
    }

    function getEntryHash(string memory entryID) public returns (string memory){
        return entries[entryID].hash;
    }

}