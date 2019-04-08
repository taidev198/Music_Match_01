package com.sunasterisk.musixmatch.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.ui.base.BaseActivity;
import com.sunasterisk.musixmatch.ui.main.home.HomeFragment;
import com.sunasterisk.musixmatch.ui.music.FragmentMusic;
import com.sunasterisk.musixmatch.ui.search.SearchActivity;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponents() {
        mBottomNavigationView = findViewById(R.id.navigation_main);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mBottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    @Override
    protected void initData() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                addFragment(HomeFragment.newInstance());
                break;
            case R.id.action_music:
                addFragment(FragmentMusic.newInstance());
                break;
            case R.id.action_search:
                jumpToSearchScreen();
                break;
        }
        return true;
    }

    private void addFragment(Fragment fragment) {
        Fragment oldFragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (oldFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(oldFragment)
                    .commit();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_main, fragment)
                .commit();
    }

    private void jumpToSearchScreen() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
