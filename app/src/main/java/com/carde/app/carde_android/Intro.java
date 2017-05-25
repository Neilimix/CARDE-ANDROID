package com.carde.app.carde_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button btnSkipIntro = (Button) findViewById(R.id.skip_intro);
        btnSkipIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connect = new Intent(Intro.this, Connect.class);
                startActivity(connect);
            }
        });
    }
}
