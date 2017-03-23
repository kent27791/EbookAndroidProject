package xuxu.ebookproject.ui.ebook.account.library;

import java.util.ArrayList;

import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentView;

/**
 * Created by phanx on 19/12/2016.
 */
public interface LibraryFragmentView extends BaseFragmentView {

    void setUpLibraryBookRecyclerView();

    void setUpAdapterLibraryBookRecyclerView(ArrayList<BookViewModel> books);
}
