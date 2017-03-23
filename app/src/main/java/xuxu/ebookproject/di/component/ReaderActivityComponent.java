package xuxu.ebookproject.di.component;


import dagger.Component;
import xuxu.ebookproject.di.module.reader.ReaderActivityModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.ui.ebook.reader.ReaderActivity;

@ActivityScope
@Component(modules = {ReaderActivityModule.class }, dependencies = { ApplicationComponent.class })
public interface ReaderActivityComponent {
    void inject(ReaderActivity activity);
}
