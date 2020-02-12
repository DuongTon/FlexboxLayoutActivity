package com.example.flexboxlayoutactivity;

public class Question {
    private String question = Constant.EMPTY_STRING;
    private boolean isClick;
    private int positionAnswer;
    private int positionCorrect;
    private boolean isCorrect;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public int getPositionAnswer() {
        return positionAnswer;
    }

    public void setPositionAnswer(int positionAnswer) {
        this.positionAnswer = positionAnswer;
    }

    public int getPositionCorrect() {
        return positionCorrect;
    }

    public void setPositionCorrect(int positionCorrect) {
        this.positionCorrect = positionCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}