package com.messaging.chatrapid.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.messaging.chatrapid.Fragments.CallsFragment;
import com.messaging.chatrapid.Fragments.ChatFragment;
import com.messaging.chatrapid.Fragments.HomeFragment;
import com.messaging.chatrapid.Fragments.SearchFragment;
import com.messaging.chatrapid.Fragments.StatusFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new ChatFragment();
            case 1: return new HomeFragment();
            case 2: return  new StatusFragment();
            case 3: return new CallsFragment();
            default: return new ChatFragment();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if(position==0){
            title="Chats";
        }if(position==1){
            title="Home";
        }if(position==2){
            title="Status";
        }if(position==3){
            title="Calls";
        }
        return title;
    }
}
