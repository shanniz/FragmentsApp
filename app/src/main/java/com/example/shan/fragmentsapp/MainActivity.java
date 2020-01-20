package com.example.shan.fragmentsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements FragmentMain.FragmentMainListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void replaceFragment(View view) {
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.detailFragmentContainer,
                new FragmentDetail());
        fragmentTransaction.commit();
    }

    public void addFragment(View view) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("data",
                ((EditText)findViewById(R.id.editTextData)).getText().toString()
        );

        FragmentDetail fragmentDetail = new FragmentDetail();
        fragmentDetail.setArguments(bundle);
        fragmentTransaction.add(R.id.detailFragmentContainer,
                fragmentDetail
        ).commit();
    }
    public void removeFagment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment =
                fragmentManager.findFragmentById(R.id.detailFragmentContainer);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment =
                getSupportFragmentManager().findFragmentById(R.id.detailFragmentContainer);
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .remove(fragment)
                    .commit();
        }else {
            super.onBackPressed();
        }
    }



    @Override
    public void onDataSent(String input) {
        FragmentDetail fragmentDetail = (FragmentDetail)
                getSupportFragmentManager().findFragmentById(R.id.detailFragmentContainer);
        if(fragmentDetail!=null)
            fragmentDetail.updateData(input);
    }
}
