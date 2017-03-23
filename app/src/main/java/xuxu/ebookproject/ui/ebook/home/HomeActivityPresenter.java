package xuxu.ebookproject.ui.ebook.home;

import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.base.activity.BaseActivityPresenter;


public class HomeActivityPresenter extends BaseActivityPresenter<HomeActivityView> {


    public HomeActivityPresenter(HomeActivityView view, CategoryService categoryService, SearchService searchService) {
        super(view, categoryService, searchService);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.setUpTabLayout();
        mView.setUpViewPager();
    }
}
