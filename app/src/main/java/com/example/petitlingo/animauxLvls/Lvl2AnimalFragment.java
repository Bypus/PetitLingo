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
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayerCow = MediaPlayer.create(requireContext(), R.raw.cow);
        mediaPlayerLion = MediaPlayer.create(requireContext(), R.raw.lion);
        mediaPlayerGiraffe = MediaPlayer.create(requireContext(), R.raw.giraffe);
        mediaPlayerElephant = MediaPlayer.create(requireContext(), R.raw.elephant);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lvl2_animal, container, false);

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
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    private void checkButtonsPressed() {
        countButtonsPressed++;
        if (countButtonsPressed == 4) {
            btnNextLvl2.setEnabled(true);
        }
    }


    @Override
    public void onClick(View view) {
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
            // Reemplazar el fragmento actual con el nuevo fragmento
            Lvl1AnimalFragment lvl1Fragment = new Lvl1AnimalFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, lvl1Fragment)
                    .addToBackStack(null)  // Esto agrega el fragmento al back stack, por si quieres realizar un retroceso
                    .commit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Liberar recursos de MediaPlayer
        mediaPlayerCow.release();
        mediaPlayerLion.release();
        mediaPlayerGiraffe.release();
        mediaPlayerElephant.release();
    }
}
