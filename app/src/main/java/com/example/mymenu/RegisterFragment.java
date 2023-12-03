package com.example.mymenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterFragment extends Fragment {

    private DatabaseHelper myDb;
    private EditText name, surename, email, etId, etPhone;
    private Button btnInsert, btnView, btnUpdate, btnDelete;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        myDb = new DatabaseHelper(requireContext());

        name = view.findViewById(R.id.etName);
        surename = view.findViewById(R.id.etSure);
        email = view.findViewById(R.id.etMail);
        etId = view.findViewById(R.id.etId);
        etPhone = view.findViewById(R.id.etPhone);

        btnInsert = view.findViewById(R.id.btnInsert);
        btnView = view.findViewById(R.id.btnView);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);

        setupClickListeners();

        return view;
    }

    private void setupClickListeners() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), ShowDataActivity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    private void insertData() {
        boolean isInserted = myDb.insertData(
                etId.getText().toString(),
                name.getText().toString(),
                surename.getText().toString(),
                email.getText().toString(),
                etPhone.getText().toString());

        showResultToast(isInserted, "Data Inserted");
    }

    private void updateData() {
        boolean isUpdated = myDb.updateData(
                etId.getText().toString(),
                name.getText().toString(),
                surename.getText().toString(),
                email.getText().toString(),
                etPhone.getText().toString());

        showResultToast(isUpdated, "Data Updated");
    }

    private void deleteData() {
        int rowsAffected = myDb.deleteData(etId.getText().toString());
        showResultToast(rowsAffected > 0, "Data " + (rowsAffected > 0 ? "Deleted" : "Not Deleted"));
    }

    private void showResultToast(boolean success, String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        if (success) {
            clearFields();
        }
    }

    private void clearFields() {
        etId.setText("");
        name.setText("");
        surename.setText("");
        email.setText("");
        etPhone.setText("");
    }
}