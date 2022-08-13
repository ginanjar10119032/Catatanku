package com.ginanjartg.catatanku.viewpager;

//NIM     : 10119032
//NAMA    : GINANJAR TUBAGUS GUMILAR
//KELAS   : IF-1

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ginanjartg.catatanku.R;

public class ViewPagerKedua extends Fragment {


    public ViewPagerKedua() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_pager_kedua, container, false);
    }
}