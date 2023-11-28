package com.example.petitlingo.couleurs;

import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.petitlingo.R;
import com.example.petitlingo.WelcomeFragment;
import com.example.petitlingo.data.Couleurs;

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
    private static Map<String, Map<String, String>> colorsMap;

    private String randomColor1;
    private String randomColor2;
    private String randomColor3;
    private String trueColor;


    public Couleur1 newInstance() {
        Couleur1 fragment = new Couleur1();

        this.randomColor1 = ColorPicker.pickRandomColor(colorsMap);
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

        return fragment;
    }
    public Couleur1() {
    }

    public static String getColorCode(String whichColor){
        return Couleurs.getData(colorsMap, whichColor, "code");
    }
    public static String getColorText(String whichColor){
        return Couleurs.getData(colorsMap, whichColor, "text");
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_couleur1, container, false);

        // Initialisez vos ImageView
//        color1View = rootView.findViewById(R.id.color1);
//        color2View = rootView.findViewById(R.id.color2);
//        color3View = rootView.findViewById(R.id.color3);
//        color4View = rootView.findViewById(R.id.color4);

        // Utiliser les méthodes pour obtenir le code de chaque couleur
        // Génération d'un nombre aléatoire entre 1 et 4 pour décider de la bonne couleur
        Random random = new Random();
        Integer goodAnswer = random.nextInt(4) + 1;

        // Créer un tableau pour stocker les couleurs dans l'ordre
        String[] colorCodes = new String[4];
        colorCodes[0] = getColorCode(this.randomColor1);
        colorCodes[1] = getColorCode(this.randomColor2);
        colorCodes[2] = getColorCode(this.randomColor3);
        colorCodes[3] = getColorCode(this.trueColor);

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
            int colorViewId = getResources().getIdentifier("color" + (i + 1) + "View", "id", getActivity().getPackageName());
            ImageView colorView = rootView.findViewById(colorViewId);
            colorView.setBackgroundColor(Color.parseColor(colorCodes[orderIndex]));
        }

        return rootView;
    }

}