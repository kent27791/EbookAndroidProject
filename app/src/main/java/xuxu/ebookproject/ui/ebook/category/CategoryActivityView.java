package xuxu.ebookproject.ui.ebook.category;

import java.util.ArrayList;

import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.base.activity.BaseActivityView;

/**
 * Created by phanx on 27/11/2016.
 */
public interface CategoryActivityView extends BaseActivityView {
    int getCategoryId();

    void setUpBooksByCategoryRecyclerView();

    void setUpAdapterBooksByCategoryRecyclerView(ArrayList<BookViewModel> books);

    void setUpBookSortAlertDialog();
}
