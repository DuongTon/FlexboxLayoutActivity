package com.example.flexboxlayoutactivity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView textItem;

    public ViewHolder(View itemView) {
        super(itemView);
        if(itemView!=null) {
            this.textItem = itemView.findViewById(R.id.tv_question);
        }
    }

    public TextView getTextItem() {
        return textItem;
    }
}
