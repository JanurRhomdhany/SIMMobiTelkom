package com.example.opsmobitelkomandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class StatusTransaksiMobil extends AppCompatActivity {


    TabLayout tableLayout;
    ViewPager2 viewPager2;
    VPAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_transaksi_mobil);

        tableLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewPager);
        vpAdapter = new VPAdapter(this);
        viewPager2.setAdapter(vpAdapter);

        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tableLayout.getTabAt(position).select();
            }
        });


    }
}