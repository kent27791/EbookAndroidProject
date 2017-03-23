package xuxu.ebookproject.ui.ebook.account;

import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.base.activity.BaseActivityPresenter;

public class AccountActivityPresenter extends BaseActivityPresenter<AccountActivityView> {
    public AccountActivityPresenter(AccountActivityView view, CategoryService categoryService, SearchService searchService) {
        super(view, categoryService, searchService);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.setUpTabLayout();
        mView.setUpViewPager();
    }
}
