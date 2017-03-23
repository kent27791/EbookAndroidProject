package xuxu.ebookproject.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xuxu.ebookproject.gson.GmtDateTypeAdapter;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.service.AuthorService;
import xuxu.ebookproject.service.BookService;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.ReaderService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.application.EBookApplication;
import xuxu.ebookproject.ui.session.UserSessionManager;

@Module
public class NetworkModule {
    String mBaseUrl;
    public NetworkModule(String baseUrl){
        this.mBaseUrl = baseUrl;
    }


    @Singleton
    @Provides
    Gson provideGson(){
        return new GsonBuilder()
                //.setDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'")
                .registerTypeAdapter(Date.class, new GmtDateTypeAdapter())
                .create();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    /*@Singleton
    @Provides
    SimpleXmlConverterFactory providerSimpleXmlConverterFactory(){
        return SimpleXmlConverterFactory.create();
    }

    @Singleton
    @Provides
    QualifiedTypeConverterFactory providerQualifiedTypeConverterFactory(GsonConverterFactory gsonConverterFactory, SimpleXmlConverterFactory simpleXmlConverterFactory){
        return new QualifiedTypeConverterFactory(gsonConverterFactory, simpleXmlConverterFactory);
    }*/

    @Provides
    @Singleton
    Cache provideOkHttpCache(EBookApplication application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, final UserSessionManager userSessionManager) {
        OkHttpClient client = new OkHttpClient.Builder()
                //.cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        if(userSessionManager.isUserLoggedIn()){
                            Request.Builder requestBuilder = original.newBuilder()
                                    .header("Authorization", "Bearer " + userSessionManager.getUserLoginSession().AccessToken);
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                        return chain.proceed(original);

                    }
                })
                .build();
        return client;
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    AuthorService providerAuthorService(Retrofit retrofit){
        return retrofit.create(AuthorService.class);
    }

    @Singleton
    @Provides
    BookService providerBookService(Retrofit retrofit){
        return retrofit.create(BookService.class);
    }

    @Singleton
    @Provides
    CategoryService providerCategoryService(Retrofit retrofit){
        return retrofit.create(CategoryService.class);
    }

    @Singleton
    @Provides
    AccountService providerAccountService(Retrofit retrofit){
        return retrofit.create(AccountService.class);
    }

    @Singleton
    @Provides
    ReaderService providerReaderService(Retrofit retrofit){
        return retrofit.create(ReaderService.class);
    }

    @Singleton
    @Provides
    SearchService providerSearchService(Retrofit retrofit){
        return retrofit.create(SearchService.class);
    }
}