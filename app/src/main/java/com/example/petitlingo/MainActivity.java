package com.example.petitlingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// MainActivity.java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtén una referencia al botón
        Button btnGoToFragment = findViewById(R.id.btnGoToFragment);

        // Configura el click listener
        btnGoToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reemplaza el contenido del fragment_container con fragment_types
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new TypesFragment())
                        .addToBackStack(null) // Opcional: agrega la transacción al back stack
                        .commit();
            }
        });
    }
}
