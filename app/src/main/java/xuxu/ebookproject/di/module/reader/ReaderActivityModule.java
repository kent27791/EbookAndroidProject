package xuxu.ebookproject.di.module.reader;

import dagger.Module;
import dagger.Provides;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.service.ReaderService;
import xuxu.ebookproject.ui.ebook.reader.ReaderActivityPresenter;
import xuxu.ebookproject.ui.ebook.reader.ReaderActivityView;

@Module
public class ReaderActivityModule {
    protected ReaderActivityView mView;
    public ReaderActivityModule(ReaderActivityView view){
        this.mView = view;
    }

    @Provides
    @ActivityScope
    ReaderActivityView providerView(){
        return mView;
    }

    @Provides
    @ActivityScope
    ReaderActivityPresenter providerPresenter(ReaderActivityView view, ReaderService readerService){
        return new ReaderActivityPresenter(view, readerService);
    }
}

