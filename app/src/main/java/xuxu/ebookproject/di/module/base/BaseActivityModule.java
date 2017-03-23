package xuxu.ebookproject.di.module.base;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.ui.base.activity.BaseActivityView;

/**
 * Created by phanx on 27/11/2016.
 */

@Module
public abstract class BaseActivityModule<V extends BaseActivityView> {
    protected V mView;
    public BaseActivityModule(V view){
        this.mView = view;
    }

    @Provides
    @ActivityScope
    protected V providerView(){
        return mView;
    }
}
