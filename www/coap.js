/*global cordova, module*/

var Coap = {
    get: function (name, successCallback, errorCallback) {
        if (typeof successCallback !== "function") {
            console.log("get first parameter must be a function to handle responses.");
            return;
        }
        cordova.exec(successCallback, errorCallback, "Coap", "get", [name]);
    },
    post: function (name, options, successCallback, errorCallback) {
        if (typeof successCallback !== "function") {
            console.log("post first parameter must be a function to handle responses.");
            return;
        }
        cordova.exec(successCallback, errorCallback, "Coap", "post", [name, options]);
    },
    put: function (name, options, successCallback, errorCallback) {
        if (typeof successCallback !== "function") {
            console.log("put first parameter must be a function to handle responses.");
            return;
        }
            cordova.exec(successCallback, errorCallback, "Coap", "put", [name, options]);
    },
    test: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Coap", "test");
    }
};

module.exports = Coap;