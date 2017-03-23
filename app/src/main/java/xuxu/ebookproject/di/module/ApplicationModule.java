package xuxu.ebookproject.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.ui.application.EBookApplication;


@Module
public class ApplicationModule {
    EBookApplication mApplication;
    public ApplicationModule(EBookApplication application){
        this.mApplication = application;
    }

    @Provides
    @Singleton
    EBookApplication provideApplication(){
        return this.mApplication;
    }

}
