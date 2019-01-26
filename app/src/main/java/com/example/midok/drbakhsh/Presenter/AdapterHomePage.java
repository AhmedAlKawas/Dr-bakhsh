package com.example.midok.drbakhsh.Presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.midok.drbakhsh.View.FragmentAppointment;
import com.example.midok.drbakhsh.View.FragmentComplaint;
import com.example.midok.drbakhsh.View.FragmentLabResults;
import com.example.midok.drbakhsh.View.FragmentRadiology;
import com.example.midok.drbakhsh.View.FragmentSurvey;

public class AdapterHomePage extends FragmentPagerAdapter {

    private String[] tabTitles = new String[5];

    public AdapterHomePage(FragmentManager fm) {
        super(fm);
        tabTitles[0] = "Appointment";
        tabTitles[1] = "Lab results";
        tabTitles[2] = "Radiology";
        tabTitles[3] = "Survey";
        tabTitles[4] = "Complaints";
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentAppointment();
            case 1:
                return new FragmentLabResults();
            case 2:
                return new FragmentRadiology();
            case 3:
                return new FragmentSurvey();
            case 4:
                return new FragmentComplaint();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
