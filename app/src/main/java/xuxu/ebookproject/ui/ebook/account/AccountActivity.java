package xuxu.ebookproject.ui.ebook.account;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.AccountActivityComponent;
import xuxu.ebookproject.di.component.ApplicationComponent;
import xuxu.ebookproject.di.component.DaggerAccountActivityComponent;
import xuxu.ebookproject.di.module.account.AccountActivityModule;
import xuxu.ebookproject.di.module.account.InformationFragmentModule;
import xuxu.ebookproject.di.module.account.LibraryFragmentModule;
import xuxu.ebookproject.di.module.account.SignInFragmentModule;
import xuxu.ebookproject.di.module.account.SignUpFragmentModule;
import xuxu.ebookproject.model.FragmentViewModel;
import xuxu.ebookproject.ui.adapter.PagerFragmentAdapter;
import xuxu.ebookproject.ui.base.activity.BaseActivity;
import xuxu.ebookproject.ui.base.activity.HasComponent;
import xuxu.ebookproject.ui.ebook.account.information.InformationFragment;
import xuxu.ebookproject.ui.ebook.account.information.InformationFragmentView;
import xuxu.ebookproject.ui.ebook.account.library.LibraryFragment;
import xuxu.ebookproject.ui.ebook.account.library.LibraryFragmentView;
import xuxu.ebookproject.ui.ebook.account.sign_in.SignInFragment;
import xuxu.ebookproject.ui.ebook.account.sign_in.SignInFragmentView;
import xuxu.ebookproject.ui.ebook.account.sign_up.SignUpFragment;
import xuxu.ebookproject.ui.ebook.account.sign_up.SignUpFragmentView;

public class AccountActivity extends BaseActivity<AccountActivityPresenter> implements AccountActivityView, HasComponent<AccountActivityComponent> {
    protected AccountActivityComponent mActivityComponent;

    @BindView(R.id.account_view_pager)
    ViewPager mAccountViewPager;

    @BindView(R.id.account_tab_layout)
    TabLayout mAccountTabLayout;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_account;
    }

    @Override
    protected void setUpFragments() {
        mFragmentViewModels.add(0, new FragmentViewModel(0, "Đăng nhập", false, new SignInFragment()));
        mFragmentViewModels.add(1, new FragmentViewModel(1, "Đăng ký", false, new SignUpFragment()));
        mFragmentViewModels.add(2, new FragmentViewModel(2, "Thông tin tài khoản", true, new InformationFragment()));
        mFragmentViewModels.add(3, new FragmentViewModel(3, "Thư viện của tôi", true, new LibraryFragment()));
    }

    @Override
    protected void setUpComponent(ApplicationComponent applicationComponent) {
        mActivityComponent = DaggerAccountActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .accountActivityModule(new AccountActivityModule(this))
                .signInFragmentModule(new SignInFragmentModule((SignInFragmentView) mFragmentViewModels.get(0).Fragment))
                .signUpFragmentModule(new SignUpFragmentModule((SignUpFragmentView) mFragmentViewModels.get(1).Fragment))
                .informationFragmentModule(new InformationFragmentModule((InformationFragmentView) mFragmentViewModels.get(2).Fragment))
                .libraryFragmentModule(new LibraryFragmentModule((LibraryFragmentView) mFragmentViewModels.get(3).Fragment))
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public AccountActivityComponent getComponent() {
        return mActivityComponent;
    }

    @Override
    public void setUpTabLayout() {
        mAccountTabLayout.removeAllTabs();
        if(getUserSessionManager().isUserLoggedIn()){
            mFragmentViewModels.remove(0);
            mFragmentViewModels.remove(0);
        }else{
            mFragmentViewModels.remove(2);
            mFragmentViewModels.remove(2);
        }

        for (FragmentViewModel fragmentViewModel : mFragmentViewModels){
            mAccountTabLayout.addTab(mAccountTabLayout.newTab().setText(fragmentViewModel.Title));
        }

        mAccountTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mAccountViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setUpViewPager() {
        mAccountViewPager.setAdapter(new PagerFragmentAdapter(getSupportFragmentManager(), mFragmentViewModels));
        mAccountViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mAccountTabLayout));
    }

    public ViewPager getAccountViewPager(){
        return mAccountViewPager;
    }
}
