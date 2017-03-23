package xuxu.ebookproject.di.module.home;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseFragmentModule;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.service.AuthorService;
import xuxu.ebookproject.ui.ebook.home.highlight_book.HighlightBookFragmentPresenter;
import xuxu.ebookproject.ui.ebook.home.highlight_book.HighlightBookFragmentView;

/**
 * Created by phanx on 26/11/2016.
 */

@Module
public class HighlightBookFragmentModule extends BaseFragmentModule<HighlightBookFragmentView> {

    public HighlightBookFragmentModule(HighlightBookFragmentView view) {
        super(view);
    }

    @Provides
    @FragmentScope
    protected HighlightBookFragmentPresenter providerPresenter(HighlightBookFragmentView view, AuthorService authorService){
        return new HighlightBookFragmentPresenter(view, authorService);
    }
}
