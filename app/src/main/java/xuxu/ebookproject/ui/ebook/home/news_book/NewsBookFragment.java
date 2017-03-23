package xuxu.ebookproject.ui.ebook.home.news_book;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.HomeActivityComponent;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.adapter.BookAdapter;
import xuxu.ebookproject.ui.base.fragment.BaseFragment;
import xuxu.ebookproject.ui.event.ItemRecyclerViewClickListener;

public class NewsBookFragment extends BaseFragment<NewsBookFragmentPresenter> implements NewsBookFragmentView {
    @BindView(R.id.news_book_recycler_view)
    RecyclerView mNewsBookRecyclerView;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_news_book;
    }

    @Override
    protected void setUpComponent() {
        this.getComponent(HomeActivityComponent.class).inject(this);
    }

    @Override
    public void setUpNewsBookRecyclerView(ArrayList<BookViewModel> books) {
        mNewsBookRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mNewsBookRecyclerView.setAdapter(new BookAdapter(getContext(), books, 2, getNumberFormat()));
        mNewsBookRecyclerView.addOnItemTouchListener(new ItemRecyclerViewClickListener(getContext(), new ItemRecyclerViewClickListener.OnItemRecyclerViewClickListener() {
            @Override
            public void onItemRecyclerViewClick(View view, int position, View beforeSelectedView) {
                if(beforeSelectedView != null){
                    beforeSelectedView.findViewById(R.id.hover_relative_layout).setVisibility(View.GONE);
                }
                view.findViewById(R.id.hover_relative_layout).setVisibility(View.VISIBLE);
            }
        }));
    }
}
