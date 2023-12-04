package com.example.petitlingo.animauxLvls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.petitlingo.R;

public class Lvl2AnimalFragment extends Fragment implements View.OnClickListener {

    private Button btnLion, btnCow, btnGiraffe, btnElephant, btnNextLvl2;
    private MediaPlayer mediaPlayerCow, mediaPlayerLion, mediaPlayerGiraffe, mediaPlayerElephant;
    private int countButtonsPressed = 0;

    public Lvl2AnimalFragment() {
        // Constructeur public vide requis par les fragments
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialiser les objets MediaPlayer avec les fichiers audio respectifs
        mediaPlayerCow = MediaPlayer.create(requireContext(), R.raw.cow);
        mediaPlayerLion = MediaPlayer.create(requireContext(), R.raw.lion);
        mediaPlayerGiraffe = MediaPlayer.create(requireContext(), R.raw.giraffe);
        mediaPlayerElephant = MediaPlayer.create(requireContext(), R.raw.elephant);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflater la mise en page pour ce fragment
        View view = inflater.inflate(R.layout.fragment_lvl2_animal, container, false);

        // Initialiser les boutons et définir les écouteurs de clic
        btnCow = view.findViewById(R.id.btnCow);
        btnLion = view.findViewById(R.id.btnLion);
        btnGiraffe = view.findViewById(R.id.btnGiraffe);
        btnElephant = view.findViewById(R.id.btnElephant);
        btnNextLvl2 = view.findViewById(R.id.btnNextLvl2);

        btnCow.setOnClickListener(this);
        btnLion.setOnClickListener(this);
        btnGiraffe.setOnClickListener(this);
        btnElephant.setOnClickListener(this);
        btnNextLvl2.setOnClickListener(this);

        return view;
    }

    private void playSound(MediaPlayer mediaPlayer) {
        // Jouer le son correspondant à l'animal
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    private void checkButtonsPressed() {
        // Vérifier combien de boutons ont été pressés
        countButtonsPressed++;
        if (countButtonsPressed == 4) {
            // Activer le bouton "Suivant" une fois que tous les boutons ont été pressés
            btnNextLvl2.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        // Gestionnaire de clics pour les boutons
        if (view.getId() == R.id.btnCow) {
            playSound(mediaPlayerCow);
            checkButtonsPressed();
        } else if (view.getId() == R.id.btnLion) {
            playSound(mediaPlayerLion);
            checkButtonsPressed();
        } else if (view.getId() == R.id.btnGiraffe) {
            playSound(mediaPlayerGiraffe);
            checkButtonsPressed();
        } else if (view.getId() == R.id.btnElephant) {
            playSound(mediaPlayerElephant);
            checkButtonsPressed();
        } else if (view.getId() == R.id.btnNextLvl2) {
            // Remplacer le fragment actuel par le nouveau fragment
            Lvl1AnimalFragment lvl1Fragment = new Lvl1AnimalFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, lvl1Fragment)
                    .addToBackStack(null)  // Cela ajoute le fragment à la pile de retour, au cas où vous souhaitez effectuer un retour en arrière
                    .commit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Libérer les ressources du MediaPlayer
        mediaPlayerCow.release();
        mediaPlayerLion.release();
        mediaPlayerGiraffe.release();
        mediaPlayerElephant.release();
    }
}
