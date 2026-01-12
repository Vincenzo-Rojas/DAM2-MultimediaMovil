package rojascarrera.vincenzo.appenviodatos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SegundaActividad extends AppCompatActivity {

    TextView tvSaludo;
    EditText etRespuesta;
    Button btnResponder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);

        tvSaludo = findViewById(R.id.tvSaludo);
        etRespuesta = findViewById(R.id.etRespuesta);
        btnResponder = findViewById(R.id.btnResponder);

        String nombre = getIntent().getStringExtra("nombre");
        tvSaludo.setText("Hola, " + nombre);

        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String respuesta = etRespuesta.getText().toString().trim();

                Intent datos = new Intent();
                datos.putExtra("saludo", respuesta.isEmpty()
                        ? "Antipático"
                        : respuesta);

                setResult(RESULT_OK, datos);
                finish();
            }
        });
    }
}
