package xuxu.ebookproject.ui.adapter;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.TextView;

import xuxu.ebookproject.R;

public class SearchAdapter extends SimpleCursorAdapter {


    public SearchAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView searchTypeTextView = (TextView) view.findViewById(R.id.search_type_text_view);
        if(cursor.getInt(3) == 1){
            searchTypeTextView.setBackgroundResource(R.drawable.square_radius_5);
            searchTypeTextView.setText("Sách");
        }else{
            searchTypeTextView.setBackgroundResource(R.drawable.square_radius_1);
            searchTypeTextView.setText("Tác giả");
        }
        TextView searchDisplayTextView = (TextView) view.findViewById(R.id.search_display_text_view);
        searchDisplayTextView.setText(cursor.getString(2));
    }
}
