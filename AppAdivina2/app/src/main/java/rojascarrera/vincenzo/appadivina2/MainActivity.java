package rojascarrera.vincenzo.appadivina2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int numElegido;
    private int numIntentos;

    private TextView etiquetaSuperior;
    private TextView etiquetaIntentos;
    private EditText txtNumero;
    private Button btnProbar;
    private Button btnReiniciar;

    private static final String STATE_NUM_ELEGIDO = "numElegido";
    private static final String STATE_NUM_INTENTOS = "numIntentos";
    private static final String STATE_MENSAJE = "mensajeActual";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etiquetaSuperior = findViewById(R.id.etiquetaSuperior);
        etiquetaIntentos = findViewById(R.id.etiquetaIntentos);
        txtNumero = findViewById(R.id.txtNumero);
        btnProbar = findViewById(R.id.btnProbar);
        btnReiniciar = findViewById(R.id.btnReiniciar);

        btnProbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarNumero();
            }
        });

        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciaPartida();
            }
        });

        if (savedInstanceState == null) {
            reiniciaPartida();
        } else {
            numElegido = savedInstanceState.getInt(STATE_NUM_ELEGIDO);
            numIntentos = savedInstanceState.getInt(STATE_NUM_INTENTOS);

            if (numElegido == -1) {
                partidaAcabada();
            } else {
                etiquetaSuperior.setText(
                        savedInstanceState.getString(STATE_MENSAJE)
                );
                actualizarIntentos();
            }
        }
    }

    private void reiniciaPartida() {
        Random r = new Random();
        numElegido = r.nextInt(100) + 1;
        numIntentos = 0;

        etiquetaSuperior.setText("Introduce un numero del 1 al 100");
        actualizarIntentos();

        txtNumero.setText("");
        txtNumero.setVisibility(View.VISIBLE);
        btnProbar.setVisibility(View.VISIBLE);
        btnReiniciar.setVisibility(View.GONE);
    }

    private void comprobarNumero() {
        if (txtNumero.getText().toString().isEmpty()) {
            return;
        }

        int numIntroducido = Integer.parseInt(txtNumero.getText().toString());
        numIntentos++;

        if (numIntroducido < numElegido) {
            etiquetaSuperior.setText("El numero es mayor");
        } else if (numIntroducido > numElegido) {
            etiquetaSuperior.setText("El numero es menor");
        } else {
            etiquetaSuperior.setText("Correcto. Has acertado");
            numElegido = -1;
            partidaAcabada();
            return;
        }

        actualizarIntentos();
        txtNumero.setText("");
    }

    private void actualizarIntentos() {
        etiquetaIntentos.setText("Intentos: " + numIntentos);
    }

    private void partidaAcabada() {
        txtNumero.setVisibility(View.GONE);
        btnProbar.setVisibility(View.GONE);
        btnReiniciar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle estado) {
        super.onSaveInstanceState(estado);

        estado.putInt(STATE_NUM_ELEGIDO, numElegido);
        estado.putInt(STATE_NUM_INTENTOS, numIntentos);
        estado.putString(STATE_MENSAJE, etiquetaSuperior.getText().toString());
    }
}
