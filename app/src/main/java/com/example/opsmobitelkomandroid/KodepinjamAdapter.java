package com.example.opsmobitelkomandroid;

import android.content.Context;

import com.example.opsmobitelkomandroid.ModelResponse.Kodepinjam;
import com.example.opsmobitelkomandroid.ModelResponse.Mobil;

import java.util.List;

public class KodepinjamAdapter {

    public KodepinjamAdapter(Context context, List<Kodepinjam> kodepinjamList) {
        this.context = context;
        this.kodepinjamList = kodepinjamList;
    }

    private Context context;
    private List<Kodepinjam> kodepinjamList;




}
