package com.example.opsmobitelkomandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opsmobitelkomandroid.Activities.HomeActivity;
import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPeminjamanMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.KodekembaliResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Lokasi;
import com.example.opsmobitelkomandroid.ModelResponse.Mobil;
import com.example.opsmobitelkomandroid.ModelResponse.Peminjaman;
import com.example.opsmobitelkomandroid.ModelResponse.PengembalianResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Sales;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengembalianMobil extends AppCompatActivity {

    List<Peminjaman> peminjamanList;

    RelativeLayout btnPilihGambar;
    ViewPager2 viewPager;
    private Button btn_reqKembali;
    private TextView tv_pilihGambar;
    private String path;

    private EditText et_idKembali, et_jamKembali, et_ketStatusKembali, et_statusKembali, et_tanggalKembali, et_ketKegiatan;

    private EditText et_idSales, et_idMobil, et_idLokasi, et_idPinjam;

    private String idKembali, idPinjam, idSales, idMobil, idLokasi, tanggalKembali, jamKembali, ketStatusKembali, statusKembali, ketKegiatan, buktiKondisiMobil;

    SharedPrefManager sharedPrefManager;

    Uri[] imageUris;
    ArrayList<Uri> pilihGambarList;



    private static final int REQUEST_CODE_SELECT_IMAGES = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengembalian_mobil);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        pilihGambarList = new ArrayList<>();
        autoIdPengembalian();
        pilihGambar();
        jamKembaliRequest();
        prosesRequestPengembalian();

        et_ketKegiatan = findViewById(R.id.et_ketKegiatan);
        View focusDummy = findViewById(R.id.focusDummy);
        focusDummy.requestFocus();
        et_idSales = findViewById(R.id.et_idSales);
        et_idMobil = findViewById(R.id.et_idMobil);
        et_idLokasi = findViewById(R.id.et_idLokasi);
        viewPager = findViewById(R.id.vp_buktigambar);

        tv_pilihGambar = findViewById(R.id.tv_pilihGambar);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        String dataLoginIdSales = sharedPrefManager.getLogin().getIdSales();
        Calendar c = Calendar.getInstance();
        Date d = Calendar.getInstance().getTime();
        // Create a SimpleDateFormat object to format the time
        SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        //simpledateformattanggal
        SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        //get current date
        String currentDate = dateFormatTanggal.format(d);
        getDataMobilPinjam(dataLoginIdSales, currentDate);

        et_idSales.setText(sharedPrefManager.getLogin().getIdSales());

    }

    private void getDataMobilPinjam(String idSales, String tanggalPinjam) {

        Call<FetchStatusPeminjamanMobilResponse> fetchStatusPeminjamanMobilResponseCall = RetrofitClient.getInstance().getApi().fetchPeminjaman(idSales, tanggalPinjam);

        fetchStatusPeminjamanMobilResponseCall.enqueue(new Callback<FetchStatusPeminjamanMobilResponse>() {
            @Override
            public void onResponse(Call<FetchStatusPeminjamanMobilResponse> call, Response<FetchStatusPeminjamanMobilResponse> response) {
                if (response.isSuccessful()){

                    assert response.body() != null;
                    peminjamanList = response.body().getPeminjamanList();
                    String dataIdMobil = null;
                    String dataIdLokasi = null;
                    if (peminjamanList != null && !peminjamanList.isEmpty()){
                        for (Peminjaman pj : peminjamanList){
                            dataIdMobil = pj.getIdMobil();
                            dataIdLokasi = pj.getIdLokasi();
                            break;
                        }
                    }
                    et_idMobil.setText(dataIdMobil);
                    et_idLokasi.setText(dataIdLokasi);
                }
            }

            @Override
            public void onFailure(Call<FetchStatusPeminjamanMobilResponse> call, Throwable t) {

            }
        });
    }

    private void prosesRequestPengembalian() {
        btn_reqKembali = findViewById(R.id.btn_reqKembali);

        btn_reqKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idKembali = et_idKembali.getText().toString();
                //tambah id pinjam untuk dimasukkan ke dalam tabel
                idSales = et_idSales.getText().toString();
                idMobil = et_idMobil.getText().toString();
                idLokasi = et_idLokasi.getText().toString();
                statusKembali = et_statusKembali.getText().toString();
                buktiKondisiMobil = tv_pilihGambar.getText().toString();
                ketKegiatan = et_ketKegiatan.getText().toString();
                ketStatusKembali = et_ketStatusKembali.getText().toString();
                tanggalKembali = et_tanggalKembali.getText().toString();
                jamKembali = et_jamKembali.getText().toString();

                if (ketKegiatan.isEmpty()) {
                    et_ketKegiatan.setError("Ket Kegiatan Harus Diisi!");
                } else if (imageUris == null || imageUris.length == 0) {
                    // Show an error message or toast to inform the user to select images
                    Toast.makeText(PengembalianMobil.this, "Upload gambar sebagai bukti kondisi mobil", Toast.LENGTH_SHORT).show();
                } else {
                    // Both ketKegiatan is not empty and images are selected, proceed with the API call
                    createReqKembali(idKembali, tanggalKembali, idSales, idMobil, idLokasi, ketKegiatan, jamKembali, statusKembali, ketStatusKembali);
                }
            }


            private void createReqKembali(String idKembali, String tanggalKembali, String idSales, String idMobil, String idLokasi, String ketKegiatan, String jamKembali, String statusKembali, String ketStatusKembali) {

                List<MultipartBody.Part> imageParts = new ArrayList<>();
                for (Uri imageUri : imageUris) {
                    imageParts.add(prepareFiles("bukti_kondisi_mobil[]", imageUri));

                }

                RequestBody pb_idKembali = RequestBody.create(MediaType.parse("multipart/form-data"), idKembali);
                //request peminjaman belum ada
                RequestBody pb_tanggalKembali = RequestBody.create(MediaType.parse("multipart/form-data"), tanggalKembali);

                //idsales, idmobil, idlokasi tidak perlu pada pengembalian mobil, ambil langusng dari id peminjaman
                RequestBody pb_idSales = RequestBody.create(MediaType.parse("multipart/form-data"), idSales);
                RequestBody pb_idMobil = RequestBody.create(MediaType.parse("multipart/form-data"), idMobil);
                RequestBody pb_idLokasi = RequestBody.create(MediaType.parse("multipart/form-data"), idLokasi);
                RequestBody pb_ketKegiatan = RequestBody.create(MediaType.parse("multipart/form-data"), ketKegiatan);
                RequestBody pb_jamKembali = RequestBody.create(MediaType.parse("multipart/form-data"), jamKembali);
                RequestBody pb_statusKembali = RequestBody.create(MediaType.parse("multipart/form-data"), statusKembali);
                RequestBody pb_ketStatusKembali = RequestBody.create(MediaType.parse("multipart/form-data"), ketStatusKembali);


                Call<PengembalianResponse> call = RetrofitClient.getInstance().getApi().InputDataPengembalian(imageParts, pb_idKembali, pb_tanggalKembali, pb_idSales, pb_idMobil, pb_idLokasi, pb_ketKegiatan, pb_jamKembali, pb_statusKembali, pb_ketStatusKembali);

                call.enqueue(new Callback<PengembalianResponse>() {
                    @Override
                    public void onResponse(Call<PengembalianResponse> call, Response<PengembalianResponse> response) {
                        if (response.isSuccessful()){
                            Intent i = new Intent(PengembalianMobil.this, HomeActivity.class);
                            startActivity(i);
                            Toast.makeText(PengembalianMobil.this, "Request Pengembalian Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PengembalianResponse> call, Throwable t) {
                        Toast.makeText(PengembalianMobil.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private MultipartBody.Part prepareFiles(String partName, Uri imageUri) {

                try {
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    RequestBody buktiKondisi = RequestBody.create(MediaType.parse("multipart/form-data"), getBytes(inputStream));
                    return MultipartBody.Part.createFormData(partName, getFileName(imageUri), buktiKondisi);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

            private String getFileName(Uri imageUri) {
                String result = null;
                if (imageUri.getScheme().equals("content")) {
                    Cursor cursor = getContentResolver().query(imageUri, null, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                                if (nameIndex >= 0) {
                                    result = cursor.getString(nameIndex);
                                }
                            }
                        } finally {
                            cursor.close();
                        }
                    }
                }
                if (result == null) {
                    result = imageUri.getLastPathSegment();
                }
                return result;
            }

            private byte[] getBytes(InputStream inputStream) throws IOException {
                ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    byteBuffer.write(buffer, 0, len);
                }
                return byteBuffer.toByteArray();
            }




        });
    }

    private void jamKembaliRequest() {
        //waktu request
        Calendar c = Calendar.getInstance();
        Date d = Calendar.getInstance().getTime();
        // Create a SimpleDateFormat object to format the time
        SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        //simpledateformattanggal
        SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        //get current date
        String currentDate = dateFormatTanggal.format(d);

        et_tanggalKembali = findViewById(R.id.et_tanggalKembali);
        et_tanggalKembali.setText(currentDate);
        // Get the current time
        String currentTime = dateFormatJam.format(d);
        et_jamKembali = findViewById(R.id.et_jamKembali);
        et_jamKembali.setText(currentTime);

        int currentHour = dateFormatJam.getCalendar().get(Calendar.HOUR_OF_DAY);
        int currentMinute = dateFormatJam.getCalendar().get(Calendar.MINUTE);
        int currentSecond = dateFormatJam.getCalendar().get(Calendar.SECOND);

        int desiredHour = 17;
        int desiredMinute = 0;
        int desiredSecond = 0;

        et_statusKembali = findViewById(R.id.et_statusKembali);
        et_ketStatusKembali = findViewById(R.id.et_ketStatusKembali);
        et_statusKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_ketStatusKembali, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        if (currentHour < desiredHour ||
                (currentHour == desiredHour && currentMinute <= desiredMinute) ||
                (currentHour == desiredHour && currentMinute == desiredMinute && currentSecond <= desiredSecond)) {
            // If the current time is less than 09:00:00, set the text to "value"

            et_statusKembali.setText("Berhasil");
            et_ketStatusKembali.setText("Mobil Parkir");
        } else {
            // Otherwise, set the text to something else
            et_statusKembali.setText("Request");
            et_ketStatusKembali.setText("Mobil Jalan");
        }
    }

    private void autoIdPengembalian() {

        Call<KodekembaliResponse> callKodeKembali = RetrofitClient.getInstance().getApi().getLatestDataPengembalian();

        callKodeKembali.enqueue(new Callback<KodekembaliResponse>() {
            @Override
            public void onResponse(Call<KodekembaliResponse> call, Response<KodekembaliResponse> response) {

                if (response.isSuccessful()){
                    KodekembaliResponse kodekembaliResponse = response.body();

                    if (kodekembaliResponse != null){
                        String latestId = kodekembaliResponse.getLatestIdPengembalian();
                        if (latestId != null){
                            String autoId = generateAutoCode(latestId);
                            et_idKembali = findViewById(R.id.et_idPengembalian);
                            et_idKembali.setText(autoId);

                        }else {
                            et_idKembali = findViewById(R.id.et_idPengembalian);
                            et_idKembali.setText("PB001");
                        }
                    }
                }
            }

            private String generateAutoCode(String latestId) {

                int numericPart = Integer.parseInt(latestId.substring(2));
                numericPart++;

                String newCode = "PB" + String.format("%03d", numericPart);

                return newCode;
            }

            @Override
            public void onFailure(Call<KodekembaliResponse> call, Throwable t) {
                Toast.makeText(PengembalianMobil.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void pilihGambar() {
        btnPilihGambar = findViewById(R.id.pilihGambar);
        btnPilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGES);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGES && resultCode == RESULT_OK) {
            if (data.getClipData() != null) {
                // Multiple images selected
                int count = data.getClipData().getItemCount();
                imageUris = new Uri[count];
                for (int i = 0; i < count; i++) {
                    imageUris[i] = data.getClipData().getItemAt(i).getUri();
                }
            } else if (data.getData() != null) {
                // Single image selected
                imageUris = new Uri[] { data.getData() };
            }
            // Log the contents of imageUris array
            if (imageUris != null) {
                for (Uri uri : imageUris) {
                    Log.d("Selected Image Uri", uri.toString());
                }
            }

            // Create and set the adapter with the updated data
            if (imageUris != null) {
                viewPager.setAdapter(new ViewPagerImageAdapter(Arrays.asList(imageUris), this));
            }
        }
    }

}