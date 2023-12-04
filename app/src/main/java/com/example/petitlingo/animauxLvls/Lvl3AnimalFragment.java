package com.example.petitlingo.animauxLvls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.example.petitlingo.R;
import com.example.petitlingo.TypesFragment;
import com.example.petitlingo.WelcomeFragment;

public class Lvl3AnimalFragment extends Fragment {

    private EditText etxtElephant, etxtLion, etxtCow, etxtGiraffe;
    private Button btnValid, btnNextLvl3;
    private ImageView imageElephant, imageLion, imageCow, imageGiraffe;

    public Lvl3AnimalFragment() {
        // Constructeur public vide requis par les fragments
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflater la mise en page pour ce fragment
        View view = inflater.inflate(R.layout.fragment_lvl3_animal, container, false);

        // Initialiser les composants de l'interface utilisateur
        etxtElephant = view.findViewById(R.id.etxtElephant);
        etxtLion = view.findViewById(R.id.etxtLion);
        etxtCow = view.findViewById(R.id.etxtCow);
        etxtGiraffe = view.findViewById(R.id.etxtGiraffe);

        imageElephant = view.findViewById(R.id.imageElephant);
        imageLion = view.findViewById(R.id.imageLion);
        imageCow = view.findViewById(R.id.imageCow);
        imageGiraffe = view.findViewById(R.id.imageGiraffe);

        btnValid = view.findViewById(R.id.btnValid);
        btnNextLvl3 = view.findViewById(R.id.btnNextLvl3);

        // Ajouter un auditeur au bouton de validation
        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswers();
            }
        });

        // Désactiver le bouton "Next" initialement
        btnNextLvl3.setEnabled(false);
        // Ajouter un auditeur au bouton "Next"
        btnNextLvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remplacer le fragment actuel par le fragment d'accueil
                WelcomeFragment welcomeFragment = new WelcomeFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, welcomeFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void validateAnswers() {
        // Valider les réponses et mettre à jour l'interface utilisateur
        boolean isElephantCorrect = etxtElephant.getText().toString().equalsIgnoreCase("elephant");
        boolean isLionCorrect = etxtLion.getText().toString().equalsIgnoreCase("lion");
        boolean isCowCorrect = etxtCow.getText().toString().equalsIgnoreCase("cow");
        boolean isGiraffeCorrect = etxtGiraffe.getText().toString().equalsIgnoreCase("giraffe");

        // Changer la couleur du texte en fonction de la réponse correcte ou incorrecte
        setTextColor(etxtElephant, isElephantCorrect);
        setTextColor(etxtLion, isLionCorrect);
        setTextColor(etxtCow, isCowCorrect);
        setTextColor(etxtGiraffe, isGiraffeCorrect);

        // Activer le bouton "Next" si toutes les réponses sont correctes
        btnNextLvl3.setEnabled(isElephantCorrect && isLionCorrect && isCowCorrect && isGiraffeCorrect);
    }

    private void setTextColor(EditText editText, boolean isCorrect) {
        // Changer la couleur du texte en fonction de la réponse correcte ou incorrecte
        if (isCorrect) {
            editText.setTextColor(getResources().getColor(R.color.correctColor)); // Changez par votre couleur correcte
        } else {
            editText.setTextColor(getResources().getColor(R.color.incorrectColor)); // Changez par votre couleur incorrecte
        }
    }
}
