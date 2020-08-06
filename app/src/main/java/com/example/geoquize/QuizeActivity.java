package com.example.geoquize;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizeActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mImageButton;
    private ImageButton mPrevImage;
    private TextView mQuestionTextView;
    private ImageButton mDoubleRight;
    private ImageButton mDoubleLeft;
    private int mCurrentIndex=0;
    private boolean isClickable=true;
    private Question [] mQuestionBank={
            new Question(R.string.question_australia,false),
            new Question(R.string.question_american,false),
            new Question(R.string.question_ocean,false),
            new Question(R.string.question_asia,false),
            new Question(R.string.question_africa,true)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrueButton=findViewById(R.id.true_button);
        mFalseButton=findViewById(R.id.false_button);
        mQuestionTextView =findViewById(R.id.question_text_view);
        updateQuestion();


        mDoubleRight=findViewById(R.id.image_double_right);
            mDoubleRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 0;
                    updateQuestion();
                }
            });


        mDoubleLeft=findViewById(R.id.image_double_left);
        mDoubleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=mQuestionBank.length-1;
                updateQuestion();
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();

            }
        });
        if (isClickable==false) {


            mTrueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(true);
                    isClickable=true;


                }
            });
        }
        if (isClickable==false) {

            mFalseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(false);
                    isClickable=true;


                }
            });
        }
        mImageButton=findViewById(R.id.image_right);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionTextView.setTextColor(Color.BLACK);
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }


        });
        mPrevImage=findViewById(R.id.image_left);
        mPrevImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex-1+mQuestionBank.length)%mQuestionBank.length;
                updateQuestion();
            }
        });
    }


    private void updateQuestion() {
        int question=mQuestionBank[mCurrentIndex].getTextResId();
        isClickable=false;
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressTrue){
        boolean AnswerIsTrue=mQuestionBank[mCurrentIndex].isAnswerIsTrue();
        int massageRes=0;

        if (AnswerIsTrue==userPressTrue){
            massageRes=R.string.correct_toast;
            Toast toast=Toast.makeText(QuizeActivity.this,massageRes,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,0);
            toast.getView().setBackgroundColor(Color.GREEN);
            TextView textView=toast.getView().findViewById(android.R.id.message);
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_check_24,0,0,0);
            mQuestionTextView.setTextColor(Color.GREEN);
            isClickable=true;
            toast.show();



        }
        else {
            massageRes=R.string.incorrect_toast;
            Toast toast=Toast.makeText(QuizeActivity.this,massageRes,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,0);
            toast.getView().setBackgroundColor(Color.RED);
            TextView textView=toast.getView().findViewById(android.R.id.message);
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_clear_24,0,0,0);
            mQuestionTextView.setTextColor(Color.RED);
            isClickable=true;
            toast.show();


        }
    }

}