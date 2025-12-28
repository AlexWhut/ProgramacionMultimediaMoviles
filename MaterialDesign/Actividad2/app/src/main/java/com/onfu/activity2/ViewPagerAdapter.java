package com.onfu.activity2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    // Número total de pestañas
    private static final int NUM_TABS = 3;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Personalización: Cambié los textos, imágenes y mensajes de cada fragmento
        switch (position) {
            case 0:
                return ContenidoFragment.newInstance(
                    "Primavera: Flores coloridas",
                    R.drawable.flor, // Personalización: imagen de flor
                    "¡Bienvenido a la primavera!"
                );
            case 1:
                return ContenidoFragment.newInstance(
                        "Fauna: Animales increíbles",
                        R.drawable.animal, // Personalización: imagen de animal
                        "¡Explora el mundo animal!"
                );
            case 2:
                return ContenidoFragment.newInstance(
                        "Paisaje: Naturaleza viva",
                        R.drawable.paisaje, // Personalización: imagen de paisaje
                        "¡Disfruta del paisaje!"
                );
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
