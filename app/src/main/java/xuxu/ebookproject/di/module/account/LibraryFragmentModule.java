package xuxu.ebookproject.di.module.account;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseFragmentModule;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.ui.ebook.account.library.LibraryFragmentPresenter;
import xuxu.ebookproject.ui.ebook.account.library.LibraryFragmentView;

@Module
public class LibraryFragmentModule extends BaseFragmentModule<LibraryFragmentView> {

    public LibraryFragmentModule(LibraryFragmentView view) {
        super(view);
    }

    @Provides
    @FragmentScope
    LibraryFragmentPresenter providerPresenter(LibraryFragmentView view, AccountService accountService){
        return new LibraryFragmentPresenter(view, accountService);
    }
}
