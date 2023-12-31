package com.example.mymenu.Sign_in_Log_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mymenu.AboutMeActivity;
import com.example.mymenu.MainActivity;
import com.example.mymenu.MyViewPagerAdapter;
import com.example.mymenu.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainFragmentHub extends AppCompatActivity {

    ViewPager2 viewPager;
    MyViewPagerAdapter myAdapter;
    Intent intent;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_hub);

        TabLayout tabLayout = findViewById(R.id.tabLayout);


        viewPager = findViewById(R.id.viewPager2);
        myAdapter = new MyViewPagerAdapter(
                getSupportFragmentManager(),
                getLifecycle());
        myAdapter.addFragment(new RegisterFragment());
        myAdapter.addFragment(new SignInFragment());
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(myAdapter);

        new TabLayoutMediator(
                tabLayout,
                viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Tab " + (position + 1));

                    }
                }
        ).attach();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        if (menu instanceof MenuBuilder) {
            MenuBuilder mb = (MenuBuilder) menu;
            mb.setOptionalIconsVisible(true);
        }
        MenuItem menuItem = menu.findItem(R.id.Search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Home){
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(this, "You clicked Home", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.Search) {
            Toast.makeText(this, "You clicked Sign in/out", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.About) {
            intent = new Intent(this, AboutMeActivity.class);
            startActivity(intent);

            Toast.makeText(this, "You clicked About me", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}