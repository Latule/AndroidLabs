package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = "MAIN ACTIVITY";
    private static final String FILE_NAME = "export.txt";
    private String text;

    SharedPreferences sharedpreferences;
    int sizeText = 10;
    public static final String mypreference = "MyPref";

    ArrayList<String> arrayList = new ArrayList<String>(
            Arrays.asList(
            "Android",
            "Cloud",
            "Calcul Numeric",
            "Grafica",
            "Carduri Smart"
            ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (savedInstanceState != null) {
            text = savedInstanceState.getString("text");
            final TextView infoTextView = findViewById(R.id.textView);
            infoTextView.setText(text);
            infoTextView.setTextSize((float) sizeText);
        }
        Log.i(TAG, "onCreate");

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
                text.setTextSize( (float) sizeText);
            }
        });

        setupSharedPreferences();
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        final TextView text = findViewById(R.id.textView);
        text.setTextSize((float) sizeText);
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
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
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

            case R.id.export:
                Log.i(TAG, "Menu -> Export");
                export();
                return true;

            case R.id.action_settings:
                Log.i(TAG, "Menu -> Settings");
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;


            case R.id.Exit:
                Log.i(TAG, "Menu -> Exit");
                finish();
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }

    public void export() {
        String text = arrayList.toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

    private void setTextSize(int sizeText) {
        final TextView text = findViewById(R.id.textView);
        final TextView infoTextView = findViewById(R.id.textView);
        text.setTextSize((float) sizeText);
        infoTextView.setTextSize((float) sizeText);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("font_size")){
            sizeText = Integer.parseInt(sharedpreferences.getString("font_size","10"));
            setTextSize(sizeText);
        }
    }


}
