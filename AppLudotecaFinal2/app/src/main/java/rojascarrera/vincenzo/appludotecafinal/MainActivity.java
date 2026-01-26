package rojascarrera.vincenzo.appludotecafinal;

// Importaciones necesarias para que funcione Android
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Esta es la actividad principal de la aplicacion
// Desde aqui el usuario puede insertar, consultar, borrar y listar videos
public class MainActivity extends AppCompatActivity {

    // Declaramos las cajas de texto
    EditText edRef, edTitulo, edAutor;

    // Declaramos la base de datos
    CrearBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Indicamos que esta actividad usa el layout activity_main.xml
        setContentView(R.layout.activity_main);

        // Conectamos las variables con los elementos del layout
        edRef = findViewById(R.id.etReferencia);
        edTitulo = findViewById(R.id.etTitulo);
        edAutor = findViewById(R.id.etAutor);

        // Conectamos los botones
        Button btnInsertar = findViewById(R.id.btnInsertar);
        Button btnConsultar = findViewById(R.id.btnConsultar);
        Button btnBorrar = findViewById(R.id.btnBorrar);
        Button btnListar = findViewById(R.id.btnListar);

        // Creamos la base de datos
        bd = new CrearBD(this);

        // Cuando se pulsa insertar
        btnInsertar.setOnClickListener(v -> insertarVideo());

        // Cuando se pulsa consultar
        btnConsultar.setOnClickListener(v -> consultarVideo());

        // Cuando se pulsa borrar
        btnBorrar.setOnClickListener(v -> borrarVideo());

        // Cuando se pulsa listar
        btnListar.setOnClickListener(v -> {
            // Abrimos la segunda actividad
            Intent i = new Intent(this, MostrarVideos.class);
            startActivity(i);
        });
    }

    // Inserta un video en la base de datos
    private void insertarVideo() {

        // Si la referencia esta vacia no se inserta
        if (edRef.getText().toString().isEmpty()) {
            mostrarToast("Referencia vacia");
            return;
        }

        // Abrimos la base de datos en modo escritura
        SQLiteDatabase db = bd.getWritableDatabase();

        // Guardamos los datos en un objeto intermedio
        ContentValues valores = new ContentValues();
        valores.put("referencia", edRef.getText().toString());
        valores.put("titulo", edTitulo.getText().toString());
        valores.put("autor", edAutor.getText().toString());

        // Insertamos en la tabla
        db.insert("videosFinal", null, valores);

        mostrarToast("Video insertado");
        limpiarCajas();
    }

    // Consulta un video por referencia
    private void consultarVideo() {

        // Abrimos la base de datos en modo lectura
        SQLiteDatabase db = bd.getReadableDatabase();

        // Buscamos el video
        Cursor c = db.rawQuery(
                "SELECT titulo, autor FROM videosFinal WHERE referencia=?",
                new String[]{edRef.getText().toString()}
        );

        // Si existe
        if (c.moveToFirst()) {
            edTitulo.setText(c.getString(0));
            edAutor.setText(c.getString(1));
        } else {
            mostrarToast("No existe");
        }

        c.close();
    }

    // Borra un video por referencia
    private void borrarVideo() {

        SQLiteDatabase db = bd.getWritableDatabase();

        int filas = db.delete(
                "videosFinal",
                "referencia=?",
                new String[]{edRef.getText().toString()}
        );

        if (filas > 0) {
            mostrarToast("Video borrado");
            limpiarCajas();
        } else {
            mostrarToast("No se pudo borrar");
        }
    }

    // Limpia todas las cajas de texto
    private void limpiarCajas() {
        edRef.setText("");
        edTitulo.setText("");
        edAutor.setText("");
    }

    // Muestra mensajes en pantalla
    private void mostrarToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
