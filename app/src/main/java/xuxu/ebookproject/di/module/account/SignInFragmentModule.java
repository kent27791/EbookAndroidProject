package xuxu.ebookproject.di.module.account;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseFragmentModule;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.ui.ebook.account.sign_in.SignInFragmentPresenter;
import xuxu.ebookproject.ui.ebook.account.sign_in.SignInFragmentView;

/**
 * Created by phanx on 29/11/2016.
 */

@Module
public class SignInFragmentModule extends BaseFragmentModule<SignInFragmentView> {

    public SignInFragmentModule(SignInFragmentView view) {
        super(view);
    }

    @Provides
    @FragmentScope
    SignInFragmentPresenter providerPresenter(SignInFragmentView view, AccountService accountService, Gson gson){
        return new SignInFragmentPresenter(view, accountService, gson);
    }
}
