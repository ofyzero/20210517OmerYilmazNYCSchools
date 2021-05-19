package com.example.a20210517_omeryilmaz_nycschools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {
    TextView math , reading , writing ;
    String mathData , readingData, writingData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        math = findViewById(R.id.math);
        reading = findViewById(R.id.reading);
        writing = findViewById(R.id.writing);

        getData();
        setData();
    }

    // get data when press name of school
    private void getData(){
        if (getIntent().hasExtra("math") && getIntent().hasExtra("reading") && getIntent().hasExtra("writing") ){

            mathData = getIntent().getStringExtra("math");
            readingData = getIntent().getStringExtra("reading");
            writingData = getIntent().getStringExtra("writing");

        }else {
            Toast.makeText(this,"No data . ", Toast.LENGTH_SHORT).show();
        }
    }
    // set data for UI
    private void setData(){
        math.setText(   "SAT Math Avg. Score:      " + mathData);
        reading.setText("SAT Reading Avg. Score:   " + readingData);
        writing.setText("SAT Writing Avg. Score:   " + writingData);
    }
}