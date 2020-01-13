package com.example.dipto.digitalmess;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
        import android.content.Context;
        import android.content.Intent;
        import android.support.design.widget.NavigationView;
        import android.support.design.widget.TabLayout;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.view.MenuItem;
        import android.widget.Toast;
        import com.example.dipto.digitalmess.fragment.Add_todays_bazar;
        import com.example.dipto.digitalmess.fragment.Add_todays_meal;
        import java.util.ArrayList;
        import java.util.List;

public class Before_Join extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_join);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter1 = new ViewPagerAdapter(getSupportFragmentManager());
        adapter1.addFragment(new Create_Mess(),"Create Mess");
        adapter1.addFragment(new View_personal_info(),"Personal Info");
        adapter1.addFragment(new Join_a_mess(), "Join A Mess");
        viewPager.setAdapter(adapter1);

    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}
