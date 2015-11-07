package com.nidarooms.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.nidarooms.R;
import com.nidarooms.fragment.MyBookingFragment;
import com.nidarooms.fragment.MyProfileFragment;
import com.nidarooms.fragment.SearchFragment;

/**
 * HomeActivity is the landing page of Application.
 * The User can choose Country, City and Near by Hotels.
 * The User can also access the dashboard.
 *
 * @author Harish
 * @version 1.0 04/11/2015
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Set Toolbar (Action Bar)
    Toolbar toolbar;

    // Layout for User's Dashboard
    DrawerLayout drawer;

    // Toggle for Layout Close and Open
    ActionBarDrawerToggle toggle;

    // Set View for Navigation different Pages
    NavigationView navigationView;

    CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Assign Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Fragment for Home Page
        SearchFragment fragment1 = new SearchFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment1);
        fragmentTransaction.commit();

    }

    /**
     * Back Press to close drawer.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {

            // Fragment for Search Page
            SearchFragment fragment11 = new SearchFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment11);
            setTitle("NIDA Rooms");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_my_profile) {

            // Fragment for My Profile
            MyProfileFragment fragment2 = new MyProfileFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment2);
            setTitle("My Profile");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_my_booking) {

            // Fragment for My Booking
            MyBookingFragment fragment3 = new MyBookingFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment3);
            setTitle("My Booking");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_logout) {
            Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

}
