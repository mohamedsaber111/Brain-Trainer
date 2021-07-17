package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2 ;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameConstraintLayout;

    ArrayList<Integer> answers =new ArrayList<Integer>(); // list random answer is the 4 button
    int locationOfCorrectAnswer ;
    int score =0 ; // to add another question
    int numerOfQuestions=0; // to add number of correct answer 0/0

    public void start(View view ){
        startButton.setVisibility(view.INVISIBLE);
        gameConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);

        playAgain(findViewById(R.id.PlayAgainButton));
    }

    public void playAgain(View view){
        score=0;
        numerOfQuestions=0;

        timerTextView.setText("0s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(view.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100,1000) { //30 second and countDown every second
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(view.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("your score is "+ Integer.toString(score)+"/"+Integer.toString(numerOfQuestions));
                sumTextView.setText("play again?");
            }

        }.start();
    }

    public void generateQuestion(){ // to add several questions when you answer to question

        Random random = new Random();
        int a = random.nextInt(101) ; //generate the random number between 0-100
        int b = random.nextInt(101);
        sumTextView.setText(Integer.toString(a)+ " + "+Integer.toString(b));

        locationOfCorrectAnswer=random.nextInt(4); //generate the random number between 0-3 becouse there is 4 suggest answer

        answers.clear(); // becouse when start anew question the value of result added to old answer

        int inCorrectAnswer ; //maybe the randomNumber of result answer =to correct answer
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{

                inCorrectAnswer =random.nextInt(201); // becouse we add to number with 100

                while(inCorrectAnswer== a+b){
                    inCorrectAnswer =random.nextInt(201);
                }
                answers.add(inCorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0))); //tag0
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }else {
            resultTextView.setText("wrong!");
        }
        numerOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numerOfQuestions));
        generateQuestion();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton =(Button)findViewById(R.id.startButton);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        pointsTextView =(TextView)findViewById(R.id.pointsTextView);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        button0 =(Button)findViewById(R.id.button1);
        button1 =(Button)findViewById(R.id.button2);
        button2 =(Button)findViewById(R.id.button3);
        button3 =(Button)findViewById(R.id.button4);
        playAgainButton=(Button)findViewById(R.id.PlayAgainButton);
        gameConstraintLayout=(ConstraintLayout)findViewById(R.id.gameConstraintLayout);




        }
}