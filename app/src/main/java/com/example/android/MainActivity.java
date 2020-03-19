package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN ACTIVITY";
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            text = savedInstanceState.getString("text");
            final TextView infoTextView = findViewById(R.id.textView);
            infoTextView.setText(text);
        }
        Log.i(TAG, "onCreate");

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Android");
        arrayList.add("Cloud");
        arrayList.add("Calcul Numeric");
        arrayList.add("Grafica");
        arrayList.add("Carduri Smart");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        final ListView list = findViewById(R.id.list);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) list.getItemAtPosition(position);
                text="Materia " +  clickedItem;
                final TextView text = findViewById(R.id.textView);
                text.setText(MainActivity.this.text);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.openUrl:
                Log.i(TAG, "Menu -> Open Url Activity");
                openActivityUrl();
                return true;

            case R.id.dialog:
                Log.i(TAG, "Menu -> Login Dialog");
                openLoginDialog();
                return true;

            case R.id.Exit:
                Log.i(TAG, "Menu -> Exit");
                finish();
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }


    public void openLoginDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.log_dialog, null);
        builder.setView(dialogView);
        final EditText username= dialogView.findViewById(R.id.username);
        final EditText pass= dialogView.findViewById(R.id.passLogin);
        Button login= dialogView.findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i(TAG, "Username: " + username.getText().toString());
                Log.i(TAG, "Password:" + pass.getText().toString());

            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }

    public void openActivityUrl(){
        Intent intent=new Intent(this, url.class);
        startActivity(intent);
    }

}
