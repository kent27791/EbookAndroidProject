package xuxu.ebookproject.di.module.home;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseFragmentModule;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.service.BookService;
import xuxu.ebookproject.ui.ebook.home.news_book.NewsBookFragmentPresenter;
import xuxu.ebookproject.ui.ebook.home.news_book.NewsBookFragmentView;

@Module
public class NewsBookFragmentModule extends BaseFragmentModule<NewsBookFragmentView> {
    public NewsBookFragmentModule(NewsBookFragmentView view) {
        super(view);
    }

    @Provides
    @FragmentScope
    NewsBookFragmentPresenter providerPresenter(NewsBookFragmentView view, BookService bookService){
        return new NewsBookFragmentPresenter(view, bookService);
    }
}
