package xuxu.ebookproject.ui.base.activity;

import java.util.ArrayList;

import xuxu.ebookproject.model.CategoryViewModel;
import xuxu.ebookproject.model.SearchViewModel;

/**
 * Created by phanx on 26/11/2016.
 */
public interface BaseActivityView {

    void setUpMenuCategory(ArrayList<CategoryViewModel> categoryViewModels);
    void setUpActionBarDrawer();

    void setUpProviderSearch(ArrayList<SearchViewModel> data);
}
