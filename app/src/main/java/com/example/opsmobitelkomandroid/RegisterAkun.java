package com.example.opsmobitelkomandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opsmobitelkomandroid.Activities.HomeActivity;
import com.example.opsmobitelkomandroid.Activities.MainActivity;
import com.example.opsmobitelkomandroid.ModelResponse.Agency;
import com.example.opsmobitelkomandroid.ModelResponse.FetchAgencyResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchSalesResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Register;
import com.example.opsmobitelkomandroid.ModelResponse.RegisterResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Sales;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAkun extends AppCompatActivity {


    private List<Sales> salesList;
    private EditText et_idAgency, et_namaSales, et_passSales;
    private AutoCompleteTextView et_idSales;
    private String idSales, namaSales, passSales, idAgency;
    private TextView loginAkun;

    private Button registerAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_akun);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        et_idAgency = findViewById(R.id.et_registerIdAgency);
        et_idSales = findViewById(R.id.dropdownMenu_registerIdSales);
        et_namaSales = findViewById(R.id.et_registerNamaSales);
        et_passSales = findViewById(R.id.et_registerPassword);

        et_idSales.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        registerAkun = findViewById(R.id.btn_registerAkun);

        registerAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salesRegister();
            }
        });

        dropDownIDSales();

        klikLogin();


    }



    private void salesRegister() {

        idSales = et_idSales.getText().toString();
        namaSales = et_namaSales.getText().toString();
        idAgency = et_idAgency.getText().toString();
        passSales = et_passSales.getText().toString();

        if (idSales.isEmpty()){
            et_idSales.requestFocus();
            et_idSales.setError("ID Sales Harus Diisi");
            return;
        } else if (namaSales.isEmpty()) {
                et_namaSales.requestFocus();
                et_namaSales.setError("Nama Sales Harus Diisi");
                return;
        } else if (idAgency.isEmpty()) {
                et_idAgency.requestFocus();
                et_idAgency.setError("Agency Harus Dipilih");
                return;
        } else if (passSales.isEmpty()) {
            et_passSales.requestFocus();
            et_passSales.setError("Password Harus Diisi");
            return;
        } else {


            prosesRegisterAkun();
        }


    }

    private void prosesRegisterAkun() {

        Call<RegisterResponse> registerResponseCall = RetrofitClient.getInstance().getApi().registerSales(idSales, passSales);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()){

                    Register register = response.body().getRegister();


                    Intent intent = new Intent(RegisterAkun.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterAkun.this, "Berhasil Daftar Akun", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(RegisterAkun.this, "ID sudah terdaftar, silahkan Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterAkun.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void klikLogin() {
        loginAkun = findViewById(R.id.tv_loginAkun);
        String text = "Sudah punya akun? Silahkan Login";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(RegisterAkun.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan, 27, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginAkun.setText(ss);
        loginAkun.setMovementMethod(LinkMovementMethod.getInstance());
    }


    private void dropDownIDSales() {
        Call<FetchSalesResponse> fetchSalesResponseCall = RetrofitClient.getInstance().getApi().fetchSales();

        fetchSalesResponseCall.enqueue(new Callback<FetchSalesResponse>() {
            @Override
            public void onResponse(Call<FetchSalesResponse> call, Response<FetchSalesResponse> response) {
                if (response.isSuccessful()){

                    salesList = response.body().getSalesList();
                    List<String> listSales = new ArrayList<>();
                    for (Sales sl : salesList){
                        listSales.add(sl.getId_sales());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(RegisterAkun.this, android.R.layout.simple_dropdown_item_1line, listSales);
                    et_idSales.setAdapter(adapter);

                }else {

                }

                et_idSales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String sales = (String) parent.getItemAtPosition(position);
                        et_idSales.setText(sales);

                        Sales selectedSales = null;
                        for (Sales sl : salesList){
                            if (sl.getId_sales().equals(sales)){
                                selectedSales = sl;
                                break;
                            }
                        }

                        if (selectedSales != null){
                            et_idSales.setText(String.valueOf(selectedSales.getId_sales()));
                            et_namaSales.setText(String.valueOf(selectedSales.getNama_sales()));
                            et_idAgency.setText(String.valueOf(selectedSales.getId_agency()));
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<FetchSalesResponse> call, Throwable t) {

            }
        });
    }
}