package xuxu.ebookproject.di.component;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xuxu.ebookproject.di.module.ApplicationModule;
import xuxu.ebookproject.di.module.NetworkModule;
import xuxu.ebookproject.di.module.StorageModule;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.service.AuthorService;
import xuxu.ebookproject.service.BookService;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.ReaderService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.application.EBookApplication;
import xuxu.ebookproject.ui.session.UserSessionManager;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class, StorageModule.class })
public interface ApplicationComponent {
    EBookApplication getApplication();

    Retrofit getRetrofit();
    OkHttpClient getOkHttpClient();
    Gson getGson();
    GsonConverterFactory getGsonConverterFactory();

    //SimpleXmlConverterFactory getSimpleXmlConverterFactory();
    //QualifiedTypeConverterFactory getQualifiedTypeConverterFactory();

    UserSessionManager getUserSessionManager();

    AuthorService getAuthorService();
    BookService getBookService();
    CategoryService getCategoryService();
    AccountService getAccountService();
    ReaderService getReaderService();
    SearchService getSearchService();
}
