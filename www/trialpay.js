function TrialpayPlugin() {}

TrialpayPlugin.prototype.initialize = function (appKey, userId, securityToken, success, error) {
	cordova.exec(success, error, 'Trialpay', 'initialize', [appKey, userId, securityToken]);
};

TrialpayPlugin.prototype.showOfferwall = function (success, error) {
	cordova.exec(success, error, 'Trialpay', 'showOfferwall', []);
};

TrialpayPlugin.prototype.showRewardedVideo = function (success, error) {
	cordova.exec(success, error, 'Trialpay', 'showRewardedVideo', []);
};

module.exports = new TrialpayPlugin();