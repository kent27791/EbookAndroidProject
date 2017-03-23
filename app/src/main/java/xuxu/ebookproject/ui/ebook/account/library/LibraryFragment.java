package xuxu.ebookproject.ui.ebook.account.library;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.AccountActivityComponent;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.adapter.BookAdapter;
import xuxu.ebookproject.ui.base.fragment.BaseFragment;
import xuxu.ebookproject.ui.event.ItemRecyclerViewClickListener;

public class LibraryFragment extends BaseFragment<LibraryFragmentPresenter> implements LibraryFragmentView {
    @BindView(R.id.library_book_recyclver_view)
    RecyclerView mLibraryBookRecyclerView;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_library;
    }

    @Override
    protected void setUpComponent() {
        this.getComponent(AccountActivityComponent.class).inject(this);
    }


    @Override
    public void setUpLibraryBookRecyclerView() {
        mLibraryBookRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        mLibraryBookRecyclerView.addOnItemTouchListener(new ItemRecyclerViewClickListener(getContext(), new ItemRecyclerViewClickListener.OnItemRecyclerViewClickListener() {
            @Override
            public void onItemRecyclerViewClick(View view, int position, View beforeSelectedView) {
                if(beforeSelectedView != null){
                    beforeSelectedView.findViewById(R.id.hover_relative_layout).setVisibility(View.GONE);
                }
                view.findViewById(R.id.hover_relative_layout).setVisibility(View.VISIBLE);
            }
        }));
    }

    @Override
    public void setUpAdapterLibraryBookRecyclerView(ArrayList<BookViewModel> books) {
        mLibraryBookRecyclerView.setAdapter(new BookAdapter(getContext(), books, 2, getNumberFormat()));
    }
}
