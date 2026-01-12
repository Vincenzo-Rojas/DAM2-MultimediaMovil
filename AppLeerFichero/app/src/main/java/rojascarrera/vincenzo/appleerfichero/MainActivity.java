package rojascarrera.vincenzo.appleerfichero;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private RadioGroup grupoMelodias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grupoMelodias = findViewById(R.id.grupoMelodias);

        leerFicheroYCrearRadios();
    }

    private void leerFicheroYCrearRadios() {
        try (
                InputStream is = getResources().openRawResource(R.raw.melodias);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))
        ) {
            String linea;
            boolean primerRadio = true;

            while ((linea = br.readLine()) != null) {
                RadioButton rb = new RadioButton(this);
                rb.setText(linea);
                grupoMelodias.addView(rb);

                if (primerRadio) {
                    rb.setChecked(true);
                    primerRadio = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
