package rojas.vincenzo.appsimpson;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ListView lista;
    String[] titulos;
    String[] descripciones;
    int[] imagenes = {
            R.drawable.hommer,
            R.drawable.marge,
            R.drawable.bart3,
            R.drawable.lisa,
            R.drawable.magie
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        titulos = res.getStringArray(R.array.titulo);
        descripciones = res.getStringArray(R.array.descripcion);

        lista = findViewById(R.id.lista);

        ListaAdapter adapter = new ListaAdapter(this, titulos, imagenes, descripciones);
        lista.setAdapter(adapter);

        // Listener para clicks en items
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Seleccionaste: " + titulos[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
