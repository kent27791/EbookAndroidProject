package xuxu.ebookproject.model;

import android.support.v4.app.Fragment;

/**
 * Created by phanx on 27/11/2016.
 */

public class FragmentViewModel{
    public FragmentViewModel(int index, String title, boolean requiredAuth, Fragment fragment){
        this.Index = index;
        this.Title = title;
        this.Fragment = fragment;
        this.RequiredAuth = requiredAuth;
    }
    public int Index;
    public String Title;
    public Fragment Fragment;
    public boolean RequiredAuth;
}
