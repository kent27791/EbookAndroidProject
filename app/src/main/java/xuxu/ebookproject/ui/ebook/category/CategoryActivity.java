package xuxu.ebookproject.ui.ebook.category;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.ApplicationComponent;
import xuxu.ebookproject.di.component.CategoryActivityComponent;
import xuxu.ebookproject.di.component.DaggerCategoryActivityComponent;
import xuxu.ebookproject.di.module.category.CategoryActivityModule;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.adapter.BookAdapter;
import xuxu.ebookproject.ui.base.activity.BaseActivity;
import xuxu.ebookproject.ui.base.activity.HasComponent;
import xuxu.ebookproject.ui.event.ItemRecyclerViewClickListener;

public class CategoryActivity extends BaseActivity<CategoryActivityPresenter> implements CategoryActivityView, HasComponent<CategoryActivityComponent>{
    protected CategoryActivityComponent mActivityComponent;

    @BindView(R.id.filter_button)
    Button mFilterButton;

    @BindView(R.id.sort_button)
    Button mSortButton;

    @BindView(R.id.display_image_button)
    ImageButton mDisplayImageButton;

    @BindView(R.id.books_by_category_recycler_view)
    RecyclerView mBooksByCategoryRecyclerView;

    @BindView(R.id.activity_category)
    LinearLayout mCategoryLinearLayout;

    AlertDialog mBookSortAlertDialog;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected void setUpFragments() {

    }

    @Override
    protected void setUpComponent(ApplicationComponent applicationComponent) {
        mActivityComponent = DaggerCategoryActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .categoryActivityModule(new CategoryActivityModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public CategoryActivityComponent getComponent() {
        return mActivityComponent;
    }

    @Override
    public int getCategoryId() {
        return getIntent().getIntExtra("CategoryId", 0);
    }

    @Override
    public void setUpBooksByCategoryRecyclerView() {
        mBooksByCategoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        mBooksByCategoryRecyclerView.addOnItemTouchListener(new ItemRecyclerViewClickListener(this, new ItemRecyclerViewClickListener.OnItemRecyclerViewClickListener() {
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
    public void setUpAdapterBooksByCategoryRecyclerView(ArrayList<BookViewModel> books) {
        mBooksByCategoryRecyclerView.setAdapter(new BookAdapter(this, books, 2, mNumberFormat));
    }

    @Override
    public void setUpBookSortAlertDialog() {
        mBookSortAlertDialog = new AlertDialog.Builder(this)
                .setTitle("Sắp xếp theo :")
                .setItems(R.array.book_sort, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                mPresenter.getBooksByCategory(getCategoryId(), 1, 18, "Title", "asc");
                                break;
                            case 1:
                                mPresenter.getBooksByCategory(getCategoryId(), 1, 18, "Title", "desc");
                                break;
                            case 2:
                                mPresenter.getBooksByCategory(getCategoryId(), 1, 18, "SaleOff", "asc");
                                break;
                            case 3:
                                mPresenter.getBooksByCategory(getCategoryId(), 1, 18, "SaleOff", "desc");
                                break;
                            default:
                                break;
                        }
                    }
                }).create();

        mSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookSortAlertDialog.show();
            }
        });
    }
}
