package rojas.vincenzo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    // Variable para alternar fragments
    private boolean cargarFragmentoB = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargar fragmentoA inicialmente
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new fragmentoA())
                .commit();

        // Obtener referencia al FloatingActionButton
        FloatingActionButton fab = findViewById(R.id.fab);

        // Alternar fragments al pulsar el botón
        fab.setOnClickListener(view -> {
            if (cargarFragmentoB) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new fragmentoB())
                        .commit();
                cargarFragmentoB = false;
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new fragmentoA())
                        .commit();
                cargarFragmentoB = true;
            }
        });
    }
}
