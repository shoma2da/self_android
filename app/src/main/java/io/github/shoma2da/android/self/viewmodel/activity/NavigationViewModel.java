package io.github.shoma2da.android.self.viewmodel.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import co.meyasuba.android.sdk.Meyasubaco;
import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.view.activity.LicenseActivity;
import io.github.shoma2da.android.self.view.activity.SettingActivity;

/**
 * Created by shoma2da on 2015/11/01.
 */
public class NavigationViewModel implements NavigationView.OnNavigationItemSelectedListener {

    private Activity mActivity;
    private DrawerLayout mDrawerLayout;

    public NavigationViewModel(Activity activity, DrawerLayout drawerLayout, NavigationView view) {
        mActivity = activity;
        mDrawerLayout = drawerLayout;

        view.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_license) {
            mActivity.startActivity(new Intent(mActivity, LicenseActivity.class));
        } else if (id == R.id.nav_comments) {
            Meyasubaco.showCommentActivity(mActivity);
        } else if (id == R.id.nav_helps) {
            Meyasubaco.showHelpListActivity(mActivity);
        } else if (id == R.id.nav_setting) {
            mActivity.startActivity(new Intent(mActivity, SettingActivity.class));
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String urlString = "https://play.google.com/store/apps/details?id=" + mActivity.getPackageName();
            intent.putExtra(Intent.EXTRA_TEXT, "便利に使える自分だけのチャットアプリ " + urlString);
            intent.setType("text/plain");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(intent);
        } else if (id == R.id.nav_store) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + mActivity.getPackageName()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(intent);
        }

        return true;
    }

    public void onPause() {
    }
}
