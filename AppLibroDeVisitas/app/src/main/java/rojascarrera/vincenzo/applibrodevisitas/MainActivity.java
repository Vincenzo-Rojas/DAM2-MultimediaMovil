package rojascarrera.vincenzo.applibrodevisitas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private TextView etVisitantes;

    private final static String NOMBRE_FICHERO = "visitantes.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etVisitantes = findViewById(R.id.etVisitantes);

        etNombre.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    actionId == EditorInfo.IME_ACTION_GO ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                onNuevoNombre();
                actualizarVisitantes();
                return true;
            }
            return false;
        });

        actualizarVisitantes();
    }

    private void onNuevoNombre() {
        String nombre = etNombre.getText().toString().trim();

        if (nombre.isEmpty()) {
            // Solo toast de error si el campo esta vacio
            mostrarMensaje(getString(R.string.errorNombre));
            return;
        }

        try {
            FileOutputStream fos = openFileOutput(NOMBRE_FICHERO, MODE_APPEND);
            fos.write((nombre + "\n").getBytes());
            fos.close();
            etNombre.setText("");

            // Solo mostrar toast de bienvenida si se ha guardado correctamente
            mostrarMensaje(getString(R.string.bienvenido));

        } catch (Exception e) {
            mostrarMensaje(getString(R.string.errorRegistro));
        }
    }

    private void actualizarVisitantes() {
        try {
            FileInputStream fis = openFileInput(NOMBRE_FICHERO);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String linea;
            StringBuilder texto = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                texto.append(linea).append("\n");
            }

            br.close();
            fis.close();
            etVisitantes.setText(texto.toString());
        } catch (Exception e) {
            mostrarMensaje(getString(R.string.errorVisitantes));
        }
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
