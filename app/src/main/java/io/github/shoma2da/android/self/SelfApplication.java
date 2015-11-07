package io.github.shoma2da.android.self;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import co.meyasuba.android.sdk.Meyasubaco;
import timber.log.Timber;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class SelfApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "9G4O1T132F572roEvApvSv394oVoYklq8CDPXDlh", "f1JdakedjMytcEhkfHesuNg6s4DTWXAYpAMaS4Ae");

        Meyasubaco.setApiKey(this, "a0450e91e19fd2aa2aa7d3cd1de9ce6d7d02f99a0b664b15f7cd3cf92cb70b50");

        Timber.plant(new Timber.DebugTree());
    }
}
