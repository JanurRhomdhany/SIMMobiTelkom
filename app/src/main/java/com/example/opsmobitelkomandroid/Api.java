package com.example.opsmobitelkomandroid;


import com.example.opsmobitelkomandroid.ModelResponse.FetchAgencyResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchJadwalResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchLokasiResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchSalesResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPeminjamanMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPengembalianMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.KodekembaliResponse;
import com.example.opsmobitelkomandroid.ModelResponse.KodepinjamResponse;
import com.example.opsmobitelkomandroid.ModelResponse.LoginResponse;
import com.example.opsmobitelkomandroid.ModelResponse.PeminjamanResponse;
import com.example.opsmobitelkomandroid.ModelResponse.PengembalianResponse;
import com.example.opsmobitelkomandroid.ModelResponse.RegisterResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {


    @FormUrlEncoded
    @POST("LoginSales")
    Call<LoginResponse> loginSales(@Field("id_sales") String idSales,
                                   @Field("password") String passSales);

    @FormUrlEncoded
    @POST("RegisterSales")
    Call<RegisterResponse> registerSales(@Field("id_sales") String idSales,
                                         @Field("password") String passSales);


    @GET("Jadwal/dataJadwalTanggal")
    Call<FetchJadwalResponse> fetchJadwalTanggal(@Query("tanggal") String tanggal);

    @GET("Jadwal")
    Call<FetchJadwalResponse> fetchJadwal(@Query("search") String search);

    @GET("Mobil")
    Call<FetchMobilResponse> fetchMobil();

    @GET("Agency")
    Call<FetchAgencyResponse> fetchAgency();

    @GET("Lokasi")
    Call<FetchLokasiResponse> fetchLokasi();

    @GET("Sales")
    Call<FetchSalesResponse> fetchSales();

    @GET("Peminjaman/dataPeminjamanIdSales")
    Call<FetchStatusPeminjamanMobilResponse> fetchPeminjamanIdSales(@Query("idSales") String idSales);

    @GET("Peminjaman")
    Call<FetchStatusPeminjamanMobilResponse> fetchPeminjaman(@Query("idSales") String idSales, @Query("tanggal") String tanggal);

    @GET("Peminjaman/mobilPeminjaman")
    Call<FetchStatusPeminjamanMobilResponse> fetchMobilPeminjaman(@Query("idMobil") String idMobil, @Query("tanggal") String tanggal);

    @GET("Pengembalian")
    Call<FetchStatusPengembalianMobilResponse> fetchPengembalian(@Query("idSales") String idSales, @Query("tanggal") String tanggal);

    @FormUrlEncoded
    @POST("Peminjaman")
    Call<PeminjamanResponse> InputDataPeminjaman(
            @Field("id_peminjaman") String idPeminjaman,
            @Field("tanggal") String tanggalPeminjaman,
            @Field("id_sales") String idSalesPeminjaman,
            @Field("id_mobil") String idMobilPeminjaman,
            @Field("id_lokasi") String idLokasiPeminjaman,
            @Field("jam_peminjaman") String jamPeminjaman,
            @Field("status_peminjaman") String statusPeminjaman,
            @Field("ket") String ketPeminjaman
            );

    @GET("Kodepinjam")
    Call<KodepinjamResponse> getLatestData();

    @GET("Kodekembali")
    Call<KodekembaliResponse> getLatestDataPengembalian();

    @Multipart
    @POST("Pengembalian")
    Call<PengembalianResponse> InputDataPengembalian(@Part List<MultipartBody.Part> bukti_kondisi_mobil,
                                                     @Part("id_pengembalian")RequestBody id_pengembalian,
                                                     @Part("tanggal")RequestBody tanggal,
                                                     @Part("id_sales")RequestBody id_sales,
                                                     @Part("id_mobil")RequestBody id_mobil,
                                                     @Part("id_lokasi")RequestBody id_lokasi,
                                                     @Part("ket_kegiatan")RequestBody ket_kegiatan,
                                                     @Part("jam_pengembalian")RequestBody jam_pengembalian,
                                                     @Part("status_pengembalian")RequestBody status_pengembalian,
                                                     @Part("ket_status")RequestBody ket_status);
}
