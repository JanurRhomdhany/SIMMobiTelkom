package com.example.opsmobitelkomandroid;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.opsmobitelkomandroid.statusfragments.StatusPeminjamanFragment;
import com.example.opsmobitelkomandroid.statusfragments.StatusPengembalianFragment;

public class VPAdapter extends FragmentStateAdapter {


    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new StatusPeminjamanFragment();
            case 1:
                return new StatusPengembalianFragment();
            default:
                return new StatusPeminjamanFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
