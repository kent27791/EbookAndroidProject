package xuxu.ebookproject.di.module.home;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseActivityModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.ebook.home.HomeActivityPresenter;
import xuxu.ebookproject.ui.ebook.home.HomeActivityView;

/**
 * Created by phanx on 26/11/2016.
 */

@Module
public class HomeActivityModule extends BaseActivityModule<HomeActivityView> {

    public HomeActivityModule(HomeActivityView view) {
        super(view);
    }

    @Provides
    @ActivityScope
    HomeActivityPresenter providerPresenter(HomeActivityView view, CategoryService categoryService, SearchService searchService) {
        return new HomeActivityPresenter(view, categoryService, searchService);

    }

}
