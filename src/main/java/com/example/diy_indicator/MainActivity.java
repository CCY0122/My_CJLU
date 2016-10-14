package com.example.diy_indicator;


import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.diy_indicator.fragmentView.fragment_1.Fragment_1;
import com.example.diy_indicator.fragmentView.fragment_2.Fragment_2;
import com.example.diy_indicator.fragmentView.Fragment_3.Fragment_3;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

public class MainActivity extends AppCompatActivity {


    private ViewIndicator viewIndicator;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | lp.flags);//沉浸式状态栏 透明
        }
        setContentView(R.layout.activity_main);
        
        initBmob();

        initView();

        initData();

        initViewPager();
    }

    private void initBmob() {
        Bmob.initialize(this, "e0534a119023df75eb89a91d0b9d8e30");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation().save();
        // 启动推送服务
        BmobPush.startWork(this);
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewIndicator = (ViewIndicator) findViewById(R.id.view_indicator);
        fragment1  = new Fragment_1();
        fragment2 = new Fragment_2();
        fragment3 = new Fragment_3();

    }
    private void initData() {
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(new Fragment_2());


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
    public Fragment getFragment(int index){
        viewPager.setCurrentItem(index);
        return fragmentList.get(index);
    }


}
