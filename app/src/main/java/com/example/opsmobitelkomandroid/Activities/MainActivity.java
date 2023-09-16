package com.example.opsmobitelkomandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opsmobitelkomandroid.ModelResponse.Login;
import com.example.opsmobitelkomandroid.ModelResponse.LoginResponse;
import com.example.opsmobitelkomandroid.R;
import com.example.opsmobitelkomandroid.RegisterAkun;
import com.example.opsmobitelkomandroid.RetrofitClient;
import com.example.opsmobitelkomandroid.SharedPrefManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    TextView registerAkun;
    EditText et_idSales, et_passSales;
    Button btnLogin;
    SharedPrefManager sharedPrefManager;
    private ProgressDialog progressDialog;

    String idSales, passSales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        registerAkun = findViewById(R.id.tv_registerAkun);
        String text = "Belum punya akun? Silahkan Daftar";

        SpannableString ss = new SpannableString(text);


        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, RegisterAkun.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan, 27, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerAkun.setText(ss);
        registerAkun.setMovementMethod(LinkMovementMethod.getInstance());

        et_idSales = findViewById(R.id.et_idSales);
        et_idSales.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        et_passSales = findViewById(R.id.et_passSales);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userLogin();
            }


        });

    }

    private void userLogin() {
        idSales = et_idSales.getText().toString();
        passSales = et_passSales.getText().toString();


        if (idSales.isEmpty()){
            et_idSales.requestFocus();
            et_idSales.setError("Username Harus Diisi!");
            return;
        } else if (passSales.isEmpty()) {
            et_passSales.requestFocus();
            et_passSales.setError("Password Harus Diisi!");
            return;
        } else {
            prosesLoginSales();
        }


    }

    private void prosesLoginSales() {
        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().loginSales(idSales, passSales);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();


                if (response.isSuccessful()){

                    if (loginResponse.getStatus(true)){

                        sharedPrefManager.saveUser(loginResponse.getLogin());
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(MainActivity.this, "ID Sales atau Password Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPrefManager.isLoggedIn()){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}