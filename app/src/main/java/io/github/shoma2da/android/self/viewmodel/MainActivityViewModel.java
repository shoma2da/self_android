package io.github.shoma2da.android.self.viewmodel;

import android.content.Intent;

import io.github.shoma2da.android.self.model.User;
import io.github.shoma2da.android.self.view.LoginActivity;
import io.github.shoma2da.android.self.view.MainActivity;

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
    }

}
