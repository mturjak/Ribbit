package com.newtpond.ribbt;

import android.app.Application;

import com.deploygate.sdk.DeployGate;
import com.parse.Parse;

/**
 * Created by martinturjak on 12/30/14.
 */
public class RibbitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "Nasr5RWhl5eyrYY5RnWcgDaJq5kU4QRbHlZwy95i", "hq6Da4CNp6yVRzt9F08krQfstMaYWnrMLU0DQIhQ");

        DeployGate.install(this);
    }
}
