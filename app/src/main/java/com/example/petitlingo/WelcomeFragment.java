package com.example.petitlingo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.petitlingo.databinding.FragmentWelcomeBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WelcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.CatCouleurs.setEnabled(true);
        binding.CatCouleurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TypesFragment typesFragment = TypesFragment.newInstance("Couleur");

                fragmentTransaction.replace(R.id.fragmentContainerView, typesFragment);
                // Ajouter à la pile de retour en arrière si nécessaire
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.CatAnimaux.setEnabled(true);
        binding.CatAnimaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TypesFragment typesFragment = TypesFragment.newInstance("Animal");

                fragmentTransaction.replace(R.id.fragmentContainerView, typesFragment);
                // Ajouter à la pile de retour en arrière si nécessaire
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        binding.CatVetements.setEnabled(true);
        binding.CatVetements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TypesFragment typesFragment = new TypesFragment();

                fragmentTransaction.replace(R.id.fragmentContainerView, typesFragment);
                // Ajouter à la pile de retour en arrière si nécessaire
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        binding.CatVetements.setEnabled(false); // Rend le bouton non cliquable
        binding.CatVetements.setAlpha(0.5f); // Change l'opacité pour griser visuellement le bouton

    }
}