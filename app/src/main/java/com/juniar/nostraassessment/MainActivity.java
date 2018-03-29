package com.juniar.nostraassessment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.juniar.nostraassessment.calculate.CalculateFragment;
import com.juniar.nostraassessment.contact.ContactFragment;
import com.juniar.nostraassessment.listnumber.ListNumberFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    NavigationView navView;
    MenuItem navCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.calculate_text));

        navView = findViewById(R.id.nav_view);
        navCalculate = navView.getMenu().findItem(R.id.nav_calculate);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_body, new CalculateFragment())
                .commit();

        navCalculate.setChecked(true);

        navigationView.setNavigationItemSelectedListener(this);
    }

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
        int id = item.getItemId();
        Fragment fragment=null;

        if (id == R.id.nav_calculate) {
            fragment=new CalculateFragment();
            getSupportActionBar().setTitle(R.string.calculate_text);
        } else if (id == R.id.nav_list_number) {
            fragment=new ListNumberFragment();
            getSupportActionBar().setTitle(R.string.list_number_text);
        } else if (id == R.id.nav_contact) {
            fragment=new ContactFragment();
            getSupportActionBar().setTitle(R.string.contact_text);
        }

        if(fragment!=null){
            fragmentManager.beginTransaction()
                    .replace(R.id.container_body,fragment)
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
