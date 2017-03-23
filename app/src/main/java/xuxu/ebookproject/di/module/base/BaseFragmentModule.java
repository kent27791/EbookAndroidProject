package xuxu.ebookproject.di.module.base;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentView;

/**
 * Created by phanx on 27/11/2016.
 */
@Module
public abstract class BaseFragmentModule<V extends BaseFragmentView> {
    protected V mView;
    public BaseFragmentModule(V view){
        this.mView = view;
    }

    @Provides
    @FragmentScope
    protected V providerView(){
        return mView;
    }
}
