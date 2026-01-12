package rojascarrera.vincenzo.appintent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnPulsame;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contexto = MainActivity.this;
        btnPulsame = findViewById(R.id.btnPulsame);

        btnPulsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(contexto, SegundaActividad.class);
                startActivity(i);

                Toast.makeText(contexto, "Intent enviado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
