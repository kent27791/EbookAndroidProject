package xuxu.ebookproject.di.component;

import dagger.Component;
import xuxu.ebookproject.di.module.category.CategoryActivityModule;
import xuxu.ebookproject.di.scope.ActivityScope;
import xuxu.ebookproject.di.scope.FragmentScope;
import xuxu.ebookproject.ui.ebook.category.CategoryActivity;

@ActivityScope
@FragmentScope
@Component(modules = { CategoryActivityModule.class }, dependencies = { ApplicationComponent.class })
public interface CategoryActivityComponent {
    void inject(CategoryActivity activity);
}
