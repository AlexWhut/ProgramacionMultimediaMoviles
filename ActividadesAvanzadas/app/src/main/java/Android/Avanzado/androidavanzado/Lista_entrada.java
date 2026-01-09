package Android.Avanzado.androidavanzado;

// Personalización: Este POJO incluye un método toString personalizado para depuración.
public class Lista_entrada {
    private String id;
    private int idImagen;
    private String textoEncima;
    private String textoDebajo;

    public Lista_entrada(String id, int idImagen, String textoEncima, String textoDebajo) {
        this.id = id;
        this.idImagen = idImagen;
        this.textoEncima = textoEncima;
        this.textoDebajo = textoDebajo;
    }

    public String getId() { return id; }
    public int getIdImagen() { return idImagen; }
    public String getTextoEncima() { return textoEncima; }
    public String getTextoDebajo() { return textoDebajo; }

    // Personalización: método para depuración
    @Override
    public String toString() {
        return textoEncima + " (" + id + ")";
    }
}
