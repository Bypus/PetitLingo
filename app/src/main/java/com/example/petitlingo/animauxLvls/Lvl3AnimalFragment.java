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
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lvl3_animal, container, false);

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

        // Agregar un listener al botón de validación
        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswers();
            }
        });

        // Deshabilitar el botón "Next" inicialmente
        btnNextLvl3.setEnabled(false);
        // Agregar un listener al botón "Next"
        btnNextLvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reemplazar el fragmento actual con el fragmento de inicio
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
        boolean isElephantCorrect = etxtElephant.getText().toString().equalsIgnoreCase("elephant");
        boolean isLionCorrect = etxtLion.getText().toString().equalsIgnoreCase("lion");
        boolean isCowCorrect = etxtCow.getText().toString().equalsIgnoreCase("cow");
        boolean isGiraffeCorrect = etxtGiraffe.getText().toString().equalsIgnoreCase("giraffe");

        // Cambiar el color del texto según si la respuesta es correcta o incorrecta
        setTextColor(etxtElephant, isElephantCorrect);
        setTextColor(etxtLion, isLionCorrect);
        setTextColor(etxtCow, isCowCorrect);
        setTextColor(etxtGiraffe, isGiraffeCorrect);

        // Habilitar el botón "Next" si todas las respuestas son correctas
        btnNextLvl3.setEnabled(isElephantCorrect && isLionCorrect && isCowCorrect && isGiraffeCorrect);
    }

    private void setTextColor(EditText editText, boolean isCorrect) {
        if (isCorrect) {
            editText.setTextColor(getResources().getColor(R.color.correctColor)); // Cambia a tu color correcto
        } else {
            editText.setTextColor(getResources().getColor(R.color.incorrectColor)); // Cambia a tu color incorrecto
        }
    }


}
