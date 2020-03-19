package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLEncoder;

public class url extends AppCompatActivity {


    private android.widget.EditText EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        EditText = findViewById(R.id.editText);
        Button send = findViewById(R.id.openUrl);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                send();
            }
        });
    }

    private void send() {
        EditText = findViewById(R.id.editText);
        String msg=EditText.getText().toString();
        Uri uri = Uri.parse("http://www.google.com/#q=" + msg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
