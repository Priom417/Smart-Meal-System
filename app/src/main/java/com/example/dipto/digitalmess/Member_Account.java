package com.example.dipto.digitalmess;
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

public class Member_Account extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    //private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public void showToast(String string){
        Context context = getApplicationContext();
        CharSequence text = string;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_account);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_id);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_id);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(navigationView!=null){
            navigationView.setNavigationItemSelectedListener(this);
        }
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add_member_id){
            showToast("yes");
        }
        else if(id==R.id.mess_structure_id){
            showToast("yes");
        }
        else if(id==R.id.meal_chart_id){
            showToast("yes");

        }
        else if(id==R.id.view_total_bazar_id){
            showToast("yes");

        }
        else if(id==R.id.bazar_order_id){
            showToast("yes");

        }
        else if(id==R.id.mess_info_id){
            showToast("yes");
        }
        else if(id==R.id.calculation_id){
            showToast("yes");

        }
        else if(id==R.id.settings_id){
            showToast("yes");

        }
        else if(id==R.id.logout_id){
            Context context = getApplicationContext();
            Intent i = new Intent(context,MainActivity.class);
            startActivity(i);
        }
        return true;
    }




    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter1 = new ViewPagerAdapter(getSupportFragmentManager());
        adapter1.addFragment(new Add_todays_bazar(),"Add Todays Bazar");
        adapter1.addFragment(new Add_todays_meal(), "Add Todays Meal");
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
