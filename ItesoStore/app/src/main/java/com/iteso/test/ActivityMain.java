package com.iteso.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.iteso.test.beans.ItemProduct;

public class ActivityMain extends AppCompatActivity {

    private FragmentTechnology fragmentTechnology;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            ItemProduct modifiedItem = data.getParcelableExtra("ITEM");
            int productIndex = modifiedItem.getCode();
            fragmentTechnology.modifyItem(modifiedItem,productIndex);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_log_out:
                SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashScreen.MY_PREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NAME", "");
                editor.putString("PWD", "");
                editor.putBoolean("LOGGED", false);
                editor.apply();

                Intent logOutIntent = new Intent(ActivityMain.this, ActivityLogin.class);
                startActivity(logOutIntent);
                finish();

                break;


            case R.id.menu_privacy_policy:
                Intent intent = new Intent(ActivityMain.this, ActivityPrivacyPolicy.class);
                startActivity(intent);
                break;
    }
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0: if(fragmentTechnology == null)
                    fragmentTechnology = new FragmentTechnology();
                    return fragmentTechnology;
                case 1:  return new FragmentHome();
                case 2:  return new FragmentElectronics();
                default: return new FragmentTechnology();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:  return getString(R.string.technology_header);
                case 1:  return getString(R.string.home_header);
                case 2:  return getString(R.string.electronics_header);
                default: return getString(R.string.technology_header);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format,
                    getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }
    }

}
