var TimeKeeping = artifacts.require("TimeKeeping.sol");

module.exports = function (deployer) {
    deployer.deploy(TimeKeeping);
};