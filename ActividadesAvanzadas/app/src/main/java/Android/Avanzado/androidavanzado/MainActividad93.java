package Android.Avanzado.androidavanzado;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

// Personalización: Se muestra un mensaje en log al iniciar la actividad.
public class MainActividad93 extends FragmentActivity implements FragmentoLista.Callbacks {
    private boolean mDosPaneles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad93);
        android.util.Log.i("MainActividad93", "Actividad iniciada correctamente");
        if (findViewById(R.id.contenedor_detalle) != null) {
            mDosPaneles = true;
        } else {
            mDosPaneles = false;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.contenedor_movil, new FragmentoLista())
                        .commit();
            }
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mDosPaneles) {
            Bundle arguments = new Bundle();
            arguments.putString(FragmentoDetalle.ARG_ITEM_ID, id);
            FragmentoDetalle fragment = new FragmentoDetalle();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_detalle, fragment)
                    .commit();
        } else {
            Bundle arguments = new Bundle();
            arguments.putString(FragmentoDetalle.ARG_ITEM_ID, id);
            FragmentoDetalle fragment = new FragmentoDetalle();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_movil, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
