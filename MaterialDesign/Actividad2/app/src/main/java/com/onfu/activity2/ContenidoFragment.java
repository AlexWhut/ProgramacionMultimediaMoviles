package com.onfu.activity2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;

public class ContenidoFragment extends Fragment {
    // Constantes para los argumentos
    private static final String ARG_TEXTO = "arg_texto";
    private static final String ARG_DRAWABLE_ID = "arg_drawable_id";
    private static final String ARG_TOAST_MSG = "arg_toast_msg";

    // Variables de instancia
    private String texto;
    private int drawableId;
    private String toastMsg;

    /**
     * Constructor "fábrica" estático.
     * Es la forma recomendada de crear fragments con argumentos.
     */
    public static ContenidoFragment newInstance(String texto, int drawableId, String toastMsg) {
        ContenidoFragment fragment = new ContenidoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXTO, texto);
        args.putInt(ARG_DRAWABLE_ID, drawableId);
        args.putString(ARG_TOAST_MSG, toastMsg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recuperamos los argumentos aquí.
        if (getArguments() != null) {
            texto = getArguments().getString(ARG_TEXTO);
            drawableId = getArguments().getInt(ARG_DRAWABLE_ID);
            toastMsg = getArguments().getString(ARG_TOAST_MSG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflamos (cargamos) el layout XML para este fragmento
        return inflater.inflate(R.layout.fragment_contenido, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Encontrar las vistas
        ImageView imageView = view.findViewById(R.id.fragment_image);
        TextView textView = view.findViewById(R.id.fragment_text);
        MaterialButton button = view.findViewById(R.id.fragment_button);

        // Configurar las vistas con los datos
        if (texto != null) {
            textView.setText(texto);
        }
        if (drawableId != 0) {
            imageView.setImageResource(drawableId);
        }
        // Personalización: Cambié el texto del botón y el mensaje del Toast
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toastMsg != null && getContext() != null) {
                    Toast.makeText(getContext(), toastMsg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
