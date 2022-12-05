package com.example.mathquizappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    TextView tv_score, tv_questions, tv_timer, tv_bottommessage;
    ProgressBar prog_timer;

    Game g = new Game();

    int secondRemaining = 30;

    CountDownTimer timer = new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long l) {
            secondRemaining--;
            tv_timer.setText(Integer.toString(secondRemaining) + "sec");
            prog_timer.setProgress(30 - secondRemaining);
        }

        @Override
        public void onFinish() {
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            tv_bottommessage.setText(" TIME IS UP! " + g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));



            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                    Intent tryagain  = new Intent (QuizActivity.this , GameOver.class);
                    startActivity(tryagain);

                }
            }, 4000);
        }
    };


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        btn_start = findViewById(R.id.btn_start);
        btn_answer0 = findViewById(R.id.btn_answer0);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);

        tv_score = findViewById(R.id.tv_score);
        tv_questions = findViewById(R.id.tv_questions);
        tv_bottommessage = findViewById(R.id.tv_bottommessage);
        tv_timer = findViewById(R.id.tv_timer);

        prog_timer = findViewById(R.id.prog_timer);
        tv_timer.setText("0 sec");
        tv_questions.setText("");
        tv_bottommessage.setText(" PRESS START ");
        tv_score.setText("0 pts");
        prog_timer.setProgress(0);

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Button start_button =(Button) v;


                start_button.setVisibility(View.INVISIBLE);
                secondRemaining = 30;

                nextTurn();
                timer.start();
            }
        };

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                g.checkAnswer(answerSelected);
                tv_score.setText(Integer.toString(g.getScore()));
                nextTurn();
            }
        };

        btn_start.setOnClickListener(startButtonClickListener);
        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);
    }
    private void nextTurn(){
        g.makeNewQuestion();
        int [] answer = g.getCurrentQuestion().getAnswerArray();

        btn_answer0.setText(Integer.toString(answer[0]));
        btn_answer1.setText(Integer.toString(answer[1]));
        btn_answer2.setText(Integer.toString(answer[2]));
        btn_answer3.setText(Integer.toString(answer[3]));

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);


        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_bottommessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));
    }


    public class Question {
        private int firstNumber;
        private int secondNumber;
        private int answer;

        // store the answer
        private int [] answerArray;

        // answer choices to be picked
        private int answerPosition;

        // max value of the first or second number
        private int upperLimit;

        // string output of the question problem
        private String questionPhrase;

        // generate a new random question
        public Question (int upperLimit){
            this.upperLimit = upperLimit;
            Random randomNumberMaker = new Random();

            this.firstNumber = randomNumberMaker.nextInt(upperLimit);
            this.secondNumber = randomNumberMaker.nextInt(upperLimit);
            this.answer = this.firstNumber + this.secondNumber;
            this.questionPhrase = firstNumber + " + " + secondNumber + " = ";

            this.answerPosition = randomNumberMaker.nextInt(4);
            this.answerArray = new int[] {0,1,2,3};

            this.answerArray[0] = answer + 1;
            this.answerArray[1] = answer + 10;
            this.answerArray[2] = answer - 5;
            this.answerArray[3] = answer - 2;

            this.answerArray = shuffleArray(this.answerArray);

            answerArray[answerPosition] = answer;
        }
        private int [] shuffleArray(int[] array){
            int index, temp;
            Random randomNumberGenerator = new Random();

            for (int i = array.length - 1 ; i > 0 ; i--){
                index = randomNumberGenerator.nextInt(i + 1);
                temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
            return array;
        }

        public int getFirstNumber() {
            return firstNumber;
        }

        public void setFirstNumber(int firstNumber) {
            this.firstNumber = firstNumber;
        }

        public int getSecondNumber() {
            return secondNumber;
        }

        public void setSecondNumber(int secondNumber) {
            this.secondNumber = secondNumber;
        }

        public int[] getAnswerArray() {
            return answerArray;
        }

        public void setAnswerArray(int[] answerArray) {
            this.answerArray = answerArray;
        }

        public int getAnswerPosition() {
            return answerPosition;
        }

        public void setAnswerPosition(int answerPosition) {
            this.answerPosition = answerPosition;
        }

        public int getUpperLimit() {
            return upperLimit;
        }

        public void setUpperLimit(int upperLimit) {
            this.upperLimit = upperLimit;
        }

        public String getQuestionPhrase() {
            return questionPhrase;
        }

        public void setQuestionPhrase(String questionPhrase) {
            this.questionPhrase = questionPhrase;
        }

        public int getAnswer() {
            return answer;
        }

        public void setAnswer(int answer) {
            this.answer = answer;
        }
    }


    public class Game {
        private List<Question> questions;
        private int numberCorrect;
        private int numberIncorrect;
        private int totalQuestions;
        private int score;
        private Question currentQuestion;

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }

        public int getNumberCorrect() {
            return numberCorrect;
        }

        public void setNumberCorrect(int numberCorrect) {
            this.numberCorrect = numberCorrect;
        }

        public int getNumberIncorrect() {
            return numberIncorrect;
        }

        public void setNumberIncorrect(int numberIncorrect) {
            this.numberIncorrect = numberIncorrect;
        }

        public int getTotalQuestions() {
            return totalQuestions;
        }

        public void setTotalQuestions(int totalQuestions) {
            this.totalQuestions = totalQuestions;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Question getCurrentQuestion() {
            return currentQuestion;
        }

        public void setCurrentQuestion(Question currentQuestion) {
            this.currentQuestion = currentQuestion;
        }

        public Game(){
            numberCorrect = 0;
            numberIncorrect = 0;
            totalQuestions = 0;
            score = 0;
            currentQuestion = new Question (10);
            questions = new ArrayList<Question>();
        }

        public void makeNewQuestion() {
            currentQuestion = new Question ( totalQuestions * 2 + 5);
            totalQuestions ++;
            questions.add(currentQuestion);
        }

        public boolean checkAnswer (int submittedAnswer){
            boolean isCorrect;
            if (currentQuestion.getAnswer() == submittedAnswer){
                numberCorrect++;
                isCorrect = true;
            }
            else{
                numberIncorrect++;
                isCorrect = false;
            }
            score = numberCorrect * 10 - numberIncorrect * 30;
            return isCorrect;
        }



    }

}