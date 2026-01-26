package app.examen2;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class verLibros extends AppCompatActivity {
    ListView lista;
    String[] titulos;
    String[] autores;
    int[] imagenes = {
            R.drawable.libro1,
            R.drawable.libro2,
            R.drawable.libro3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_mostrar_libros);

        Resources res = getResources();
        titulos = res.getStringArray(R.array.titulo);
        autores = res.getStringArray(R.array.autor);

        lista = findViewById(R.id.lista);

        ListaAdapter adapter = new ListaAdapter(this, titulos, imagenes, autores);
        lista.setAdapter(adapter);

        // Listener para clicks en items
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(verLibros.this, "Seleccionaste: " + titulos[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}
