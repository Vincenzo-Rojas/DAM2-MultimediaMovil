package rojascarrera.vincenzo.appludotecafinal;

// Esta clase representa un video
// Es solo un contenedor de datos
public class Video {

    private String referencia;
    private String titulo;
    private String autor;

    // Constructor
    public Video(String referencia, String titulo, String autor) {
        this.referencia = referencia;
        this.titulo = titulo;
        this.autor = autor;
    }

    // Devuelve la referencia
    public String getReferencia() {
        return referencia;
    }

    // Devuelve el titulo
    public String getTitulo() {
        return titulo;
    }

    // Devuelve el autor
    public String getAutor() {
        return autor;
    }

    // Convierte el objeto en texto
    @Override
    public String toString() {
        return referencia + " - " + titulo + " - " + autor;
    }
}
