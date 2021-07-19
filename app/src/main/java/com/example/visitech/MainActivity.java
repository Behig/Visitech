package com.example.visitech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * The first activity of the app, which is the activity of HomeFragment
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class MainActivity extends AppCompatActivity {
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the bottom navigation menu.
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // The default fragment of thia activity is the HomeFragment.
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    /**
     * This object is a listener for the bottom navigation menu.
     *
     * We can go back to HomeFragment, NewPatientFragment, InfoFragment and UserFragment.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selecteFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selecteFragment = new HomeFragment();
                            break;
                        case R.id.nav_add:
                            selecteFragment = new NewPatientFragment();
                            break;
                        case R.id.nav_info:
                            selecteFragment = new InfoFragment();
                            break;
                        case R.id.nav_user:
                            selecteFragment = new UserFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selecteFragment).commit();
                    return true;
                }
            };

    /**
     * If we press back, the whole activity will finish.
     */
    @Override
    public void onBackPressed() {
        finish();
    }
}
