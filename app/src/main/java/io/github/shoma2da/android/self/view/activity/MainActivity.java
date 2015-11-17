package io.github.shoma2da.android.self.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import io.github.shoma2da.android.self.R;
import io.github.shoma2da.android.self.viewmodel.activity.MainActivityViewModel;
import io.github.shoma2da.android.self.viewmodel.activity.NavigationViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mMainActivityViewModel;
    private NavigationViewModel mNavigationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        mNavigationViewModel = new NavigationViewModel(this, drawer, navigationView);

        mMainActivityViewModel = new MainActivityViewModel(this);
        mMainActivityViewModel.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainActivityViewModel.confirmLogin();
        mMainActivityViewModel.showContents();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNavigationViewModel.onPause();
        mMainActivityViewModel.onPause();
    }
}
