package xuxu.ebookproject.di.module.account;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseFragmentModule;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.ui.ebook.account.sign_up.SignUpFragmentPresenter;
import xuxu.ebookproject.ui.ebook.account.sign_up.SignUpFragmentView;

@Module
public class SignUpFragmentModule extends BaseFragmentModule<SignUpFragmentView> {

    public SignUpFragmentModule(SignUpFragmentView view) {
        super(view);
    }

    @Provides
    @FragmentScope
    SignUpFragmentPresenter providerPresenter(SignUpFragmentView view, AccountService accountService){
        return new SignUpFragmentPresenter(view, accountService);
    }

}
