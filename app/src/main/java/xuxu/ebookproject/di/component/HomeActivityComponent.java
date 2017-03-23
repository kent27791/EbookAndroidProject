package xuxu.ebookproject.di.component;

import dagger.Component;
import xuxu.ebookproject.di.module.home.HighlightBookFragmentModule;
import xuxu.ebookproject.di.module.home.HomeActivityModule;
import xuxu.ebookproject.di.module.home.NewsBookFragmentModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.ui.ebook.home.HomeActivity;
import xuxu.ebookproject.ui.ebook.home.highlight_book.HighlightBookFragment;
import xuxu.ebookproject.ui.ebook.home.news_book.NewsBookFragment;

/**
 * Created by phanx on 26/11/2016.
 */

@ActivityScope
@FragmentScope
@Component(modules = { HomeActivityModule.class, HighlightBookFragmentModule.class, NewsBookFragmentModule.class }, dependencies = { ApplicationComponent.class })
public interface HomeActivityComponent {
    void inject(HomeActivity activity);
    void inject(HighlightBookFragment fragment);
    void inject(NewsBookFragment fragment);
}
