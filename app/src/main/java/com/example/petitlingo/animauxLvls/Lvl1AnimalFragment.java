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
import androidx.lifecycle.ViewModelProvider;
import com.example.petitlingo.R;

public class Lvl1AnimalFragment extends Fragment {

    private ImageView animalImageElephant, animalImageLion, animalImageCow, animalImageGiraffe;
    private FrameLayout dropAreaElephant, dropAreaLion, dropAreaCow, dropAreaGiraffe;
    private AnimalViewModel viewModel;

    public Lvl1AnimalFragment() {
        // Constructeur vide requis par Fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lvl1_animal, container, false);

        // Initialiser le ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);

        // Récupérer les références des vues (ImageView et FrameLayout) depuis le layout
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

    // Vérifie si tous les animaux sont à leur place
    private void checkAllAnimalsInPlace() {
        if (viewModel.isElephantInPlace() && viewModel.isLionInPlace()
                && viewModel.isCowInPlace() && viewModel.isGiraffeInPlace()) {
            enableNextButton(); // Active le bouton Suivant si tous les animaux sont à leur place
        }
    }

    // Active le bouton Suivant
    private void enableNextButton() {
        Button btnNext = requireView().findViewById(R.id.btnNext);
        btnNext.setEnabled(true);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remplace le fragment actuel par le nouveau fragment (par exemple, Lvl2AnimalFragment)
                Lvl3AnimalFragment lvl3Fragment = new Lvl3AnimalFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, lvl3Fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    // Écouteur tactile pour les images (début du glisser-déposer)
    private class TouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Préparer les données à transférer pendant le glisser-déposer
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                // Démarrer l'opération de glisser-déposer
                v.startDragAndDrop(data, shadowBuilder, v, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    // Écouteur de glisser-déposer pour les zones de dépôt
    private class DropListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();
            switch (action) {
                case DragEvent.ACTION_DROP:
                    // Récupérer l'ID de la zone de dépôt et de l'image glissée
                    int dropAreaId = view.getId();
                    ImageView draggedImage = (ImageView) dragEvent.getLocalState();

                    // Vérifier si l'animal est placé correctement dans la zone correspondante
                    if (dropAreaId == R.id.dropAreaElephant && draggedImage.getId() == R.id.imageAnimalElephant) {
                        viewModel.setElephantInPlace(true);
                        showToast("Tu as placé l'éléphant correctement !");
                    } else if (dropAreaId == R.id.dropAreaLion && draggedImage.getId() == R.id.imageAnimalLion) {
                        viewModel.setLionInPlace(true);
                        showToast("Tu as placé le lion correctement !");
                    } else if (dropAreaId == R.id.dropAreaCow && draggedImage.getId() == R.id.imageAnimalCow) {
                        viewModel.setCowInPlace(true);
                        showToast("Tu as placé la vache correctement !");
                    } else if (dropAreaId == R.id.dropAreaGiraffe && draggedImage.getId() == R.id.imageAnimalGiraffe) {
                        viewModel.setGiraffeInPlace(true);
                        showToast("Tu as placé la girafe correctement !");
                    } else {
                        showToast("Tu n'as pas placé l'animal correctement !");
                    }

                    // Vérifier si tous les animaux sont à leur place
                    checkAllAnimalsInPlace();
                    break;
            }
            return true;
        }
    }

    // Afficher un message toast
    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
