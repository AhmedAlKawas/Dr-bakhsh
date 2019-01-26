package com.example.midok.drbakhsh.View;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.midok.drbakhsh.Presenter.AdapterHomePage;
import com.example.midok.drbakhsh.R;

public class HomePageFragmentView extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_fragment_view);

        viewPager = findViewById(R.id.home_page_view_pager);
        tabLayout = findViewById(R.id.home_page_tab_layout);

        android.support.v4.app.FragmentManager fm =getSupportFragmentManager();
        AdapterHomePage adapterHomePage = new AdapterHomePage(fm);
        viewPager.setAdapter(adapterHomePage);
        tabLayout.setupWithViewPager(viewPager);

    }
}
