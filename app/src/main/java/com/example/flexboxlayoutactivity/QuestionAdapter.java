package com.example.flexboxlayoutactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questions;
    private Context context;
    private Answer answer;
    private int positionAnswer = Constant.DEFAULT_RES_ID;
    private QuestionAdapterListener listener;
    private boolean checkAnswer;
    private boolean isCorrectAll = true;

    public QuestionAdapter(Context context, List<Question> questions, QuestionAdapterListener listener) {
        this.questions = questions;
        this.context = context;
        this.listener = listener;
    }

    public void setDataAnswer(Answer dataAnswer, int positionAnswer) {
        this.answer = dataAnswer;
        this.positionAnswer = positionAnswer;
        notifyDataSetChanged();
    }

    public void checkAnswer(boolean isCheckAnswer) {
        this.checkAnswer = isCheckAnswer;
        notifyDataSetChanged();
    }

    public boolean isCorrectAll() {
        return isCorrectAll;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Question question = questions.get(position);
        if (answer != null && question.isClick() && answer.getPositionCorrect() == question.getPositionCorrect()) {
            question.setCorrect(true);
        }
        if (question.isClick()) {
            holder.tvQuestion.setPadding(50, 0, 50, 0);
        } else {
            holder.tvQuestion.setPadding(0, 0, 0, 0);
        }
        if (question.isClick()) {
            if (!question.isCorrect() && checkAnswer) {
                holder.tvQuestion.setBackgroundResource(R.drawable.background_read);
                isCorrectAll = false;
            } else {
                holder.tvQuestion.setBackgroundResource(R.drawable.background_blue);
            }
        } else {
            holder.tvQuestion.setBackgroundResource(R.drawable.background_white);
        }

        if (question.getQuestion().isEmpty()) {
            holder.tvQuestion.setText(answer == null || answer.getAnswer() == null ? Constant.EMPTY_STRING : answer.getAnswer());
            if (answer != null && answer.getAnswer() != null && !answer.getAnswer().isEmpty()) {
                question.setQuestion(answer.getAnswer());
                question.setPositionAnswer(this.positionAnswer);
                answer = null;
            }
        } else {
            holder.tvQuestion.setText(question.getQuestion());
        }
        holder.tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkAnswer && question.isClick() && !question.getQuestion().isEmpty()) {
                    Toast.makeText(view.getContext(), "You click text : " + holder.tvQuestion.getText(), Toast.LENGTH_SHORT).show();
                    if (listener != null) {
                        listener.onClick(question.getPositionAnswer());
                        question.setCorrect(false);
                    }
                    question.setQuestion(Constant.EMPTY_STRING);
                    question.setPositionAnswer(Constant.DEFAULT_RES_ID);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions != null ? questions.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tv_question);
        }
    }

    public interface QuestionAdapterListener {
        void onClick(int positionAnswer);
    }
}
