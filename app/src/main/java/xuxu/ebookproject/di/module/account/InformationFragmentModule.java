package xuxu.ebookproject.di.module.account;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.module.base.BaseFragmentModule;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.ui.ebook.account.information.InformationFragmentPresenter;
import xuxu.ebookproject.ui.ebook.account.information.InformationFragmentView;

@Module
public class InformationFragmentModule extends BaseFragmentModule<InformationFragmentView> {
    public InformationFragmentModule(InformationFragmentView view) {
        super(view);
    }

    @Provides
    @FragmentScope
    InformationFragmentPresenter providerPresenter(InformationFragmentView view, AccountService accountService){
        return new InformationFragmentPresenter(view, accountService);
    }
}
