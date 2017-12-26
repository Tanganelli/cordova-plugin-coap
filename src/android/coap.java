package com.phodal.plugin;

import org.apache.cordova.*;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;

public class Coap extends CordovaPlugin {


    private class AsyncHandler implements CoapHandler {
        private CallbackContext callbackContext = null;
        public AsyncHandler(CallbackContext callbackContext){
            this.callbackContext = callbackContext;
        }
        @Override public void onLoad(CoapResponse response) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, response.getResponseText());
            result.setKeepCallback(false);
            callbackContext.sendPluginResult(result);
        }

        @Override public void onError() {
            PluginResult result = new PluginResult(PluginResult.Status.ERROR, "No response");
            result.setKeepCallback(false);
            callbackContext.sendPluginResult(result);
        }
    }
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }
    URI uri;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("get")) {
            Log.d("Coap ", "\n test for get");

            try {
                String uri_s = data.getString(0);
                int mediatype = -1;
                if(!data.isNull(1))
                    mediatype = data.getInt(1);
                if(!MediaTypeRegistry.getAllMediaTypes().contains(mediatype))
                    mediatype = -1;
                URI uri = new URI(data.getString(0));
                CoapClient mCoapClient = new CoapClient(uri);
                AsyncHandler my_handler = new AsyncHandler(callbackContext);
                if(mediatype!=-1)
                    mCoapClient.get(my_handler, mediatype);
                else
                    mCoapClient.get(my_handler);
                PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
                result.setKeepCallback(true);
                callbackContext.sendPluginResult(result);
                return true;
            } catch (URISyntaxException e) {
                Log.e("Coap", "URISyntaxException");
                callbackContext.error("URISyntaxException");
                return false;
            }
        } else if(action.equals("post")) {
            Log.d("Coap ", "\n test for post");

            try{
                String uri_s = data.getString(0);
                String payload_s = data.getString(1);
                int mediatype = MediaTypeRegistry.TEXT_PLAIN;
                if(!data.isNull(2))
                    mediatype = data.getInt(2);
                if(!MediaTypeRegistry.getAllMediaTypes().contains(mediatype))
                    mediatype = MediaTypeRegistry.TEXT_PLAIN;
                URI uri = new URI(uri_s);
                CoapClient mCoapClient = new CoapClient(uri);
                CoapResponse response = mCoapClient.post(payload_s, mediatype);
                callbackContext.success(response.getResponseText());
                return true;
            } catch (URISyntaxException e) {
                Log.e("Coap", "URISyntaxException");
                callbackContext.error("URISyntaxException");
                return false;
            }catch (JSONException e) {
                Log.e("Coap", "JSONException");
                callbackContext.error("JSONException");
                return false;
            } catch (Exception e) {
                Log.e("Coap", "Exception");
                callbackContext.error("Exception");
                return false;
            }
        } else if(action.equals("put")) {
            Log.d("Coap ", "\n test for put");
            try{
                String uri_s = data.getString(0);
                String payload_s = data.getString(1);
                int mediatype = MediaTypeRegistry.TEXT_PLAIN;
                if(!data.isNull(2))
                    mediatype = data.getInt(2);
                if(!MediaTypeRegistry.getAllMediaTypes().contains(mediatype))
                    mediatype = MediaTypeRegistry.TEXT_PLAIN;
                URI uri = new URI(uri_s);
                CoapClient mCoapClient = new CoapClient(uri);
                CoapResponse response = mCoapClient.put(payload_s, mediatype);
                callbackContext.success(response.getResponseText());
                return true;
            } catch (URISyntaxException e) {
                Log.e("Coap", "URISyntaxException");
                callbackContext.error("URISyntaxException");
                return false;
            }catch (JSONException e) {
                Log.e("Coap", "JSONException");
                callbackContext.error("JSONException");
                return false;
            } catch (Exception e) {
                Log.e("Coap", "Exception");
                callbackContext.error("Exception");
                return false;
            }
        } else if (action.equals("test")) {
            Log.d("Coap ", "\n test for get");

            try {
                URI uri = new URI("coap://iot.eclipse.org/test");
                CoapClient mCoapClient = new CoapClient(uri);
                CoapResponse response = mCoapClient.get();
                callbackContext.success(response.getResponseText());
                return true;
            } catch (URISyntaxException e) {
                Log.e("Coap", "URISyntaxException");
                callbackContext.error("URISyntaxException");
                return false;
            }
        } else{
            return false;
        }
    }
}