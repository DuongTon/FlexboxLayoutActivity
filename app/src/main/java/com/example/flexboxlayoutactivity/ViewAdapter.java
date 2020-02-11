package com.example.flexboxlayoutactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<ViewItemDTO> itemDtoList;

    public ViewAdapter(List<ViewItemDTO> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_flexbox_layout_recycler_view_item, parent, false);

        final TextView textItem = (TextView)itemView.findViewById(R.id.flex_box_recycler_view_text_item);
        textItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "You click text : " + textItem.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        ViewHolder ret = new ViewHolder(itemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ViewItemDTO itemDto = itemDtoList.get(position);
        // Set item image resource id.
        // holder.getImageItem().setImageResource(itemDto.getImageId());
        // Set item text.
        holder.getTextItem().setText(itemDto.getText());
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(this.itemDtoList!=null)
        {
            ret = itemDtoList.size();
        }
        return ret;
    }
}