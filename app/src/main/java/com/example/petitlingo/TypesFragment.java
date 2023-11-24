package com.example.petitlingo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TypesFragment extends Fragment {

    public TypesFragment(String categorie) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TypesFragment", "onCreateView called");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_types, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnNvl1 = view.findViewById(R.id.btnNvl1);
        btnNvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra la vista de temas
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new Nvl1Fragment())
                        .addToBackStack(null)  // Opcional: agrega la transacción al back stack
                        .commit();
            }
        });
    }
}
