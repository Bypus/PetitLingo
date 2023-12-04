package com.example.petitlingo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.petitlingo.animauxLvls.Lvl1AnimalFragment;
import com.example.petitlingo.couleurs.Lv1Couleur;
import com.example.petitlingo.animauxLvls.Lvl2AnimalFragment;
import com.example.petitlingo.animauxLvls.Lvl3AnimalFragment;

public class TypesFragment extends Fragment {

    protected String categorie;

    public TypesFragment() {
        // Required empty public constructor
    }

    public static TypesFragment newInstance(String categorie) {
        TypesFragment fragment = new TypesFragment();
        Bundle args = new Bundle();
        args.putString("categorie", categorie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TypesFragment", "onCreateView called");
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categorie = getArguments().getString("categorie");
        }
        return inflater.inflate(R.layout.fragment_types, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment fragment;

        // Identificadores de los botones
        int[] buttonIds = {R.id.btnLvl1, R.id.btnLvl2, R.id.btnLvl3 /* Agrega más botones según sea necesario */};

        for (int i = 0; i < buttonIds.length; i++) {
            Button button = view.findViewById(buttonIds[i]);
            final int nivel = i + 1; // Nivel es 1-indexado

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Muestra la vista del nivel seleccionado
                    switch (categorie) {
                        case "Animal":
                            Fragment fragment;
                            switch (nivel) {
                                case 1:
                                    fragment = new Lvl2AnimalFragment();
                                    break;
                                case 2:
                                    fragment = new Lvl1AnimalFragment();
                                    break;
                                case 3:
                                    fragment = new Lvl3AnimalFragment();
                                    break;
                                // Agrega más casos según sea necesario
                                default:
                                    fragment = new Nvl1Fragment();
                                    break;
                            }
                            // Agrega más casos según sea necesario
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentContainerView, fragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        case "Couleur":
                            Lv1Couleur couleur1fragment = new Lv1Couleur();
                            fragment = couleur1fragment.newInstance(); // Appelez ensuite newInstance() sur cette instance
                            break;
            //            case "Vetement":
            //                Vetement vetement1fragment = new Vetement();
            //                fragment = vetement1fragment;
            //                break;
                        default:
                            fragment = new Nvl1Fragment();
                            break;

                    }
                }
            });
        }
        Button btnNvl1 = view.findViewById(R.id.btnLvl1);
        Fragment finalFragment = fragment;
        btnNvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra la vista de temas
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, finalFragment)
                        .addToBackStack(null)  // Opcional: agrega la transacción al back stack
                        .commit();
            }
        });

        Button btnNvl2 = view.findViewById(R.id.btnLvl2);
        btnNvl2.setEnabled(false); // Rend le bouton non cliquable
        btnNvl2.setAlpha(0.5f); // Change l'opacité pour griser visuellement le bouton

        Button btnNvl3 = view.findViewById(R.id.btnLvl3);
        btnNvl3.setEnabled(false);
        btnNvl3.setAlpha(0.5f);

        Button btnNvl4 = view.findViewById(R.id.btnLvl4);
        btnNvl4.setEnabled(false);
        btnNvl4.setAlpha(0.5f);
    }
}
