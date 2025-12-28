package com.onfu.actividad4;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter2 extends FragmentStateAdapter {
    private static final int NUM_TABS = 3;
    public ViewPagerAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new FragmentPestanaUno();
            case 1: return new FragmentPestanaDos();
            case 2: return new FragmentPestanaCuatro();
            default: return null;
        }
    }
    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
