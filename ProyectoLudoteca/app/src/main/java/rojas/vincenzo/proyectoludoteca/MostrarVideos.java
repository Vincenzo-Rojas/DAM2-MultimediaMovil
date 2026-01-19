package rojas.vincenzo.proyectoludoteca;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MostrarVideos extends AppCompatActivity {

    RecyclerView recycler;
    CrearBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_videos);

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        bd = new CrearBD(this);

        List<Video> items = listarVideos();
        MiAdaptador adaptador = new MiAdaptador(items);
        recycler.setAdapter(adaptador);
    }

    private List<Video> listarVideos() {
        List<Video> lista = new ArrayList<>();

        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT referencia, titulo, autor FROM " + CrearBD.TABLA_VIDEOS,
                null
        );

        while (c.moveToNext()) {
            lista.add(new Video(
                    c.getString(0),
                    c.getString(1),
                    c.getString(2)
            ));
        }

        c.close();
        db.close();

        return lista;
    }
}
