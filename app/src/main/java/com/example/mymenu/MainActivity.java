package com.example.mymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mymenu.Sign_in_Log_in.MainFragmentHub;

public class MainActivity extends AppCompatActivity {

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Toast.makeText(this, "You clicked Home", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.Search) {
            intent = new Intent(this, MainFragmentHub.class);
            startActivity(intent);

            Toast.makeText(this, "You clicked Sign in/out", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.About) {
            intent = new Intent(this, AboutMeActivity.class);
            startActivity(intent);

            Toast.makeText(this, "You clicked About me", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        intent = new Intent(this, MainFragmentHub.class);
        startActivity(intent);

        Toast.makeText(this, "Sign up/in page", Toast.LENGTH_SHORT).show();
    }
}