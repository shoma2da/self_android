package io.github.shoma2da.android.self.view.activity;

import android.app.Activity;
import android.os.Bundle;

import io.github.shoma2da.android.self.R;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onBackPressed() {
        //disabled!
    }
}
