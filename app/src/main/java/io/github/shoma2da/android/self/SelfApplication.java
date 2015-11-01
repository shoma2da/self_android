package io.github.shoma2da.android.self;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class SelfApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "9G4O1T132F572roEvApvSv394oVoYklq8CDPXDlh", "f1JdakedjMytcEhkfHesuNg6s4DTWXAYpAMaS4Ae");
    }
}
