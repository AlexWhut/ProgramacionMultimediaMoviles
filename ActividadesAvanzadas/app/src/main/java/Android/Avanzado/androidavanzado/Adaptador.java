package Android.Avanzado.androidavanzado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import Android.Avanzado.androidavanzado.Lista_entrada;

// Personalización: El adaptador usa un layout de fila personalizado con bordes redondeados.
public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Lista_entrada> datos;

    public Adaptador(Context context, ArrayList<Lista_entrada> datos) {
        this.context = context;
        this.datos = datos;
    }

    @Override
    public int getCount() { return datos.size(); }

    @Override
    public Object getItem(int position) { return datos.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listado, parent, false);
        }
        ImageView imagen = convertView.findViewById(R.id.imgListado);
        TextView texto = convertView.findViewById(R.id.txtListadoTitulo);
        Lista_entrada item = datos.get(position);
        imagen.setImageResource(item.getIdImagen());
        texto.setText(item.getTextoEncima());
        return convertView;
    }
}
