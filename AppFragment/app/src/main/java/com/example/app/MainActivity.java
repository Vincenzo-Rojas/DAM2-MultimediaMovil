package com.example.app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements EnviarMensaje {
    private Fragment_b fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentB = (Fragment_b) getSupportFragmentManager().findFragmentById(R.id.fragmentB);
    }

    @Override
    public void enviarDato(String dato) {
        if(fragmentB != null){
            fragmentB.recibir(dato);
        }
    }
}
