package xuxu.ebookproject.ui.ebook.home.highlight_book;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.HomeActivityComponent;
import xuxu.ebookproject.model.AuthorViewModel;
import xuxu.ebookproject.ui.adapter.AuthorAdapter;
import xuxu.ebookproject.ui.base.fragment.BaseFragment;

public class HighlightBookFragment extends BaseFragment<HighlightBookFragmentPresenter> implements HighlightBookFragmentView {
    @BindView(R.id.highlight_authors_recycler_view)
    RecyclerView mHighlightAuthorsRecyclerView;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_highlight_book;
    }

    @Override
    protected void setUpComponent() {
        this.getComponent(HomeActivityComponent.class).inject(this);
    }

    @Override
    public void setUpHighlightAuthorRecyclerView(List<AuthorViewModel> authors) {
        mHighlightAuthorsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mHighlightAuthorsRecyclerView.setAdapter(new AuthorAdapter(getContext(), authors));
    }

    @Override
    public void getHighlightAuthorFailure() {

    }
}

