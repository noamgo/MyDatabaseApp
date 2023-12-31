package com.example.mymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymenu.Sign_in_Log_in.MainFragmentHub;

public class ShowDataActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText etName;
    Button btnView, btnBack;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        myDb = new DatabaseHelper(this);


    etName = findViewById(R.id.etName);
    btnView = findViewById(R.id.btnView);
    btnView.setOnClickListener(new View.OnClickListener()
    {
        public void onClick (View v){
        Cursor res = myDb.getDataByName(etName.getText().toString());
        if (res.getCount() == 0) {
            //show message
            showData("Error", "No Data Found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("NAME: " + res.getString(1) + "\n");
            buffer.append("SURNAME: " + res.getString(2) + "\n");
            buffer.append("EMAIL: " + res.getString(3) + "\n\n");

        }
        showData("Data", buffer.toString());
        }
    });


    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            intent = new Intent(ShowDataActivity.this, MainFragmentHub.class);
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
            intent = new Intent(this, AboutMeActivity.class);
            startActivity(intent);

            Toast.makeText(this, "You clicked About me", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showData(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}