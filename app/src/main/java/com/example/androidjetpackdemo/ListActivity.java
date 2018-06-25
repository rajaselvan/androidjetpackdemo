package com.example.androidjetpackdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidjetpackdemo.ui.ListFragment;

/**
 *  This class is our launcher activity.
 *
 * @author Rajaselvan
 */
public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ListFragment.newInstance())
                    .commitNow();
        }
    }
}
