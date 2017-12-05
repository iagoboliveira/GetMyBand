package com.project.iago.getmyband.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.project.iago.getmyband.R;
import com.project.iago.getmyband.fragments.HomeFragment;

public class NavigationActivity extends AppCompatActivity {

    private static final String TAG = NavigationActivity.class.getSimpleName();
    private TextView mTextUser;
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;

/*    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextUser.setText(R.string.title_home);
                    Log.i("MyBand", TAG+".onNavigationItemSelected() - navigation_home ");
                    fragment = new HomeFragment();
                    callFragment();
                    return true;
                case R.id.navigation_bands:
                    Log.i("MyBand", TAG+".onNavigationItemSelected() - navigation_bands ");
                    //mTextUser.setText(R.string.title_bands);
                    return true;
                case R.id.navigation_artists:
                    Log.i("MyBand", TAG+".onNavigationItemSelected() - navigation_artists ");
                    //mTextUser.setText(R.string.title_artists);
                    return true;
            }
            return false;
        }
    };*/

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mTextUser = (TextView) findViewById(R.id.txtViewShowUser);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String nameUser = getIntent().getStringExtra("EMAIL");
        mTextUser.setText("Welcome "+ nameUser);
        Log.i("MyBand", TAG+".onCreate() - INICIO 2- " +
                "email: "+nameUser);

        bottomNavigation = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigation.getMenu().clear();
        bottomNavigation.inflateMenu(R.menu.navigation);
        fragmentManager = getSupportFragmentManager();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_home:
                        //mTextUser.setText(R.string.title_home);
                        Log.i("MyBand", TAG+".onNavigationItemSelected() - navigation_home ");
                        fragment = new HomeFragment();
                        callFragment();
                        return true;
                    case R.id.navigation_bands:
                        Log.i("MyBand", TAG+".onNavigationItemSelected() - navigation_bands ");
                        //mTextUser.setText(R.string.title_bands);
                        return true;
                    case R.id.navigation_artists:
                        Log.i("MyBand", TAG+".onNavigationItemSelected() - navigation_artists ");
                        //mTextUser.setText(R.string.title_artists);
                        return true;
                }
                Log.i("MyBand", TAG+".onNavigationItemSelected() - vai fazer o inflate ");
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_navigation, fragment).commit();
                return true;
            }
        });
    }

    private void callFragment(){
        Log.i("MyBand", TAG+".callFragment()");
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_navigation, fragment).commit();
    }

}
