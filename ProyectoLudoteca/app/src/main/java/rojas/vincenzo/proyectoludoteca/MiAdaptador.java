package rojas.vincenzo.proyectoludoteca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<Video> lista;

    public MiAdaptador(List<Video> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtRef, txtTitulo, txtAutor;

        public ViewHolder(View itemView) {
            super(itemView);
            txtRef = itemView.findViewById(R.id.txtRef);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtAutor = itemView.findViewById(R.id.txtAutor);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video v = lista.get(position);
        holder.txtRef.setText(v.getReferencia());
        holder.txtTitulo.setText(v.getTitulo());
        holder.txtAutor.setText(v.getAutor());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
