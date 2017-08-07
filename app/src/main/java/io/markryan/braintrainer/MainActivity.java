package io.markryan.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startBtn, playAgainBtn;
    TextView sumTextView;
    TextView pointsTextView;
    TextView resultTextView;
    TextView timerTextView;
    RelativeLayout blockLayer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void start(View view) {
        startBtn.setVisibility(View.INVISIBLE);
        blockLayer.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainBtn));

    }

    public void playAgain(final View view){
        score = 0;
        numberOfQuestions = 0;
        pointsTextView.setText("0/0");
        resultTextView.setText(" ");
        timerTextView.setText("30s");
        playAgainBtn.setVisibility(view.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainBtn.setVisibility(view.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score:" + score + "/" + numberOfQuestions);
            }
        }.start();
    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        //clean array list so buttons change;
        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i<4; i++ ){
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {

        if (view.getTag().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");

        } else {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointsTextView.setText(score + "/" + numberOfQuestions);
        generateQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = (Button) findViewById(R.id.startBtn);
        playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        blockLayer = (RelativeLayout) findViewById(R.id.blockLayer);

        //game buttons
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

    }

}
