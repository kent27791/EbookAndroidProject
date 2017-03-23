package xuxu.ebookproject.ui.ebook.home.news_book;

import java.util.ArrayList;

import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentView;

/**
 * Created by phanx on 27/11/2016.
 */
public interface NewsBookFragmentView extends BaseFragmentView {
    void setUpNewsBookRecyclerView(ArrayList<BookViewModel> books);
}
