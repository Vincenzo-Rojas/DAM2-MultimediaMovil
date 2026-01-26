package rojascarrera.vincenzo.appludotecafinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Este adaptador conecta la lista de videos con el RecyclerView
public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    // Lista de videos
    private List<Video> listaVideos;

    // Constructor
    public MiAdaptador(List<Video> listaVideos) {
        this.listaVideos = listaVideos;
    }

    // Esta clase representa una fila del listado
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtRef, txtTitulo, txtAutor;

        public ViewHolder(View itemView) {
            super(itemView);

            // Conectamos los textos del item
            txtRef = itemView.findViewById(R.id.tvReferencia);
            txtTitulo = itemView.findViewById(R.id.tvTitulo);
            txtAutor = itemView.findViewById(R.id.tvAutor);
        }
    }

    // Crea una fila nueva
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);

        return new ViewHolder(vista);
    }

    // Asigna datos a una fila
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Video v = listaVideos.get(position);

        holder.txtRef.setText(v.getReferencia());
        holder.txtTitulo.setText(v.getTitulo());
        holder.txtAutor.setText(v.getAutor());
    }

    // Indica cuantos elementos hay
    @Override
    public int getItemCount() {
        return listaVideos.size();
    }
}
