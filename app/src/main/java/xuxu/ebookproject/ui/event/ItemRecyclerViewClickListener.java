package xuxu.ebookproject.ui.event;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by phanx on 20/10/2016.
 */

public class ItemRecyclerViewClickListener implements RecyclerView.OnItemTouchListener{
    private OnItemRecyclerViewClickListener mOnItemRecyclerViewClickListener;
    private View mCurrentItemView = null;
    public interface OnItemRecyclerViewClickListener {
        void onItemRecyclerViewClick(View view, int position, View beforeSelectedView);
    }

    GestureDetector mGestureDetector;

    public ItemRecyclerViewClickListener(@NonNull Context context,@NonNull OnItemRecyclerViewClickListener onItemRecyclerViewClickListener) {
        mOnItemRecyclerViewClickListener = onItemRecyclerViewClickListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View currentItemView = view.findChildViewUnder(e.getX(), e.getY());
        if (currentItemView != null && mGestureDetector.onTouchEvent(e)) {
            mOnItemRecyclerViewClickListener.onItemRecyclerViewClick(currentItemView, view.getChildAdapterPosition(currentItemView), mCurrentItemView);
            mCurrentItemView = currentItemView;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
