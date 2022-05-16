var exec = require('cordova/exec');

var WoosimPrinter = {
    coolMethod: function (arg0, success, error) {
		exec(success, error, 'WoosimCordovaPlugin', 'coolMethod', [arg0]);
	},

	PrintText: function (arg0, success, error) {
		exec(success, error, 'WoosimCordovaPlugin', 'PrintText', [arg0]);
	}
};

module.exports = WoosimPrinter;