package com.example.mymenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AboutMeActivity extends AppCompatActivity {

    Intent intent;

    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    TextView tvWelcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me_screen);

        tvWelcome = findViewById(R.id.tvWelcome);
        buildText();
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
            intent = new Intent(this, MainFragmentHub.class);
            startActivity(intent);

            Toast.makeText(this, "You clicked Sign in/out", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.About) {
            Toast.makeText(this, "You clicked About me", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void buildText() {
        is = getResources().openRawResource(R.raw.about_me);
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        String st, all = "";

        try {
            while ((st = br.readLine()) != null)
                all += st + "\n";
            br.close();
        } catch (IOException e) {
            Toast.makeText(this, "Erorr", Toast.LENGTH_SHORT).show();
        }
        tvWelcome.setText(all);
    }

    public void goBack(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
