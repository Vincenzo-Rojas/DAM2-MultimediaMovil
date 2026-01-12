package rojascarrera.vincenzo.appsalvarinstancia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int valor;
    private TextView txtValor;

    private final static String VALOR_GUARDADO = "valor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValor = findViewById(R.id.txtValor);
        Button btnMas = findViewById(R.id.btnMas);
        Button btnMenos = findViewById(R.id.btnMenos);

        if (savedInstanceState != null) {
            valor = savedInstanceState.getInt(VALOR_GUARDADO);
        } else {
            valor = Integer.parseInt(txtValor.getText().toString());
        }

        txtValor.setText(String.valueOf(valor));

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementar();
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementar();
            }
        });
    }

    public void incrementar() {
        valor++;
        txtValor.setText(String.valueOf(valor));
    }

    public void decrementar() {
        valor--;
        txtValor.setText(String.valueOf(valor));
    }

    @Override
    public void onSaveInstanceState(Bundle estado) {
        super.onSaveInstanceState(estado);
        estado.putInt(VALOR_GUARDADO, valor);
    }
}
