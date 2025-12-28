package com.onfu.actividad4;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActividad4 extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter2 viewPagerAdapter2;
    private final String[] titulosTabs = new String[]{"UNO", "DOS", "CUATRO"};
    private final int[] iconosTabs = new int[]{
        R.drawable.ic_tab_landscape,
        R.drawable.ic_tab_portrait,
        R.drawable.ic_tab_art
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad4);
        toolbar = findViewById(R.id.mainToolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setSupportActionBar(toolbar);
        viewPagerAdapter2 = new ViewPagerAdapter2(this);
        viewPager.setAdapter(viewPagerAdapter2);
        new TabLayoutMediator(tabLayout, viewPager,
            (tab, position) -> {
                tab.setText(titulosTabs[position]);
                tab.setIcon(iconosTabs[position]);
            }
        ).attach();
    }
}
