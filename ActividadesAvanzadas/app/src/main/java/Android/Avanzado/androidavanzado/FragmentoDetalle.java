package Android.Avanzado.androidavanzado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

// Personalización: El título se muestra en mayúsculas siempre.
public class FragmentoDetalle extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    private Lista_entrada mItem;

    public FragmentoDetalle() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Contenido.ENT_MAPA.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.txtDetalleTitulo)).setText(mItem.getTextoEncima().toUpperCase());
            ((TextView) rootView.findViewById(R.id.txtDetalleContenido)).setText(mItem.getTextoDebajo());
            ((ImageView) rootView.findViewById(R.id.imgDetalle)).setImageResource(mItem.getIdImagen());
        }
        return rootView;
    }
}
