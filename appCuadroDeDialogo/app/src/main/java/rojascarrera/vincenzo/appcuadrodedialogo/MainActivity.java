package rojascarrera.vincenzo.appcuadrodedialogo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AccionesDialogo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Botón que lanza el diálogo
    public void mostrarDialogo(View view) {
        Dialogo dialogo = new Dialogo();
        dialogo.show(getSupportFragmentManager(), "tagDialogo");
    }

    // OK → reiniciar juego
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(this, "Juego reiniciado", Toast.LENGTH_SHORT).show();
        // Aquí podrías resetear variables o reiniciar lógica de juego real
    }

    // Cancelar → finalizar juego
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "Juego finalizado", Toast.LENGTH_SHORT).show();
        // Aquí podrías cerrar la app o terminar la Activity con finish()
    }
}
