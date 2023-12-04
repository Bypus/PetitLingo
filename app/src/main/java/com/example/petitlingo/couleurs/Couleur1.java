package com.example.petitlingo.couleurs;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petitlingo.R;
import com.example.petitlingo.data.Couleurs;
import com.example.petitlingo.winorlose.WinOrLoseFragment;

import java.util.Map;
import java.util.Random;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class Couleur1 extends Fragment {
    private static Couleurs couleurs;
    private String randomColor1;
    private String randomColor2;
    private String randomColor3;
    private String trueColor;
    private String trueColorText;
    private TextView pointsTextView;
    private Integer pointsText;
    private TextView livesTextView;
    private Integer livesText;

    // Constructeur vide
    public Couleur1() {
    }

    public Couleur1 newInstance() {
        Couleur1 couleur1 = new Couleur1();
        couleurs = new Couleurs();
        return couleur1;
    }

    public void instanciateView() {
        Map<String, Map<String, String>> colorsMap = couleurs.tabColors();

        // Génération des couleurs de manière aléatoire + vérification qu'il n'y ai pas deux fois la même
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
        this.trueColorText = Couleurs.getTextForColorCode(colorsMap, this.trueColor);
        while (Objects.equals(this.trueColor, this.randomColor1) || Objects.equals(this.trueColor, this.randomColor2) || Objects.equals(this.trueColor, this.randomColor3)){
            this.trueColor = ColorPicker.pickRandomColor(colorsMap);
            this.trueColorText = Couleurs.getTextForColorCode(colorsMap, this.trueColor);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_couleur1, container, false);
        instanciateView();
        generateGame(rootView);

        // Initialisation du systeme de vie et de points
        this.pointsTextView = rootView.findViewById(R.id.colorPoints);
        this.livesTextView = rootView.findViewById(R.id.colorLives);
        this.pointsText = 0;
        this.livesText = 3;

        return rootView;
    }

    private int getTrueColorButtonId(View rootView) {

        int[] buttonIds = {R.id.color1, R.id.color2, R.id.color3, R.id.color4}; // Liste des IDs de boutons

        for (int buttonId : buttonIds) {
            View button = rootView.findViewById(buttonId); // Récupérer la vue du bouton
            if (button != null && button.getBackground() instanceof ColorDrawable) {
                int buttonColor = ((ColorDrawable) button.getBackground()).getColor();
                String buttonHexColor = String.format("#%06X", (0xFFFFFF & buttonColor)); // Conversion en hexadécimal
                if (this.trueColor.equalsIgnoreCase(buttonHexColor)) {
                    return buttonId; // Retourne l'ID du bouton correspondant à la couleur correcte
                }
            }
        }
        return -1; // Si aucun bouton correspondant n'est trouvé
    }


    @SuppressLint("SetTextI18n")
    private void handleImageClick(boolean isVictory) {
        Bundle args = new Bundle();
        if (isVictory){
            pointsText += 1;
            pointsTextView.setText(pointsText.toString());
            instanciateView();
            generateGame(this.getView());
        } else {
            livesText -= 1;
            livesTextView.setText(livesText.toString());
            if (livesText == 0){
                args.putInt(WinOrLoseFragment.RESULT_KEY, WinOrLoseFragment.RESULT_DEFEAT);
                args.putInt(WinOrLoseFragment.POINTS_KEY, pointsText);
                WinOrLoseFragment winOrLoseFragment = new WinOrLoseFragment();
                winOrLoseFragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, winOrLoseFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }

        if (pointsText == 5){
            args.putInt(WinOrLoseFragment.RESULT_KEY, WinOrLoseFragment.RESULT_VICTORY);
            args.putInt(WinOrLoseFragment.POINTS_KEY, pointsText);
            args.putInt(WinOrLoseFragment.POINTS_LIFE, livesText);
            WinOrLoseFragment winOrLoseFragment = new WinOrLoseFragment();
            winOrLoseFragment.setArguments(args);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, winOrLoseFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instanciateView();
        generateGame(this.getView());

    }

    private void generateGame(View rootView) {
        // Initialisation des TextView
        TextView viesTextView = rootView.findViewById(R.id.colorLives);
        TextView pointsTextView = rootView.findViewById(R.id.colorPoints);


        // Initialisation des ImageView
        ImageView color1View = rootView.findViewById(R.id.color1);
        ImageView color2View = rootView.findViewById(R.id.color2);
        ImageView color3View = rootView.findViewById(R.id.color3);
        ImageView color4View = rootView.findViewById(R.id.color4);
        TextView trueColorTextView = rootView.findViewById(R.id.trueColorText);

        trueColorTextView.setText(trueColorText);

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
            Log.d("log : ", colorCodes[orderIndex]);
            colorView.setBackgroundColor(Color.parseColor(colorCodes[orderIndex]));
        }

        int trueButtonId = getTrueColorButtonId(rootView);
        // Dans onViewCreated ou là où tu initialises tes ImageView
        ImageView btnClr1 = rootView.findViewById(R.id.color1);
        btnClr1.setOnClickListener(v -> handleImageClick(btnClr1.getId() == trueButtonId));

        ImageView btnClr2 = rootView.findViewById(R.id.color2);
        btnClr2.setOnClickListener(v -> handleImageClick(btnClr2.getId() == trueButtonId));

        ImageView btnClr3 = rootView.findViewById(R.id.color3);
        btnClr3.setOnClickListener(v -> handleImageClick(btnClr3.getId() == trueButtonId));

        ImageView btnClr4 = rootView.findViewById(R.id.color4);
        btnClr4.setOnClickListener(v -> handleImageClick(btnClr4.getId() == trueButtonId));
    }




}