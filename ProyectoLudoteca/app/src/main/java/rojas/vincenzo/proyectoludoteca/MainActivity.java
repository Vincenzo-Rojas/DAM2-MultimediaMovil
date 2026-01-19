package rojas.vincenzo.proyectoludoteca;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edRef, edTitulo, edAutor;
    Button btnInsertar, btnConsultar, btnBorrar, btnListar;
    CrearBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edRef = findViewById(R.id.edRef);
        edTitulo = findViewById(R.id.edTitulo);
        edAutor = findViewById(R.id.edAutor);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnListar = findViewById(R.id.btnListar);

        bd = new CrearBD(this);

        btnInsertar.setOnClickListener(v -> insertarVideo());
        btnConsultar.setOnClickListener(v -> consultarVideo());
        btnBorrar.setOnClickListener(v -> borrarVideo());
        btnListar.setOnClickListener(v ->
                startActivity(new Intent(this, MostrarVideos.class))
        );
    }

    public void verMensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void limpiarCajas() {
        edRef.setText("");
        edTitulo.setText("");
        edAutor.setText("");
    }

    private void insertarVideo() {
        if (edRef.getText().toString().isEmpty() ||
                edTitulo.getText().toString().isEmpty() ||
                edAutor.getText().toString().isEmpty()) {

            verMensajeToast("Campos obligatorios vacios");
            return;
        }

        try {
            SQLiteDatabase db = bd.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("referencia", edRef.getText().toString());
            values.put("titulo", edTitulo.getText().toString());
            values.put("autor", edAutor.getText().toString());

            db.insertOrThrow(CrearBD.TABLA_VIDEOS, null, values);
            db.close();

            verMensajeToast("Video insertado");
            limpiarCajas();

        } catch (Exception e) {
            verMensajeToast("Error al insertar");
        }
    }

    private void consultarVideo() {
        if (edRef.getText().toString().isEmpty()) {
            verMensajeToast("Referencia vacia");
            return;
        }

        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT titulo, autor FROM " + CrearBD.TABLA_VIDEOS +
                        " WHERE referencia=?",
                new String[]{edRef.getText().toString()}
        );

        if (c.moveToFirst()) {
            edTitulo.setText(c.getString(0));
            edAutor.setText(c.getString(1));
        } else {
            verMensajeToast("No existe el video");
        }

        c.close();
        db.close();
    }

    private void borrarVideo() {
        if (edRef.getText().toString().isEmpty() &&
                edTitulo.getText().toString().isEmpty()) {

            verMensajeToast("Introduce referencia o titulo");
            return;
        }

        try {
            SQLiteDatabase db = bd.getWritableDatabase();

            int filas = db.delete(
                    CrearBD.TABLA_VIDEOS,
                    "referencia=? OR titulo=?",
                    new String[]{
                            edRef.getText().toString(),
                            edTitulo.getText().toString()
                    }
            );

            db.close();

            if (filas > 0) {
                verMensajeToast("Video borrado");
                limpiarCajas();
            } else {
                verMensajeToast("No se encontro el video");
            }

        } catch (Exception e) {
            verMensajeToast("Error al borrar");
        }
    }
}
