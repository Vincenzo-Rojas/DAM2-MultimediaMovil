package rojascarrera.vincenzo.appenviodatos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SECONDARY_ACTIVITY_TAG = 1;

    EditText etNombre;
    Button btnEnviar;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contexto = MainActivity.this;
        etNombre = findViewById(R.id.etNombre);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = etNombre.getText().toString().trim();

                if (nombre.isEmpty()) {
                    Toast.makeText(contexto,
                            R.string.error_nombre,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(contexto, SegundaActividad.class);
                    i.putExtra("nombre", nombre);
                    startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECONDARY_ACTIVITY_TAG) {

            if (resultCode == RESULT_OK && data != null) {
                String saludo = data.getStringExtra("saludo");
                Toast.makeText(contexto, saludo, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(contexto,
                        "Antipático",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
