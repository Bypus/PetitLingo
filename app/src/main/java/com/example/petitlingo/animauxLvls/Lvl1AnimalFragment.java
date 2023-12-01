package com.example.petitlingo.animauxLvls;

import android.content.ClipData;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petitlingo.R;


public class Lvl1AnimalFragment extends Fragment {
    private ImageView animalImage;
    private TextView animalName;
    private FrameLayout dropArea;

    public Lvl1AnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lvl1_animal,container,false);

        animalImage = view.findViewById(R.id.imageAnimal);
        animalName = view.findViewById(R.id.nameAnimal);
        dropArea = view.findViewById(R.id.dropArea);

        //Inicialize fragment with first animal
        showNextAnimal();

        //add listener to drag and drop in dropArea
        dropArea.setOnDragListener(new DropListener());

        //add drop function
        animalImage.setOnTouchListener(new TouchListener());
        return view;
    }
    private void showNextAnimal(){
        //to get the next animal
        String name = getNextAnimalName();
        int imageId = getNextAnimalId();

        //show animal in the interface
        animalName.setText(name);
        animalImage.setImageResource(imageId);
    }

    private String getNextAnimalName() {
        // Lógica para obtener el nombre del siguiente animal
        // (puedes cargar datos desde una lista o recursos)
        return "León"; // Ejemplo, debes ajustar esto según tus datos reales
    }
    private int getNextAnimalId() {
        // Lógica para obtener el ID de recursos de la imagen del siguiente animal
        // (puedes cargar datos desde una lista o recursos)
        return R.drawable.baby_elephant; // Ejemplo, debes ajustar esto según tus datos reales
    }
    // Clase interna para gestionar el arrastrar y soltar

    // Clase interna para gestionar el arrastrar y soltar
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

    //Class intern to manage drop event
    private class DropListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();
            switch (action) {
                case DragEvent.ACTION_DROP:
                    // La imagen se soltó en el área designada
                    // Puedes agregar la lógica aquí, como mostrar un mensaje o cargar el próximo animal7
                    showToast("¡You dropped the animal!");
                    showNextAnimal();
                    break;
            }
            return true;
        }
    }
    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }



}