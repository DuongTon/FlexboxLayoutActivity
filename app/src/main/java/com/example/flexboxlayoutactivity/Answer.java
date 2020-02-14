package com.example.flexboxlayoutactivity;

public class Answer {
    private String answer;
    private int positionCorrect;

    private boolean isGone;

    public boolean isGone() {
        return isGone;
    }

    public void setGone(boolean gone) {
        isGone = gone;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPositionCorrect() {
        return positionCorrect;
    }

    public void setPositionCorrect(int positionCorrect) {
        this.positionCorrect = positionCorrect;
    }
}
