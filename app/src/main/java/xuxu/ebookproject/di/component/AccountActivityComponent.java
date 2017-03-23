package xuxu.ebookproject.di.component;

import dagger.Component;
import xuxu.ebookproject.di.module.account.AccountActivityModule;
import xuxu.ebookproject.di.module.account.InformationFragmentModule;
import xuxu.ebookproject.di.module.account.LibraryFragmentModule;
import xuxu.ebookproject.di.module.account.SignInFragmentModule;
import xuxu.ebookproject.di.module.account.SignUpFragmentModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.ui.ebook.account.AccountActivity;
import xuxu.ebookproject.ui.ebook.account.information.InformationFragment;
import xuxu.ebookproject.ui.ebook.account.library.LibraryFragment;
import xuxu.ebookproject.ui.ebook.account.sign_in.SignInFragment;
import xuxu.ebookproject.ui.ebook.account.sign_up.SignUpFragment;


@ActivityScope
@FragmentScope
@Component(modules = { AccountActivityModule.class, SignInFragmentModule.class, SignUpFragmentModule.class, LibraryFragmentModule.class, InformationFragmentModule.class }, dependencies = { ApplicationComponent.class })
public interface AccountActivityComponent {
    void inject(AccountActivity activity);
    void inject(SignInFragment fragment);
    void inject(SignUpFragment fragment);
    void inject(LibraryFragment fragment);
    void inject(InformationFragment fragment);
}
