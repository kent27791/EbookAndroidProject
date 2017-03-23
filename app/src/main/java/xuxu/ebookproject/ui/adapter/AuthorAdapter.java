package xuxu.ebookproject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xuxu.ebookproject.R;
import xuxu.ebookproject.model.AuthorViewModel;

import static xuxu.ebookproject.config.Network.BASE_URL;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {
    private Context mContext;
    private List<AuthorViewModel> mAuthorViewModels;
    public AuthorAdapter(Context context, List<AuthorViewModel> authorViewModels){
        this.mContext = context;
        this.mAuthorViewModels = authorViewModels;
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_author, parent, false);
        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        AuthorViewModel authorViewModel = mAuthorViewModels.get(position);
        holder.mNameAuthorTextView.setText(authorViewModel.Name);
        Glide.with(mContext).load(BASE_URL + authorViewModel.UrlThumbnail + "?w=175&h=255&mode=crop")
                .centerCrop()
                .placeholder(R.drawable.default_book_thumbnail)
                .into(holder.mThumbnailAuthorImageView);
    }

    @Override
    public int getItemCount() {
        return mAuthorViewModels.size();
    }
    public class AuthorViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.thumbnail_author_image_view)
        ImageView mThumbnailAuthorImageView;

        @BindView(R.id.name_author_text_view)
        TextView mNameAuthorTextView;

        public AuthorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}


