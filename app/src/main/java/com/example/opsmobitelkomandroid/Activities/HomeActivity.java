package com.example.opsmobitelkomandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.opsmobitelkomandroid.ModelResponse.LoginResponse;
import com.example.opsmobitelkomandroid.NavFragments.DashboardFragment;
import com.example.opsmobitelkomandroid.NavFragments.HistoryFragment;
import com.example.opsmobitelkomandroid.R;
import com.example.opsmobitelkomandroid.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    LoginResponse loginResponse;
    BottomNavigationView bottomNavigationView;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(this);
        loadFragment(new DashboardFragment());


        sharedPrefManager = new SharedPrefManager(getApplicationContext());


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.history:
                fragment = new HistoryFragment();
                break;
            case R.id.logout:
                showDialogLogout();

                break;
        }

        if (fragment != null){
            loadFragment(fragment);
        }
        return true;
    }

    private void showDialogLogout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Logout");
        alertDialogBuilder.setMessage("Anda yakin untuk Logout?");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when the "OK" button is clicked
                logoutUser();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when the "Cancel" button is clicked

            }
        });

        // Create and show the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void logoutUser() {




        sharedPrefManager.logout();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(this, "Berhasil Logout!", Toast.LENGTH_SHORT).show();

    }


    void loadFragment(Fragment fragment){

        //attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relative_layout, fragment).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}