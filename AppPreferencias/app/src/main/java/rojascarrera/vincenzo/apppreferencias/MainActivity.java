package rojascarrera.vincenzo.apppreferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static String PREFERENCIA_MELODIA = "melodia";

    private RadioGroup grupoMelodias;
    private Button btnAceptar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grupoMelodias = findViewById(R.id.preferenciasMelodia);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);

        SharedPreferences preferencias = getPreferences(Context.MODE_PRIVATE);
        int idSeleccionado = preferencias.getInt(
                PREFERENCIA_MELODIA,
                R.id.radio0
        );
        grupoMelodias.check(idSeleccionado);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPreferencia();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void guardarPreferencia() {
        SharedPreferences preferencias = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        int idSeleccionado = grupoMelodias.getCheckedRadioButtonId();
        editor.putInt(PREFERENCIA_MELODIA, idSeleccionado);
        editor.commit();

        finish();
    }
}
