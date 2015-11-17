package io.github.shoma2da.android.self.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.model.User;

/**
 * Created by shoma2da on 2015/11/07.
 */
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("設定");

        getFragmentManager().beginTransaction()
                .replace(R.id.content, new SettingFragment())
                .commit();
    }

    public static class SettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            User.get().isEmpty().forEach(isExists -> {
                //タイトルを設定
                String menuTitle = isExists ? "ログアウト" : "アカウント作成";
                Preference account = findPreference("account");
                account.setTitle(menuTitle);

                //挙動を設定
                account.setOnPreferenceClickListener(pref -> {
                    if (isExists) {

                    } else {

                    }
                    return true;
                });
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //アクションバーの戻るを押したときの処理
        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
