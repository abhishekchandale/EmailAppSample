package com.abhishekchandale.emailsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.abhishekchandale.emailsample.databinding.ActivityMainBinding;
import com.abhishekchandale.emailsample.fragments.InBox;

public class MainActivity extends AppCompatActivity {
    private FragmentTransaction mFragmentTranscation;
    private FragmentManager mFragmentManager;
    private ActivityMainBinding mDataBinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinder = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        try {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTranscation = mFragmentManager.beginTransaction();
            InBox inBox = new InBox();
            mFragmentTranscation.replace(R.id.frame_main, inBox, InBox.class.getSimpleName());
            mFragmentTranscation.addToBackStack(InBox.class.getSimpleName());
            mFragmentTranscation.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
