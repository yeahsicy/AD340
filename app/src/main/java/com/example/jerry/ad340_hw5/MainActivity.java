package com.example.jerry.ad340_hw5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static final String HISTORY = "HISTORY";
    HashSet<String> stringHashSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView completeTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        completeTextView.setThreshold(1);
        SharedPreferences preferences = getSharedPreferences(HISTORY, MODE_PRIVATE);
        BindPreferencesToAutoComplete(preferences, completeTextView);
    }

    public void BindPreferencesToAutoComplete(SharedPreferences preferences, AutoCompleteTextView completeTextView) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,
                preferences.getStringSet(HISTORY, stringHashSet).toArray(new String[0]));
        completeTextView.setAdapter(adapter);
        completeTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                AutoCompleteTextView v1 = (AutoCompleteTextView) v;
                v1.showDropDown();
            }
        });
    }

    public void GoToSecondActivity(View view) {
        AutoCompleteTextView completeTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        String validation_info = util.Text_entry_validation_info(completeTextView.getText().toString());
        //Text-entry field is empty
        if (validation_info.length() > 0) {
            Toast.makeText(getApplicationContext(), validation_info, Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences preferences = getSharedPreferences(HISTORY, MODE_PRIVATE);
        stringHashSet = (HashSet<String>) preferences.getStringSet(HISTORY, stringHashSet);
        stringHashSet.add(completeTextView.getText().toString());
        preferences.edit().putStringSet(HISTORY, stringHashSet).apply();
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
    }
}
