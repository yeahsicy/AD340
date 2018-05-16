package com.example.jerry.ad340_hw5;

import android.widget.AutoCompleteTextView;


public class util {
    public static String Text_entry_validation_info(String text) {
        return text.length() == 0 ? "Text-entry field is empty" : "";
    }
}
