package com.example.flexboxlayoutactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {
    private List<Answer> answers;
    private Context context;
    private AnswerAdapterListener listener;
    private int positionAnswer;
    private int limitClick;

    public AnswerAdapter(List<Answer> answers, int limitClick, Context context, AnswerAdapterListener listener) {
        this.answers = answers;
        this.context = context;
        this.listener = listener;
        this.limitClick = limitClick;
    }

    public void setData(int positionAnswer) {
        this.positionAnswer = positionAnswer;
        notifyDataSetChanged();
        limitClick++;
    }

    public int getLimitClick() {
        return limitClick;
    }

    @NonNull
    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnswerAdapter.ViewHolder holder, final int position) {
        if (AnswerAdapter.this.positionAnswer == position) {
            holder.tvAnswer.setVisibility(View.VISIBLE);
        }
        final Answer answer = answers.get(position);
        holder.tvAnswer.setText(answer.getAnswer());
        holder.tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && limitClick > 0) {
                    holder.tvAnswer.setVisibility(View.INVISIBLE);
                    listener.onClick(answer, position);
                    limitClick--;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return answers != null ? answers.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
        }
    }

    public interface AnswerAdapterListener {
        void onClick(Answer answer, int positionAnswer);
    }
}
