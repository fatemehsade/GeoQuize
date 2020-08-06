package com.example.geoquize;

public class Question {
    private int mTextResId;
    private boolean mAnswerIsTrue;

    public Question(int TextResId, boolean AnswerIsTrue) {
        this.mTextResId = TextResId;
        this.mAnswerIsTrue = AnswerIsTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerIsTrue() {
        return mAnswerIsTrue;
    }

    public void setAnswerIsTrue(boolean answerIsTrue) {
        mAnswerIsTrue = answerIsTrue;
    }
}
