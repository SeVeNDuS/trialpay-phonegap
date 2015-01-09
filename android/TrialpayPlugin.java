package uk.mondosports.plugins.trialpay;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.util.Log;

import com.trialpay.android.base.TrialpayManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrialpayPlugin extends CordovaPlugin {

    private static final String LOGTAG = "TrialpayPlugin";
    private static final String CONSTANT_TOUCHPOINT_NAME = "TrialpayButton";
    private static final String DEFAULT_APP_KEY = "58414092a9d315137524873a0ee2218c";

    private static final String ACTION_INITIALIZE = "initialize";
    private static final String ACTION_SHOW_OFFERWALL = "showOfferwall";
    private static final String ACTION_SHOW_REWARDEDVIDEO = "showRewardedVideo";
    private static final String OPT_APPLICATION_KEY = "appKey";
    private static final String OPT_USER_ID = "userId";
    private static final String OPT_SECURITY_TOKEN = "securityToken";

    private String appKey = DEFAULT_APP_KEY;
    private String userId = "5043b715c3bd823b760000ff";
    private String securityToken = "";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;
        
        if (ACTION_INITIALIZE.equals(action)) {
            JSONObject options = args.optJSONObject(0);
            result = executeInitialize(options, callbackContext);
        } else if (ACTION_SHOW_OFFERWALL.equals(action)) {
            JSONObject options = args.optJSONObject(0);
            result = executeShowOfferwall(options, callbackContext);
        } else if (ACTION_SHOW_REWARDEDVIDEO.equals(action)) {
            JSONObject options = args.optJSONObject(0);
            result = executeShowRewardedVideo(options, callbackContext);
        }

        if (result != null) callbackContext.sendPluginResult( result );

        return true;
    }

    private PluginResult executeInitialize(JSONObject options, CallbackContext callbackContext) {
        Log.w(LOGTAG, "executeInitialize");
        
        this.initialize( options );
        
        callbackContext.success();

        return null;
    }

    private void initialize( JSONObject options ) {
        if(options.has(OPT_APPLICATION_KEY)) this.appKey = options.optString( OPT_APPLICATION_KEY );
        if(options.has(OPT_USER_ID)) this.userId = options.optString( OPT_USER_ID );
        
        TrialpayManager trialpayManager = TrialpayManager.getInstance(cordova.getActivity());
        trialpayManager.setSid(this.userId);
        trialpayManager.registerVic(CONSTANT_TOUCHPOINT_NAME, this.appKey);
    }

    private PluginResult executeShowOfferwall(JSONObject options, CallbackContext callbackContext) {
        Log.w(LOGTAG, "executeShowOfferwall");
        
        this.showOfferWall( options );
        
        callbackContext.success();

        return null;
    }

    private void showOfferWall(JSONObject options) {
        trialpayManager.open(CONSTANT_TOUCHPOINT_NAME);
    }
    
    private PluginResult executeShowRewardedVideo(JSONObject options, CallbackContext callbackContext) {
        Log.w(LOGTAG, "executeShowRewardedVideo");
        
        this.showRewardedVideo( );
        
        callbackContext.success();

        return null;
    }

    private void showRewardedVideo() {
        //ssaPub.showRewardedVideo();
    }
}