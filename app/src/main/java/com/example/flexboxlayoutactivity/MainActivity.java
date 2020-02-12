package com.example.flexboxlayoutactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // This is the text that will be rendered in the screen.
    //private String textArr[] = {"dev2qa.com", "is", "a very good", "android example website", "there are", "a lot of", "android, java examples"};
    private String str = "我是他的影迷我是他 _ 的影迷我是 他的影迷我是 _他的影迷我是_他的影迷我是他_ 的影迷我是他的影迷";
    private String strAnswer = "我是他 的影迷 我是他 的影迷我 是他的";
    private String textArr[];
    private String textArrAnswer[];
    private QuestionAdapter questionAdapter;
    private AnswerAdapter answerAdapter;
    private int limitClickAnswer;
    private RecyclerView rcvQuestion;
    private RecyclerView rcvAnswer;
    private TextView btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("dev2qa.com - FlexboxLayout Example.");

        initDataQuestion();
        initDataAnswer();
        btnCheck = findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCheck.getText().equals("Kiểm tra")){
                    if (answerAdapter.getLimitClick() <= 0){
                        questionAdapter.checkAnswer(true);
                        btnCheck.setText("Tiếp tục");
                    }else {
                        Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ từ", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, questionAdapter.isCorrectAll() + " ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initDataQuestion() {
        textArr = str.trim().split(Constant.EMPTY_STRING);
        // Get the RecyclerView object.
        rcvQuestion = findViewById(R.id.rcv_question);

        // Create the FlexboxLayoutMananger, only flexbox library version 0.3.0 or higher support.
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getApplicationContext());
        // Set flex direction.
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        // Set JustifyContent.
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        rcvQuestion.setLayoutManager(flexboxLayoutManager);

        // Set adapter object.
        questionAdapter = new QuestionAdapter(this, this.initViewItemDtoList(), new QuestionAdapter.QuestionAdapterListener() {
            @Override
            public void onClick(int positionAnswer) {
                answerAdapter.setData(positionAnswer);
            }
        });
        rcvQuestion.setAdapter(questionAdapter);
    }

    private void initDataAnswer() {
        textArrAnswer = strAnswer.split(Constant.SPACE);
        // Get the RecyclerView object.
        rcvAnswer = findViewById(R.id.rcv_answer);

        // Create the FlexboxLayoutMananger, only flexbox library version 0.3.0 or higher support.
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getApplicationContext());
        // Set flex direction.
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        // Set JustifyContent.
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        rcvAnswer.setLayoutManager(flexboxLayoutManager);

        // Set adapter object.
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < textArrAnswer.length; i++) {
            Answer answer = new Answer();
            answer.setAnswer(textArrAnswer[i]);
            answer.setPositionCorrect(i + 1);
            answers.add(answer);
        }
        answerAdapter = new AnswerAdapter(answers, limitClickAnswer, this, new AnswerAdapter.AnswerAdapterListener() {
            @Override
            public void onClick(Answer answer, int positionAnswer) {
                questionAdapter.setDataAnswer(answer, positionAnswer);
            }
        });
        rcvAnswer.setAdapter(answerAdapter);
    }

    private List<Question> initViewItemDtoList() {
        List<String> questions = new ArrayList<>(Arrays.asList(textArr));
        questions.removeAll(Arrays.asList(Constant.EMPTY_STRING, null));
        questions.removeAll(Arrays.asList(Constant.SPACE, null));
        List<Question> ret = new ArrayList<>();
        for (String question : questions) {
            Question itemDto = new Question();
            if (question.trim().equals(Constant.UNDERSCORE)) {
                limitClickAnswer++;
                itemDto.setQuestion(Constant.EMPTY_STRING);
                itemDto.setClick(true);
                itemDto.setPositionCorrect(limitClickAnswer);
            } else {
                itemDto.setQuestion(question);
                itemDto.setClick(false);
            }

            ret.add(itemDto);
        }
        return ret;
    }
}
