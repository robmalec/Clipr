package com.clipr.clipr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class testActivity extends AppCompatActivity {
    LinearLayout messageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        messageLayout = (LinearLayout) findViewById(R.id.testResultLayout);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> outputMessages = extras.getStringArrayList("outputMessages");
        for (String s : outputMessages)
        {
            TextView t = new TextView(testActivity.this);
            t.setText(s);
            messageLayout.addView(t);
        }


    }
}
