package com.example.petitlingo.animauxLvls;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.petitlingo.R;

public class Lvl1AnimalFragment extends Fragment {
    private ImageView animalImageElephant, animalImageLion, animalImageCow, animalImageGiraffe;
    private FrameLayout dropAreaElephant, dropAreaLion, dropAreaCow, dropAreaGiraffe;
    private boolean elephantInPlace, lionInPlace, cowInPlace, giraffeInPlace = false;

    public Lvl1AnimalFragment() {
        // Constructeur public vide requis par les fragments
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflater la mise en page pour ce fragment
        View view = inflater.inflate(R.layout.fragment_lvl1_animal, container, false);

        // Initialisation des vues et des zones de dépôt
        animalImageElephant = view.findViewById(R.id.imageAnimalElephant);
        dropAreaElephant = view.findViewById(R.id.dropAreaElephant);
        dropAreaElephant.setOnDragListener(new DropListener());
        animalImageElephant.setOnTouchListener(new TouchListener());

        animalImageLion = view.findViewById(R.id.imageAnimalLion);
        dropAreaLion = view.findViewById(R.id.dropAreaLion);
        dropAreaLion.setOnDragListener(new DropListener());
        animalImageLion.setOnTouchListener(new TouchListener());

        animalImageCow = view.findViewById(R.id.imageAnimalCow);
        dropAreaCow = view.findViewById(R.id.dropAreaCow);
        dropAreaCow.setOnDragListener(new DropListener());
        animalImageCow.setOnTouchListener(new TouchListener());

        animalImageGiraffe = view.findViewById(R.id.imageAnimalGiraffe);
        dropAreaGiraffe = view.findViewById(R.id.dropAreaGiraffe);
        dropAreaGiraffe.setOnDragListener(new DropListener());
        animalImageGiraffe.setOnTouchListener(new TouchListener());

        return view;
    }

    private void checkAllAnimalsInPlace() {
        // Vérifie si tous les animaux sont correctement placés
        if (elephantInPlace && lionInPlace && cowInPlace && giraffeInPlace) {
            // Active le bouton "Suivant" lorsque tous les animaux sont en place
            enableNextButton();
        }
    }

    private void enableNextButton() {
        // Active le bouton "Suivant" et définis son écouteur
        Button btnNext = getView().findViewById(R.id.btnNext);
        btnNext.setEnabled(true);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remplace le fragment actuel par le nouveau fragment (par exemple, Lvl3AnimalFragment)
                Lvl3AnimalFragment lvl3Fragment = new Lvl3AnimalFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, lvl3Fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private class TouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // Gestionnaire de toucher pour démarrer l'opération de glisser-déposer
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(data, shadowBuilder, v, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    private class DropListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            // Gestionnaire de glisser-déposer pour les zones de dépôt
            int action = dragEvent.getAction();
            switch (action) {
                case DragEvent.ACTION_DROP:
                    int dropAreaId = view.getId();
                    ImageView draggedImage = (ImageView) dragEvent.getLocalState();

                    // Vérifie si l'animal est placé correctement dans la zone de dépôt spécifique
                    if (dropAreaId == R.id.dropAreaElephant && draggedImage.getId() == R.id.imageAnimalElephant) {
                        elephantInPlace = true;
                        showToast("Tu as placé l'éléphant correctement !");
                    } else if (dropAreaId == R.id.dropAreaLion && draggedImage.getId() == R.id.imageAnimalLion) {
                        lionInPlace = true;
                        showToast("Tu as placé le lion correctement !");
                    } else if (dropAreaId == R.id.dropAreaCow && draggedImage.getId() == R.id.imageAnimalCow) {
                        cowInPlace = true;
                        showToast("Tu as placé la vache correctement !");
                    } else if (dropAreaId == R.id.dropAreaGiraffe && draggedImage.getId() == R.id.imageAnimalGiraffe) {
                        giraffeInPlace = true;
                        showToast("Tu as placé la girafe correctement !");
                    } else {
                        showToast("Tu n'as pas placé l'animal correctement !");
                    }

                    // Vérifie si tous les animaux sont en place après chaque opération de glisser-déposer
                    checkAllAnimalsInPlace();
                    break;
            }
            return true;
        }
    }

    private void showToast(String message) {
        // Affiche un message Toast
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
