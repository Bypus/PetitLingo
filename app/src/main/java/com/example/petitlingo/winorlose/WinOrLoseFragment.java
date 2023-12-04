package com.example.petitlingo.winorlose;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petitlingo.R;
import com.example.petitlingo.WelcomeFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WinOrLoseFragment} factory method to
 * create an instance of this fragment.
 */
public class WinOrLoseFragment extends Fragment {

    public static final String RESULT_KEY = "result";
    public static final int RESULT_VICTORY = 1;
    public static final int RESULT_DEFEAT = 0;
    public static final String POINTS_KEY = "0";
    public static final String POINTS_LIFE = "";

    public WinOrLoseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_win_or_lose, container, false);

        TextView resultTextView = rootView.findViewById(R.id.resultTextView);

        // Récupérer les données transmises au fragment
        Bundle args = getArguments();
        if (args != null) {
            int result = args.getInt(RESULT_KEY);

            // Mettre à jour le texte en fonction du résultat
            if (result == RESULT_VICTORY) {
                resultTextView.setText("Bravo, tu as réussi à faire " + args.getInt(POINTS_KEY) + " points et il te reste " + args.getInt(POINTS_LIFE) + " vies. Continue comme ça !");
            } else if (result == RESULT_DEFEAT) {
                if (args.getInt(POINTS_KEY) == 0)
                    resultTextView.setText("Dommage, tu n'as pas réussi à faire de points et tu as perdu toutes tes vies. Retente ta chance !");
                else
                    resultTextView.setText("Bravo, tu as réussi à faire " + args.getInt(POINTS_KEY) + " points, mais tu as perdu toutes tes vies. Retente ta chance !");
            }
        }

        Button menuButton = rootView.findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retourner au fragment WelcomeFragment
                WelcomeFragment welcomeFragment = new WelcomeFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, welcomeFragment)
                        .commit();
            }
        });

        Button retryButton = rootView.findViewById(R.id.retryButton);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retourner au fragment précédent
                requireActivity().getSupportFragmentManager().popBackStack();

            }
        });

        return rootView;
    }
}