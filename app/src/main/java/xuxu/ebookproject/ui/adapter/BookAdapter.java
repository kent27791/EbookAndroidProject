package xuxu.ebookproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xuxu.ebookproject.R;
import xuxu.ebookproject.helper.StringHelper;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.ui.ebook.book.BookActivity;

import static xuxu.ebookproject.config.Network.BASE_URL;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private Context mContext;
    private List<BookViewModel> mBookViewModels;
    private int mSpanCount;
    private NumberFormat mNumberFormat;

    public BookAdapter(Context context, List<BookViewModel> bookViewModels, int spanCount, NumberFormat numberFormat){
        this.mContext = context;
        this.mBookViewModels = bookViewModels;
        this.mSpanCount = spanCount;
        this.mNumberFormat = numberFormat;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_book, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = parent.getWidth() / mSpanCount;
        view.setLayoutParams(layoutParams);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        try {
            BookViewModel bookViewModel = mBookViewModels.get(position);
            holder.mTitleBookTextView.setText(bookViewModel.Title);
            holder.mAuthorBookTextView.setText(StringHelper.join(bookViewModel.AuthorsViewModel, "Display", ","));
            if(bookViewModel.Sale == bookViewModel.SaleOff){
                holder.mSaleBookTextView.setVisibility(View.GONE);
                holder.mSaleOffBookTextView.setText(mNumberFormat.format(bookViewModel.SaleOff));
            }else{
                holder.mSaleBookTextView.setText(mNumberFormat.format(bookViewModel.Sale));
                holder.mSaleBookTextView.setPaintFlags(holder.mSaleBookTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.mSaleBookTextView.setVisibility(View.VISIBLE);
                holder.mSaleOffBookTextView.setText(mNumberFormat.format(bookViewModel.SaleOff));
            }
            Glide.with(mContext).load(BASE_URL + bookViewModel.UrlThumbnail + "?w=175&h=255&mode=crop")
                    .fitCenter()
                    .placeholder(R.drawable.default_book_thumbnail)
                    .into(holder.mThumbnailBookImageView);
            holder.mReadBookImageButton.setTag(bookViewModel.Id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return mBookViewModels.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail_book_image_view)
        ImageView mThumbnailBookImageView;

        @BindView(R.id.title_book_text_view)
        TextView mTitleBookTextView;

        @BindView(R.id.author_book_text_view)
        TextView mAuthorBookTextView;

        @BindView(R.id.sale_book_text_view)
        TextView mSaleBookTextView;

        @BindView(R.id.sale_off_book_text_view)
        TextView mSaleOffBookTextView;


        @BindView(R.id.hover_relative_layout)
        RelativeLayout mHoverRelativeLayout;

        @BindView(R.id.read_book_image_button)
        ImageButton mReadBookImageButton;

        @BindView(R.id.buy_book_image_button)
        ImageButton mBuyBookImageButton;

        @BindView(R.id.add_favorite_book_image_button)
        ImageButton mAddFavoriteBookImageButton;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mReadBookImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BookActivity.class);
                    intent.putExtra("BookId", (int) v.getTag());
                    mContext.startActivity(intent);
                }
            });


            /*
            mMenuPopupImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(mContext, v);
                    popupMenu.getMenuInflater().inflate(R.menu.book_popup_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });
            */

            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                            public void onClick(View v) {
                                RecyclerView parent = (RecyclerView) v.getParent();
                                if(mCurrentItemAdapterPosition > -1){
                                    ((BookViewHolder)parent.findViewHolderForAdapterPosition(mCurrentItemAdapterPosition))
                                            .mHoverRelativeLayout.setVisibility(View.GONE);
                                }
                                BookViewHolder currentBookViewHolder = (BookViewHolder) parent.getChildViewHolder(v);
                                mCurrentItemAdapterPosition = currentBookViewHolder.getAdapterPosition();
                                currentBookViewHolder.mHoverRelativeLayout.setVisibility(View.VISIBLE);
                            }
            });
            */


        }
    }

}
