package xuxu.ebookproject.di.component;

import dagger.Component;
import xuxu.ebookproject.di.module.book.BookActivityModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.ui.ebook.book.BookActivity;


@ActivityScope
@FragmentScope
@Component(modules = { BookActivityModule.class }, dependencies = { ApplicationComponent.class })
public interface BookActivityComponent {
    void inject(BookActivity activity);
}

