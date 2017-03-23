package xuxu.ebookproject.ui.event;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import xuxu.ebookproject.R;
import xuxu.ebookproject.ui.adapter.TreeCategoryAdapter;

public class ArrowCategoryClickListener implements View.OnClickListener {
    private TreeCategoryAdapter.ViewHolder mViewHolder;
    private boolean isExpanded = false;
    public ArrowCategoryClickListener(TreeCategoryAdapter.ViewHolder viewHolder){
        this.mViewHolder = viewHolder;
    }
    @Override
    public void onClick(View v) {
        RecyclerView childRecyclerView = (RecyclerView) mViewHolder.itemView.findViewById(R.id.child_category_recycler_view);
        ImageView arrowImageView = (ImageView) mViewHolder.itemView.findViewById(R.id.arrow_image_view);
        if(childRecyclerView == null) return;
        if(isExpanded == false){
            childRecyclerView.setVisibility(View.VISIBLE);
            arrowImageView.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            isExpanded = true;
        }else{
            childRecyclerView.setVisibility(View.GONE);
            arrowImageView.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            isExpanded = false;
        }
    }
}

