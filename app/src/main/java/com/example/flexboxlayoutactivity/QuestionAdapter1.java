package com.example.flexboxlayoutactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter1 extends RecyclerView.Adapter<ViewHolder> {

    private List<Question> itemDtoList;
    private Context context;
    private String answer = "";
    private int position = -1;

    private ViewAdapterListener listener;

    public QuestionAdapter1(Context context, List<Question> itemDtoList, ViewAdapterListener listener) {
        this.itemDtoList = itemDtoList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);

        final TextView textItem = (TextView) itemView.findViewById(R.id.tv_question);
        ViewHolder ret = new ViewHolder(itemView);
        return ret;
    }

    public void setDataAnswer(String dataAnswer, int position) {
        this.answer = dataAnswer;
        this.position = position;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Question itemDto = itemDtoList.get(position);
        // Set item image resource id.
        // holder.getImageItem().setImageResource(itemDto.getImageId());
        // Set item text.
        if (itemDto.isClick()) {
            holder.getTextItem().setPadding(50, 0, 50, 0);
        } else {
            holder.getTextItem().setPadding(0, 0, 0, 0);
        }
        holder.getTextItem().setBackgroundResource(itemDto.isClick() ? R.drawable.background_blue : R.drawable.background_white);
        if (itemDto.getQuestion().isEmpty()) {
            holder.getTextItem().setText(answer);
            if (!answer.isEmpty()) {
                itemDto.setQuestion(answer);
                itemDto.setPositionAnswer(this.position);
            }
            answer = "";
        } else {
            holder.getTextItem().setText(itemDto.getQuestion());
        }
        holder.getTextItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemDto.isClick() && !itemDto.getQuestion().isEmpty()) {
                    Toast.makeText(view.getContext(), "You click text : " + holder.getTextItem().getText(), Toast.LENGTH_SHORT).show();
                    if (listener != null) {
                        listener.onClick(itemDto.getPositionAnswer());
                    }
                    itemDto.setQuestion("");
                    itemDto.setPositionAnswer(-1);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (this.itemDtoList != null) {
            ret = itemDtoList.size();
        }
        return ret;
    }

    public interface ViewAdapterListener {
        void onClick(int position);
    }
}