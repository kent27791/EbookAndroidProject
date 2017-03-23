package xuxu.ebookproject.ui.application;

import android.app.Application;
import android.content.res.Configuration;

import xuxu.ebookproject.di.component.ApplicationComponent;
import xuxu.ebookproject.di.component.DaggerApplicationComponent;
import xuxu.ebookproject.di.module.ApplicationModule;
import xuxu.ebookproject.di.module.NetworkModule;
import xuxu.ebookproject.di.module.StorageModule;

import static xuxu.ebookproject.config.Network.BASE_URL;

public class EBookApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void buildGraphAndInject() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(BASE_URL))
                .storageModule(new StorageModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent(){
        return mApplicationComponent;
    }
}
