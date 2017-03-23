package xuxu.ebookproject.di.module.book;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseActivityModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.service.BookService;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.ebook.book.BookActivityPresenter;
import xuxu.ebookproject.ui.ebook.book.BookActivityView;

/**
 * Created by phanx on 27/11/2016.
 */

@Module
public class BookActivityModule extends BaseActivityModule<BookActivityView> {

    public BookActivityModule(BookActivityView view) {
        super(view);
    }

    @Provides
    @ActivityScope
    BookActivityPresenter providerPresenter(BookActivityView view, CategoryService categoryService, SearchService searchService, BookService bookService){
        return new BookActivityPresenter(view, categoryService, searchService, bookService);
    }

}
