package rojas.vincenzo.appsimpson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaAdapter extends ArrayAdapter<String> {

    Context context;
    int[] imagenes;
    String[] titulos;
    String[] descripciones;

    public ListaAdapter(Context c, String[] tit, int[] imgs, String[] desc) {
        super(c, R.layout.item_lista, R.id.titulo, tit);
        this.context = c;
        this.imagenes = imgs;
        this.titulos = tit;
        this.descripciones = desc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fila = inflater.inflate(R.layout.item_lista, parent, false);

        ImageView imagen = fila.findViewById(R.id.imagen);
        TextView titulo = fila.findViewById(R.id.titulo);
        TextView descrip = fila.findViewById(R.id.desc);

        imagen.setImageResource(imagenes[position]);
        titulo.setText(titulos[position]);
        descrip.setText(descripciones[position]);

        return fila;
    }
}
