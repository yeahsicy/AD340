package com.example.jerry.ad340_hw5;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class dataTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void writeTest() throws Throwable {
        mActivityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MainActivity mainActivity = mActivityTestRule.getActivity();
                AutoCompleteTextView textView = (AutoCompleteTextView) mainActivity.findViewById(R.id.autoCompleteTextView);
                textView.setText("test");
                mainActivity.stringHashSet.clear();
                mainActivity.getSharedPreferences(MainActivity.HISTORY, Context.MODE_PRIVATE).edit().clear().apply();
                mainActivity.GoToSecondActivity(new View(mainActivity.getApplicationContext()));
                Set<String> set = mainActivity.getSharedPreferences(MainActivity.HISTORY, Context.MODE_PRIVATE).getStringSet(MainActivity.HISTORY, mainActivity.stringHashSet);

                assertEquals("test", set.toArray(new String[0])[0]);
            }
        });
    }

    @Test
    public void readTest() throws Throwable {
        mActivityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MainActivity activity = mActivityTestRule.getActivity();
                SharedPreferences pref = activity.getSharedPreferences(MainActivity.HISTORY, Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>();
                AutoCompleteTextView textView = activity.findViewById(R.id.autoCompleteTextView);

                pref.edit().clear().apply();
                set.add("test");
                pref.edit().putStringSet(MainActivity.HISTORY, set).apply();

                activity.BindPreferencesToAutoComplete(pref, textView);

                String item = (String) textView.getAdapter().getItem(0);
                assertEquals("test", item);
            }
        });

    }
}
