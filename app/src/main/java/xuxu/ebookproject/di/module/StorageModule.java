package xuxu.ebookproject.di.module;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.ui.session.UserSessionManager;

@Module
public class StorageModule {
    private Context mContext;
    public StorageModule(Context context){
        this.mContext = context;
    }

    @Singleton
    @Provides
    UserSessionManager providerUserSessionManager(Gson gson){
        return new UserSessionManager(mContext, gson);
    }
}
