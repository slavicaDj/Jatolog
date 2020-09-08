package com.praksa.ucenik.jatolog.activity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.adapter.PagerAdapter;

import java.util.Locale;


public class ActivityHome extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

       /* SinhronizacijaThread st = new SinhronizacijaThread(this);
        st.start();
        try {
            st.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("util", MODE_PRIVATE);
        String localePref = sharedPreferences.getString("locale","sr");
        Locale.setDefault(new Locale(localePref));
        Configuration config = new Configuration();
        config.locale = new Locale(localePref);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.pretraga));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.top_rijeci));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.podesavanja :
                Intent intent = new Intent(this,ActivityPodesavanja.class);
                startActivity(intent);
                return true;
            default :
                return false;
        }
    }
    }