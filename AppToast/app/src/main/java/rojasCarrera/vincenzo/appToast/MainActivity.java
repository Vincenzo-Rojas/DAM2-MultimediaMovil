package rojasCarrera.vincenzo.appToast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Método asociado al botón desde XML: android:onClick="onPulsame"
    public void onPulsame(View view) {

        Context contexto = getApplicationContext();

        // ---------------------------
        // 1. Toast con mensaje simple
        // ---------------------------
        Toast.makeText(contexto, "¡Hola desde el Toast!", Toast.LENGTH_SHORT).show();

        // ---------------------------
        // 2. Toast con mensaje personalizado
        // ---------------------------
        Toast toastPersonalizado = Toast.makeText(contexto, "", Toast.LENGTH_LONG);
        toastPersonalizado.setText("Mensaje personalizado");
        toastPersonalizado.show();

        // ---------------------------
        // 3. Toast con DatePicker
        // ---------------------------
        Toast toastDatePicker = new Toast(contexto);
        toastDatePicker.setDuration(Toast.LENGTH_LONG);
        DatePicker calendario = new DatePicker(contexto);
        toastDatePicker.setView(calendario);
        toastDatePicker.show();

        // ---------------------------
        // 4. Toast con TimePicker
        // ---------------------------
        Toast toastTimePicker = new Toast(contexto);
        toastTimePicker.setDuration(Toast.LENGTH_LONG);
        TimePicker reloj = new TimePicker(contexto);
        toastTimePicker.setView(reloj);
        toastTimePicker.show();

        // ---------------------------
        // 5. SnackBar simple
        // ---------------------------
        Snackbar.make(view, "¡Hola desde SnackBar!", Snackbar.LENGTH_LONG)
                .setTextColor(Color.RED)
                .setBackgroundTint(Color.CYAN)
                .show();

        // ---------------------------
        // 6. SnackBar con acción (muestra TimePicker)
        // ---------------------------
        Snackbar.make(view, "¿Quieres ver el reloj?", Snackbar.LENGTH_LONG)
                .setTextColor(Color.RED)
                .setBackgroundTint(Color.WHITE)
                .setAction("Aceptar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = new Toast(v.getContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        TimePicker reloj = new TimePicker(v.getContext());
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.setView(reloj);
                        toast.show();
                    }
                }).show();
    }
}
