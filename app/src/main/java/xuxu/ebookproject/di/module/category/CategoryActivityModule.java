package xuxu.ebookproject.di.module.category;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseActivityModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.service.BookService;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.ebook.category.CategoryActivityPresenter;
import xuxu.ebookproject.ui.ebook.category.CategoryActivityView;

@Module
public class CategoryActivityModule extends BaseActivityModule<CategoryActivityView> {

    public CategoryActivityModule(CategoryActivityView view) {
        super(view);
    }

    @Provides
    @ActivityScope
    CategoryActivityPresenter providerPresenter(CategoryActivityView view, CategoryService categoryService, SearchService searchService, BookService bookService){
        return new CategoryActivityPresenter(view, categoryService, searchService, bookService);
    }

}
