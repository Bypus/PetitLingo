package com.example.petitlingo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Nvl1Fragment extends Fragment {
    public Nvl1Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Nvl1Fragment", "onCreateView called");
        return inflater.inflate(R.layout.fragment_nvl1, container, false);
    }
}