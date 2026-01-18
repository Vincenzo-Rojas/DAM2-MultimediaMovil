package rojascarrera.vincenzo.appbasededatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PRUEBABD extends AppCompatActivity {

    TextView tvContenido;
    Button btnInsertar, btnActualizar, btnBorrar, btnMostrar;

    CrearBD crearBD;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos del layout
        tvContenido = findViewById(R.id.tvContenido);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnMostrar = findViewById(R.id.btnMostrar);

        // Apertura de la base de datos (queda abierta para App Inspection)
        crearBD = new CrearBD(this);
        deleteDatabase(CrearBD.NOMBREBD);
        bd = crearBD.getWritableDatabase();

        // INSERT
        btnInsertar.setOnClickListener(v -> {
            try {
                bd.execSQL("INSERT INTO articulos VALUES(1,'papel')");
                bd.execSQL("INSERT INTO articulos VALUES(2,'lapiz')");
                bd.execSQL("INSERT INTO articulos VALUES(3,'carpeta')");
                bd.execSQL("INSERT INTO articulos VALUES(4,'boligrafo')");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // UPDATE
        btnActualizar.setOnClickListener(v -> {
            bd.execSQL("UPDATE articulos SET nombre='papel charol' WHERE ref=1");
            bd.execSQL("UPDATE articulos SET nombre='cinta adhesiva' WHERE nombre='carpeta'");
        });

        // DELETE
        btnBorrar.setOnClickListener(v -> {
            bd.execSQL("DELETE FROM articulos WHERE nombre='lapiz'");
        });

        // SELECT
        btnMostrar.setOnClickListener(v -> {
            Cursor cursor = bd.rawQuery("SELECT nombre FROM articulos", null);

            StringBuilder sb = new StringBuilder();
            if (cursor.moveToFirst()) {
                do {
                    sb.append(cursor.getString(0)).append("\n");
                } while (cursor.moveToNext());
            }

            tvContenido.setText(sb.toString());
            cursor.close();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (bd != null && bd.isOpen()) {
            bd.close();
        }

        deleteDatabase(CrearBD.NOMBREBD);
    }

}
