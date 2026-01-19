package rojas.vincenzo.proyectoludoteca;

public class Video {

    private String referencia;
    private String titulo;
    private String autor;

    public Video(String referencia, String titulo, String autor) {
        this.referencia = referencia;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return referencia + " - " + titulo + " - " + autor;
    }
}
