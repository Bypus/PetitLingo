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
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lvl1_animal, container, false);

        animalImageElephant = view.findViewById(R.id.imageAnimalElephant);
        dropAreaElephant = view.findViewById(R.id.dropAreaElephant);
        // Agregar oyente para arrastrar y soltar en dropAreaElephant
        dropAreaElephant.setOnDragListener(new DropListener());
        // Agregar función de soltar a la imagen
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
        if (elephantInPlace && lionInPlace && cowInPlace && giraffeInPlace) {
            // Habilita el botón "Next" cuando todos los animales están en su lugar
            enableNextButton();
        }
    }

    private void enableNextButton() {
        Button btnNext = getView().findViewById(R.id.btnNext);
        btnNext.setEnabled(true);
    }



    private class TouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Inicia la operación de arrastrar
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
            int action = dragEvent.getAction();
            switch (action) {
                case DragEvent.ACTION_DROP:
                    int dropAreaId = view.getId();
                    ImageView draggedImage = (ImageView) dragEvent.getLocalState();

                    if (dropAreaId == R.id.dropAreaElephant && draggedImage.getId() == R.id.imageAnimalElephant) {
                        elephantInPlace = true;
                        showToast("¡Has colocado el elefante correctamente!");

                    } else if (dropAreaId == R.id.dropAreaLion && draggedImage.getId() == R.id.imageAnimalLion) {
                        lionInPlace = true;
                        showToast("¡Has colocado el Leon correctamente!");
                    } else if (dropAreaId == R.id.dropAreaCow && draggedImage.getId() == R.id.imageAnimalCow) {
                        cowInPlace = true;
                        showToast("¡Has colocado la vaca correctamente!");
                    } else if (dropAreaId == R.id.dropAreaGiraffe && draggedImage.getId() == R.id.imageAnimalGiraffe) {
                        giraffeInPlace = true;
                        showToast("¡Has colocado la girafa  correctamente!");
                    } else {
                        showToast("¡Has colocado el animal en el área incorrecta!");
                    }

                    checkAllAnimalsInPlace();
                    break;
            }
            return true;
        }
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
