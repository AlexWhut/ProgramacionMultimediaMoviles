package Android.Avanzado.androidavanzado;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

// Personalización: Se añade log para depuración al seleccionar un elemento.
public class FragmentoLista extends ListFragment {
    public interface Callbacks {
        void onItemSelected(String id);
    }
    private Callbacks mCallbacks = sDummyCallbacks;
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) { }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new Adaptador(getActivity(), Contenido.ENT_LISTA));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new IllegalStateException("La actividad debe implementar Callbacks");
        }
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Personalización: Log para depuración
        android.util.Log.d("FragmentoLista", "Elemento seleccionado: " + Contenido.ENT_LISTA.get(position).getTextoEncima());
        mCallbacks.onItemSelected(Contenido.ENT_LISTA.get(position).getId());
    }
}
