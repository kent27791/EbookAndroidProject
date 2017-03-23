package xuxu.ebookproject.ui.ebook.book;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.ApplicationComponent;
import xuxu.ebookproject.di.component.BookActivityComponent;
import xuxu.ebookproject.di.component.DaggerBookActivityComponent;
import xuxu.ebookproject.di.module.book.BookActivityModule;
import xuxu.ebookproject.helper.StringHelper;
import xuxu.ebookproject.model.BookDetailsViewModel;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.adapter.BookAdapter;
import xuxu.ebookproject.ui.base.activity.BaseActivity;
import xuxu.ebookproject.ui.base.activity.HasComponent;
import xuxu.ebookproject.ui.ebook.reader.ReaderActivity;
import xuxu.ebookproject.ui.event.ItemRecyclerViewClickListener;

import static xuxu.ebookproject.config.Network.BASE_URL;

public class BookActivity extends BaseActivity<BookActivityPresenter> implements BookActivityView, HasComponent<BookActivityComponent> {
    protected BookActivityComponent mActivityComponent;

    @BindView(R.id.title_book_text_view)
    TextView mTitleBookTextView;

    @BindView(R.id.thumbnail_book_image_view)
    ImageView mThumbnailBookImageView;

    @BindView(R.id.category_book_text_view)
    TextView mCategoryBookTextView;

    @BindView(R.id.author_book_text_view)
    TextView mAuthorBookTextView;

    @BindView(R.id.publisher_book_text_view)
    TextView mPublisherBookTextView;

    @BindView(R.id.supplier_book_text_view)
    TextView mSupplierBookTextView;

    @BindView(R.id.sale_book_text_view)
    TextView mSaleBookTextView;

    @BindView(R.id.sale_off_book_text_view)
    TextView mSaleOffBookTextView;

    @BindView(R.id.summary_book_text_view)
    TextView mSummaryBookTextView;

    @BindView(R.id.related_books_recycler_view)
    RecyclerView mRelatedBooksRecyclerView;

    @BindView(R.id.buy_book_button)
    Button mBuyBookButton;

    @BindView(R.id.preview_book_button)
    Button mPreviewBookButton;

    @BindView(R.id.add_favorite_book_button)
    Button mAddFavoriteBookButton;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_book;
    }

    @Override
    protected void setUpComponent(ApplicationComponent applicationComponent) {
        mActivityComponent = DaggerBookActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .bookActivityModule(new BookActivityModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    @Override
    public BookActivityComponent getComponent() {
        return mActivityComponent;
    }

    @Override
    public int getBookId() {
        return getIntent().getIntExtra("BookId", 0);
    }

    @Override
    public void bindBookDetails(final BookDetailsViewModel book) {
        try {
            mTitleBookTextView.setText(book.Title);
            Glide.with(this).load(BASE_URL + book.UrlThumbnail + "?w=270&h=390&mode=crop")
                    .fitCenter()
                    .placeholder(R.drawable.default_book_thumbnail)
                    .into(mThumbnailBookImageView);
            mCategoryBookTextView.setText(book.CategoryViewModel.Display);
            mAuthorBookTextView.setText(StringHelper.join(book.AuthorsViewModel, "Display", ","));
            mPublisherBookTextView.setText(book.PublisherViewModel.Display);
            mSupplierBookTextView.setText(book.SupplierViewModel.Display);
            if(book.Sale == book.SaleOff){
                mSaleBookTextView.setVisibility(View.GONE);
                mSaleOffBookTextView.setText(mNumberFormat.format(book.SaleOff));
            }else{
                mSaleBookTextView.setText(mNumberFormat.format(book.Sale));
                mSaleBookTextView.setPaintFlags(mSaleBookTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                mSaleBookTextView.setVisibility(View.VISIBLE);
                mSaleOffBookTextView.setText(mNumberFormat.format(book.SaleOff));
            }
            mSummaryBookTextView.setText(book.SumaryText);
            mPreviewBookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ReaderActivity.class);
                    intent.putExtra("FileEpub", book.FileEpub);
                    v.getContext().startActivity(intent);
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUpRelatedBookRecyclerView(ArrayList<BookViewModel> books) {
        mRelatedBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); // 1 row);
        mRelatedBooksRecyclerView.setAdapter(new BookAdapter(this, books, 2, mNumberFormat)); // 2 item per screen
        mRelatedBooksRecyclerView.addOnItemTouchListener(new ItemRecyclerViewClickListener(this, new ItemRecyclerViewClickListener.OnItemRecyclerViewClickListener() {
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
    public void setUpEventButton() {

    }
}
