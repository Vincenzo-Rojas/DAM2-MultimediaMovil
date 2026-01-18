package rojascarrera.vincenzo.appbasededatos6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CrearDB crearDB;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearDB = new CrearDB(this);
        bd = crearDB.getWritableDatabase();

        Button btnMostrar = findViewById(R.id.btnMostrar);

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarLista();
            }
        });
    }

    private ArrayList<String> crearLista() {
        ArrayList<String> lista = new ArrayList<>();

        Cursor cursor = bd.rawQuery("SELECT nombre FROM articulos", null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

    private void mostrarLista() {
        ArrayList<String> listaArticulos = crearLista();

        Intent intent = new Intent(this, MuestraDatos.class);
        intent.putStringArrayListExtra("lista", listaArticulos);
        startActivity(intent);
    }
}
