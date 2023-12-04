package com.example.mymenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInFragment extends Fragment {

    DatabaseHelper myDb;
    EditText etMail, etId;
    Button btnSignIn;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        // Initialize views
        btnSignIn = view.findViewById(R.id.btnSignIn);
        etMail = view.findViewById(R.id.etMail);
        etId = view.findViewById(R.id.etId);

        // Set up click listener
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    myDb = new DatabaseHelper(getContext());
                    if (myDb.dataExists(myDb.getWritableDatabase(), etId.getText().toString(), etMail.getText().toString())) {
                        intent = new Intent(getContext(), SignedInActivity.class);
                        intent.putExtra("id", etId.getText().toString());
                        startActivity(intent);

                        Toast.makeText(getContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        return view;  // Make sure to return the inflated view
    }
}