package xuxu.ebookproject.ui.ebook.home.highlight_book;

import java.util.List;

import xuxu.ebookproject.model.AuthorViewModel;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentView;

/**
 * Created by phanx on 26/11/2016.
 */
public interface HighlightBookFragmentView extends BaseFragmentView {

    void setUpHighlightAuthorRecyclerView(List<AuthorViewModel> authors);

    void getHighlightAuthorFailure();
}
