package xuxu.ebookproject.ui.ebook.book;

import java.util.ArrayList;

import xuxu.ebookproject.model.BookDetailsViewModel;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.base.activity.BaseActivityView;

/**
 * Created by phanx on 27/11/2016.
 */
public interface BookActivityView extends BaseActivityView {
    int getBookId();

    void bindBookDetails(BookDetailsViewModel book);

    void setUpRelatedBookRecyclerView(ArrayList<BookViewModel> books);

    void setUpEventButton();
}
