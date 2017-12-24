/*global cordova, module*/

var Coap = {
    get: function (request, successCallback, errorCallback) {
        if (typeof successCallback !== "function") {
            console.log("get second parameter must be a function to handle responses.");
            return;
        }
        var options = [];
        if (request === null || typeof request !== "object") {
            console.log("Coap: Invalid parameter. request not an object.");
            return;
        }
        if (request.URI !== undefined && request.URI !== "") {
            options.push(request.URI);
        }
        else {
            console.log("Coap: No URI given.");
            return false;
        }
        if (request.mediatype !== undefined && request.mediatype !== -1) {
            options.push(request.mediatype);
        }


        cordova.exec(successCallback, errorCallback, "Coap", "get", options);
    },
    post: function (request, successCallback, errorCallback) {
        if (typeof successCallback !== "function") {
            console.log("post second parameter must be a function to handle responses.");
            return;
        }
        var options = [];
        if (request === null || typeof request !== "object") {
            console.log("Coap: Invalid parameter. request not an object.");
            return;
        }
        if (request.URI !== undefined && request.URI !== "") {
            options.push(request.URI);
        }
        else {
            console.log("Coap: No URI given.");
            return false;
        }
        if (request.payload !== undefined) {
            options.push(request.payload);
        }
        else {
            console.log("Coap: No payload given.");
            return false;
        }
        if (request.mediatype !== undefined && request.mediatype !== -1) {
            options.push(request.mediatype);
        }
        cordova.exec(successCallback, errorCallback, "Coap", "post", options);
    },
    put: function (request, successCallback, errorCallback) {
        if (typeof successCallback !== "function") {
            console.log("put second parameter must be a function to handle responses.");
            return;
        }
        var options = [];
        if (request === null || typeof request !== "object") {
            console.log("Coap: Invalid parameter. request not an object.");
            return;
        }
        if (request.URI !== undefined && request.URI !== "") {
            options.push(request.URI);
        }
        else {
            console.log("Coap: No URI given.");
            return false;
        }
        if (request.payload !== undefined) {
            options.push(request.payload);
        }
        else {
            console.log("Coap: No payload given.");
            return false;
        }
        if (request.mediatype !== undefined && request.mediatype !== -1) {
            options.push(request.mediatype);
        }
        cordova.exec(successCallback, errorCallback, "Coap", "put", options);
    },
    test: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Coap", "test");
    }
};

module.exports = Coap;