package com.example.mymenu.Sign_in_Log_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymenu.AboutMeActivity;
import com.example.mymenu.DataSendActivity;
import com.example.mymenu.DatabaseHelper;
import com.example.mymenu.R;

public class SignedInActivity extends AppCompatActivity {

    Intent intent;
    DatabaseHelper myDb;
    String idIntent;
    TextView tvWelcome;
    Button btnLogout, btnBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        myDb = new DatabaseHelper(this);

        intent = getIntent();
        idIntent = intent.getStringExtra("id");
        tvWelcome = findViewById(R.id.tvWelcome);

        // Display welcome message with user name and surename by id
        tvWelcome.setText("Welcome " + myDb.getFullNameById(idIntent) + ", Have fun!!!");

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignedInActivity.this, MainFragmentHub.class);
                startActivity(intent);

                Toast.makeText(SignedInActivity.this, "You clicked Logout", Toast.LENGTH_SHORT).show();
            }
        });

        btnBluetooth = findViewById(R.id.btnBluetooth);
        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignedInActivity.this, DataSendActivity.class);
                startActivity(intent);
            }
        });
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
}