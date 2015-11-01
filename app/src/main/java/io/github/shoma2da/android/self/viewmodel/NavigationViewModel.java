package io.github.shoma2da.android.self.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.view.LicenseActivity;

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

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_license) {
            mActivity.startActivity(new Intent(mActivity, LicenseActivity.class));
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onPause() {
    }
}
