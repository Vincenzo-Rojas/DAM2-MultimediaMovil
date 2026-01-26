package rojascarrera.vincenzo.appludotecafinal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Esta actividad muestra todos los videos en forma de lista
// Ahora muestra un mensaje si la lista esta vacia
public class MostrarVideos extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<Video> listaVideos;
    TextView tvVacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Indicamos el layout a usar
        setContentView(R.layout.activity_mostrar_videos);

        // Conectamos los elementos del layout
        recycler = findViewById(R.id.recycler);
        tvVacio = findViewById(R.id.tvVacio);

        listaVideos = new ArrayList<>();

        // Abrimos la base de datos
        CrearBD bd = new CrearBD(this);
        SQLiteDatabase db = bd.getReadableDatabase();

        // Consultamos todos los videos
        Cursor c = db.rawQuery(
                "SELECT referencia, titulo, autor FROM videosFinal",
                null
        );

        // Recorremos los resultados y agregamos a la lista
        while (c.moveToNext()) {
            listaVideos.add(
                    new Video(
                            c.getString(0),
                            c.getString(1),
                            c.getString(2)
                    )
            );
        }

        c.close();

        // Si no hay videos, mostramos el mensaje
        if (listaVideos.isEmpty()) {
            tvVacio.setVisibility(View.VISIBLE); // Muestra el mensaje
        } else {
            tvVacio.setVisibility(View.GONE); // Oculta el mensaje si hay datos
        }

        // Configuramos el RecyclerView con la lista de videos
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new MiAdaptador(listaVideos));
    }
}