package xuxu.ebookproject.ui.ebook.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.ApplicationComponent;
import xuxu.ebookproject.di.component.DaggerHomeActivityComponent;
import xuxu.ebookproject.di.component.HomeActivityComponent;
import xuxu.ebookproject.di.module.home.HighlightBookFragmentModule;
import xuxu.ebookproject.di.module.home.HomeActivityModule;
import xuxu.ebookproject.di.module.home.NewsBookFragmentModule;
import xuxu.ebookproject.model.FragmentViewModel;
import xuxu.ebookproject.ui.adapter.PagerFragmentAdapter;
import xuxu.ebookproject.ui.base.activity.BaseActivity;
import xuxu.ebookproject.ui.base.activity.HasComponent;
import xuxu.ebookproject.ui.ebook.home.highlight_book.HighlightBookFragment;
import xuxu.ebookproject.ui.ebook.home.highlight_book.HighlightBookFragmentView;
import xuxu.ebookproject.ui.ebook.home.news_book.NewsBookFragment;
import xuxu.ebookproject.ui.ebook.home.news_book.NewsBookFragmentView;

public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityView, HasComponent<HomeActivityComponent> {
    @BindView(R.id.home_view_pager)
    ViewPager mHomeViewPager;

    @BindView(R.id.home_tab_layout)
    TabLayout mHomeTabLayout;

    protected HomeActivityComponent mActivityComponent;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void setUpFragments() {
        mFragmentViewModels.add(0, new FragmentViewModel(0, "Sách nổi bật", false, new HighlightBookFragment()));
        mFragmentViewModels.add(1, new FragmentViewModel(1, "Sách mới", false, new NewsBookFragment()));
    }

    @Override
    protected void setUpComponent(ApplicationComponent applicationComponent) {
        mActivityComponent = DaggerHomeActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .homeActivityModule(new HomeActivityModule(this))
                .highlightBookFragmentModule(new HighlightBookFragmentModule((HighlightBookFragmentView) mFragmentViewModels.get(0).Fragment))
                .newsBookFragmentModule(new NewsBookFragmentModule((NewsBookFragmentView) mFragmentViewModels.get(1).Fragment))
                .build();
        mActivityComponent.inject(this);
    }



    @Override
    public HomeActivityComponent getComponent() {
        return mActivityComponent;
    }



    @Override
    public void setUpTabLayout() {
        mHomeTabLayout.removeAllTabs();
        for (FragmentViewModel fragmentViewModel : mFragmentViewModels) {
            mHomeTabLayout.addTab(mHomeTabLayout.newTab().setText(fragmentViewModel.Title));
        }
        mHomeTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mHomeViewPager.setCurrentItem(tab.getPosition());
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
        mHomeViewPager.setAdapter(new PagerFragmentAdapter(getSupportFragmentManager(), mFragmentViewModels));
        mHomeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mHomeTabLayout));
    }
}
