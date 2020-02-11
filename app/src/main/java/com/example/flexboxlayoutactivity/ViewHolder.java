package com.example.flexboxlayoutactivity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView textItem;

    public ViewHolder(View itemView) {
        super(itemView);
        if(itemView!=null) {
            this.textItem = itemView.findViewById(R.id.flex_box_recycler_view_text_item);
        }
    }

    public TextView getTextItem() {
        return textItem;
    }
}
