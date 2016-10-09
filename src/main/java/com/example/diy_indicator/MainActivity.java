package com.example.diy_indicator;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diy_indicator.fragmentView.Fragment_1;
import com.example.diy_indicator.fragmentView.Fragment_2;
import com.example.diy_indicator.fragmentView.Fragment_3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ViewIndicator viewIndicator;
    private ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏 透明
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initViewPager();
    }



    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewIndicator = (ViewIndicator) findViewById(R.id.view_indicator);

    }
    private void initData() {
        fragmentList.add(new Fragment_1());
        fragmentList.add(new Fragment_2());
        fragmentList.add(new Fragment_3());

        fragmentList.add(new Fragment_2());
        fragmentList.add(new Fragment_3());
        fragmentList.add(new Fragment_1());



    }
    private void initViewPager() {
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        viewIndicator.setViewPager(viewPager);
    }


}
