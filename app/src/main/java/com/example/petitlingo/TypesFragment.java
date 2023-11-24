package com.example.petitlingo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.petitlingo.couleurs.Couleur1;

public class TypesFragment extends Fragment {

    public TypesFragment() {
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

        Button btnLvl1 = view.findViewById(R.id.btnLvl1);
        Button btnLvl2 = view.findViewById(R.id.btnLvl2);
        Button btnLvl3 = view.findViewById(R.id.btnLvl3);
        Button btnLvl4 = view.findViewById(R.id.btnLvl4);

        btnLvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Couleur1 couleur1 = new Couleur1();

                fragmentTransaction.replace(R.id.fragmentContainerView, couleur1);
                fragmentTransaction.commit();
            }
        });
        btnLvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra la vista de temas
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new Nvl1Fragment())
                        .addToBackStack(null)  // Opcional: agrega la transacción al back stack
                        .commit();
            }
        });
        btnLvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra la vista de temas
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new Nvl1Fragment())
                        .addToBackStack(null)  // Opcional: agrega la transacción al back stack
                        .commit();
            }
        });
        btnLvl4.setOnClickListener(new View.OnClickListener() {
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
