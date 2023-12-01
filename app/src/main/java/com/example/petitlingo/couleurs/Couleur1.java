package com.example.petitlingo.couleurs;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petitlingo.R;
import com.example.petitlingo.WelcomeFragment;
import com.example.petitlingo.data.Couleurs;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class Couleur1 extends Fragment {
    private ImageView color1View;
    private ImageView color2View;
    private ImageView color3View;
    private ImageView color4View;
    private static Couleurs couleurs;

    private String randomColor1;
    private String randomColor2;
    private String randomColor3;
    private String trueColor;

    private Integer Case;

    private static Map<String, Map<String, String>> colorsMap;


    public Couleur1 newInstance() {
        Couleur1 couleur1 = new Couleur1();
        couleurs = new Couleurs();
        colorsMap = couleurs.tabColors();
        return couleur1;
    }
    public Couleur1() {
    }

    public static String getColorCode(String whichColor){
        return Couleurs.getData(colorsMap, whichColor, "code");
    }
    public static String getColorText(String whichColor){
        return Couleurs.getData(colorsMap, whichColor, "text");
    }

    public void instanciateView() {
        colorsMap = couleurs.tabColors();
        Log.d("ColorPicker", "Colors Map: " + colorsMap.toString());
        this.randomColor1 = ColorPicker.pickRandomColor(colorsMap);
        Log.d("ColorPicker", "Random Color 1: " + randomColor1);
        this.randomColor2 = ColorPicker.pickRandomColor(colorsMap);
        while (Objects.equals(this.randomColor1, this.randomColor2)){
            this.randomColor2 = ColorPicker.pickRandomColor(colorsMap);
        }
        this.randomColor3 = ColorPicker.pickRandomColor(colorsMap);
        while (Objects.equals(this.randomColor3, this.randomColor1) || Objects.equals(this.randomColor3, this.randomColor2)){
            this.randomColor3 = ColorPicker.pickRandomColor(colorsMap);
        }
        this.trueColor = ColorPicker.pickRandomColor(colorsMap);
        while (Objects.equals(this.trueColor, this.randomColor1) || Objects.equals(this.trueColor, this.randomColor2) || Objects.equals(this.trueColor, this.randomColor3)){
            this.trueColor = ColorPicker.pickRandomColor(colorsMap);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_couleur1, container, false);
        instanciateView();
        generateGame(rootView);
        return rootView;    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instanciateView();
        generateGame(this.getView());

    }

    private void generateGame(View rootView) {
        // Initialisez les TextView
        TextView viesTextView = rootView.findViewById(R.id.colorLives);
        TextView pointsTextView = rootView.findViewById(R.id.colorPoints);


        // Initialisez les ImageView
        color1View = rootView.findViewById(R.id.color1);
        color2View = rootView.findViewById(R.id.color2);
        color3View = rootView.findViewById(R.id.color3);
        color4View = rootView.findViewById(R.id.color4);

        // Utiliser les méthodes pour obtenir le code de chaque couleur
        // Génération d'un nombre aléatoire entre 1 et 4 pour décider de la bonne couleur
        Random random = new Random();
        Integer goodAnswer = random.nextInt(4) + 1;

        // Créer un tableau pour stocker les couleurs dans l'ordre
        String[] colorCodes = new String[4];
        colorCodes[0] = this.randomColor1;
        colorCodes[1] = this.randomColor2;
        colorCodes[2] = this.randomColor3;
        colorCodes[3] = this.trueColor;

        // Utiliser la logique pour décider de l'ordre des couleurs
        int[] order;
        switch (goodAnswer) {
            case 2:
                order = new int[]{0, 3, 1, 2};
                break;
            case 3:
                order = new int[]{0, 1, 3, 2};
                break;
            case 4:
                order = new int[]{3, 0, 1, 2};
                break;
            default:
                order = new int[]{0, 1, 2, 3};
                break;
        }

        // Convertir les codes et appliquer les couleurs aux ImageView
        for (int i = 0; i < colorCodes.length; i++) {
            int orderIndex = order[i];
            int colorViewId = getResources().getIdentifier("color" + (i + 1), "id", getActivity().getPackageName());
            ImageView colorView = rootView.findViewById(colorViewId);
            Log.d("log : " ,colorCodes[orderIndex]);
            colorView.setBackgroundColor(Color.parseColor(colorCodes[orderIndex]));
        }
    }


}