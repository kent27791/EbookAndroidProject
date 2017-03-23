package xuxu.ebookproject.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import xuxu.ebookproject.model.FragmentViewModel;

public class PagerFragmentAdapter extends FragmentStatePagerAdapter {
    ArrayList<FragmentViewModel> mFragmentViewModels;
    public PagerFragmentAdapter(FragmentManager fragmentManager, ArrayList<FragmentViewModel> fragmentViewModels) {
        super(fragmentManager);
        this.mFragmentViewModels = fragmentViewModels;
    }

    @Override
    public Fragment getItem(int position) {
        return  mFragmentViewModels.get(position).Fragment;
    }


    @Override
    public int getCount() {
        return mFragmentViewModels.size();
    }
}
