package xuxu.ebookproject.di.module.account;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseActivityModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.ebook.account.AccountActivityPresenter;
import xuxu.ebookproject.ui.ebook.account.AccountActivityView;


@Module
public class AccountActivityModule extends BaseActivityModule<AccountActivityView> {
    public AccountActivityModule(AccountActivityView view) {
        super(view);
    }

    @Provides
    @ActivityScope
    AccountActivityPresenter providerPresenter(AccountActivityView view, CategoryService categoryService, SearchService searchService){
        return new AccountActivityPresenter(view, categoryService, searchService);
    }
}
