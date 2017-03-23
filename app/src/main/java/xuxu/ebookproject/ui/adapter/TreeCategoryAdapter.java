package xuxu.ebookproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xuxu.ebookproject.R;
import xuxu.ebookproject.model.CategoryViewModel;
import xuxu.ebookproject.ui.ebook.category.CategoryActivity;
import xuxu.ebookproject.ui.event.ArrowCategoryClickListener;

public class TreeCategoryAdapter extends RecyclerView.Adapter<TreeCategoryAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CategoryViewModel> mCategoryViewModels;
    public TreeCategoryAdapter(Context context, ArrayList<CategoryViewModel> categoryViewModels){
        this.mContext = context;
        this.mCategoryViewModels = categoryViewModels;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryViewModel categoryViewModel = mCategoryViewModels.get(position);
        holder.mNameCategoryTextView.setText(categoryViewModel.Name);
        if(categoryViewModel.Id > 0){
            holder.itemView.setTag(categoryViewModel.Id);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CategoryActivity.class);
                    intent.putExtra("CategoryId", (int) v.getTag());
                    mContext.startActivity(intent);
                }
            });
        }
        if(categoryViewModel.Childrens.size() > 0){
            //add arrow image
            ImageView arrowImageView = new ImageView(mContext);
            arrowImageView.setId(R.id.arrow_image_view);
            arrowImageView.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParams.rightMargin = mContext.getResources().getDimensionPixelOffset(R.dimen.margin_arrow_image);
            holder.mContentItemRelativeLayout.addView(arrowImageView, layoutParams);
            arrowImageView.setOnClickListener(new ArrowCategoryClickListener(holder));

            //add child recycler view
            RecyclerView childRecyclerView = new RecyclerView(mContext);
            childRecyclerView.setId(R.id.child_category_recycler_view);
            childRecyclerView.setPadding(mContext.getResources().getDimensionPixelOffset(R.dimen.padding_recycler_view), 0, 0, 0);
            childRecyclerView.setVisibility(View.GONE);
            childRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            childRecyclerView.setAdapter(new TreeCategoryAdapter(mContext, categoryViewModel.Childrens));
            ((ViewGroup)holder.itemView).addView(childRecyclerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public int getItemCount() {
        return mCategoryViewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_category_text_view)
        TextView mNameCategoryTextView;

        @BindView(R.id.content_item_relative_layout)
        RelativeLayout mContentItemRelativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


