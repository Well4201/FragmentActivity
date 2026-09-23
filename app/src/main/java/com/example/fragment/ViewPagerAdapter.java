package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 1) {
            return new CoursesFragment();
        } else if(position == 2){
            return new NotificationsFragment();
        }else if(position == 3){
            return new SettingsFragment();
        }
            return new HomeFragment();
        }

    @Override
    public int getItemCount() {
        return 4;
    }
}
