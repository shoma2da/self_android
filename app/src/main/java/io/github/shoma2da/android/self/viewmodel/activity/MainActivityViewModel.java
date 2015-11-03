package io.github.shoma2da.android.self.viewmodel.activity;

import android.content.Intent;
import android.util.Log;

import com.parse.ParseUser;

import io.github.shoma2da.android.self.model.User;
import io.github.shoma2da.android.self.view.activity.LoginActivity;
import io.github.shoma2da.android.self.view.activity.MainActivity;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class MainActivityViewModel {

    private MainActivity mMainActivity;

    public MainActivityViewModel(MainActivity activity) {
        mMainActivity = activity;
    }

    public void confirmLogin() {
        //ログインできていなければログイン画面を表示する
        User.get().isEmpty()
                .filter(b -> b)
                .subscribe(it -> {
                    Intent intent = new Intent(mMainActivity, LoginActivity.class);
                    mMainActivity.startActivity(intent);
                });

        //Debug
        User.get().subscribe(user -> Log.d("self", "user is " + ParseUser.getCurrentUser()));
    }

}
