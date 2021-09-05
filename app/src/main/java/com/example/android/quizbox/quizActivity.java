package com.example.android.quizbox;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;

public class quizActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue = 100;
    ProgressBar progressBar;
    ArrayList<Modal> list;
    TextView ques, op1, op2, op3, op4, ans;
    CardView cardoa,cardob,cardoc,cardod;
    int index = 0,correctCount=0,wrongCount=0;
    Modal modalclass;
    Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Hooks();

        nextbtn.setClickable(false);

        list = new ArrayList<>();
        list.add(new Modal("In Back to the Future, what year does Marty McFly travel back in time to?", "1945", "1950", "1955", "1960", "1955"));
        list.add(new Modal("In Coming to America, Prince Akeem and Semmi take jobs working at a knock-off of which company?", "Walmart", "McDonald's", "Chuck E. Cheese", "Blockbuster Video", "McDonald's"));
        list.add(new Modal("Where did Kevin's family travel to in Home Alone?", "Paris", "New York", "London", "Los Angeles", "Paris"));
        list.add(new Modal("What is the name of the villainous teddy bear in Toy Story 3?", "Hugs-A-Lot", "Paddy", "Lotso", "Fitz-O", "Lotso"));
        list.add(new Modal("What is the name of Jeff Goldblum's character in Jurassic Park?", "Dr. Elliott Grant", "Dr. Max Donor", "Dr. Ari Richards", "Dr. Ian Malcom", "Dr. Ian Malcom"));
        list.add(new Modal("In Tangled, what is Flynn Rider's real name?", "Eugene Fitzherbert", "Herbert Barney", "Melvin Orvillert", "Morris Motimer", "Eugene Fitzherbert"));
        list.add(new Modal("In Black Panther, T'Challa, Okoye, and Nakia travel to which country in search of vibranium being sold on the black market?", "Japan", "Brazil", "South Korea", "England", "South Korea"));
        list.add(new Modal("In The Hunger Games, what District were Katniss and Peeta from?", "District 5", "District 9", "District 12", "District 19", "District 12"));
        list.add(new Modal("The Princess Diaries takes place in what fictional country?", "Caledonia", "Genovia", "Estrovia", "Madelvia", "Genovia"));
        list.add(new Modal("In Frozen, what event brings Prince Hans to Arendelle?", "Elsa's coronation", "Arnedelle's bicentennial", "Anna's 18th birthday", "Elsa's ball for her 20th birthday", "Elsa's coronation"));


        Collections.shuffle(list);
        modalclass = list.get(index);

        setAllData();

        countDownTimer = new CountDownTimer(20000,1000) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue -= 5;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(quizActivity.this);
                dialog.setContentView(R.layout.timeout_dialog);
                dialog.findViewById(R.id.tryagainBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(quizActivity.this,dashboardActivity.class);
                        startActivity(intent);
                    }

                });
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });

                dialog.show();


            }
        }.start();

    }

    private void setAllData() {

        ques.setText(modalclass.getQuestion());
        op1.setText(modalclass.getOp1());
        op2.setText(modalclass.getOp2());
        op3.setText(modalclass.getOp3());
        op4.setText(modalclass.getOp4());
    }

    private void Hooks() {
        ques = findViewById(R.id.quiz2_ques);
        op1 = findViewById(R.id.quiz2_ques_option1);
        op2 = findViewById(R.id.quiz2_ques_option2);
        op3 = findViewById(R.id.quiz2_ques_option3);
        op4 = findViewById(R.id.quiz2_ques_option4);
        progressBar = findViewById(R.id.progressBar);
        nextbtn = findViewById(R.id.nextBtn);
        cardoa = findViewById(R.id.cardoa);
        cardob = findViewById(R.id.cardob);
        cardoc = findViewById(R.id.cardoc);
        cardod = findViewById(R.id.cardod);
    }

    public void exit(View view) {
        finish();
    }

    public void correct(CardView cardoa){
        cardoa.setCardBackgroundColor(getResources().getColor(R.color.green));
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                modalclass=list.get(index);
                resetColor();
                enablebutton();
                setAllData();

            }
        });

    }
    public void wrong(CardView cardoa){
        cardoa.setCardBackgroundColor(getResources().getColor(R.color.red));

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if(index<list.size() - 1){
                    index++;
                    modalclass = list.get(index);
                    resetColor();
                    enablebutton();
                    setAllData();
                }else {
                    GameWon();
                }
            }
        });

    }

    private void GameWon() {
        Intent intent = new Intent(quizActivity.this,scoreCardActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);
    }

    public void enablebutton(){
        cardoa.setClickable(true);
        cardob.setClickable(true);
        cardoc.setClickable(true);
        cardod.setClickable(true);

    }
    public void disablebutton(){
        cardoa.setClickable(false);
        cardob.setClickable(false);
        cardoc.setClickable(false);
        cardod.setClickable(false);

    }
    public void resetColor(){
        cardoa.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardob.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardoc.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardod.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionAClick(View view) {
        disablebutton();
        nextbtn.setClickable(true);

        if(modalclass.getOp1().equals(modalclass.getAns())){
            if(index<list.size()-1){
                correct(cardoa);
            }
            else{
                correctCount++;
                GameWon();
            }

        }
        else{

            wrong(cardoa);
        }
    }
    public void OptionBClick(View view) {
        disablebutton();
        nextbtn.setClickable(true);


        if(modalclass.getOp2().equals(modalclass.getAns())){
            if(index<list.size()-1){
                correct(cardob);


            }
            else{
                correctCount++;
                GameWon();
            }

        }
        else{
            wrong(cardob);
        }
    }
    public void OptionCClick(View view) {
        disablebutton();
        nextbtn.setClickable(true);


        if(modalclass.getOp3().equals(modalclass.getAns())){
            if(index<list.size()-1){
                correct(cardoc);

            }
            else{
                correctCount++;
                GameWon();
            }

        }
        else{
            wrong(cardoc);
        }
    }
    public void OptionDClick(View view) {
        disablebutton();
        nextbtn.setClickable(true);


        if(modalclass.getOp4().equals(modalclass.getAns())){
            if(index<list.size()-1){
                correct(cardod);
            }
            else{
                correctCount++;
                GameWon();
            }

        }
        else{
            wrong(cardod);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}