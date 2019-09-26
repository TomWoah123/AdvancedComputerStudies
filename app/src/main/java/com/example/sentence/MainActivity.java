package com.example.sentence;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private SeekBar seekBar;
    private List<String> words;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView image = findViewById( R.id.imageView );
        image.setVisibility(View.VISIBLE);
        image.postDelayed(new Runnable() {
            @Override
            public void run() {
                image.setVisibility(View.GONE);
            }
        }, 4000);
        createSeekBar();
    }

    public void createSeekBar()
    {
        EditText sent = findViewById( R.id.sentence );
        String sentence = sent.getText().toString();
        words = new ArrayList<>( Arrays.asList( sentence.split( " " ) ) );
        seekBar = findViewById( R.id.seekBar );
        seekBar.setMax( words.size() - 1 );
        sent.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                updateSeekBar();
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                getAnswer();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    private void updateSeekBar()
    {
        EditText sent = findViewById( R.id.sentence );
        String sentence = sent.getText().toString();
        words = new ArrayList<>( Arrays.asList( sentence.split( " " ) ) );
        seekBar = findViewById( R.id.seekBar );
        seekBar.setMax( words.size() - 1 );
    }

    public void getAnswer()
    {
        int position = seekBar.getProgress();
        String pos = String.valueOf( position + 1 );
        String word = words.get( position );
        answer = findViewById( R.id.answer );
        answer.setText( "Word " + pos + ": " + word );
    }
}
