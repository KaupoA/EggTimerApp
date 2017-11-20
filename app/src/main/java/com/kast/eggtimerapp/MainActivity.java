package com.kast.eggtimerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerText;
    Button startAndStopButton;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;
    MediaPlayer mplayer;

    public void resetTimer() {

        timerText.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        startAndStopButton.setText("Go!");
        counterIsActive = false;

    }

    public void updateTimer(int secondsLeft) {

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {

            secondString = "0" + secondString;

        }

        timerText.setText(Integer.toString(minutes) + ":" + secondString);

    }

    public void startAndStop (View view) {

        if (counterIsActive == false) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startAndStopButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    resetTimer();
                    mplayer = MediaPlayer.create(getApplicationContext(), R.raw.tornadosiren);
                    mplayer.start();

                    Toast.makeText(getApplicationContext(), "Click on the egg to stop the alarm!", Toast.LENGTH_LONG).show();

                }
            }.start();
        } else {

            resetTimer();

        }
    }

    public void stopAlarm (View view) {

        if (mplayer.isPlaying()) {

            mplayer.stop();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
        timerText = (TextView)findViewById(R.id.timerText);
        startAndStopButton = (Button)findViewById(R.id.startAndStopButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
