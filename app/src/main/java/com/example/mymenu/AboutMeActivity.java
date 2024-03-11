package com.example.mymenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
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

import Tetris.Tetris;

public class AboutMeActivity extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    int[][] board;
    Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me_screen);

        // Assuming you have a GridLayout defined in your layout XML file with id gridLayout
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Define the number of rows and columns
        int numRows = 20;
        int numCols = 10;

        board = new int[numRows][numCols];

        // Initialize the game board
        initializeGameBoard();

        // Create a random shape and put it on the board
        createShape(numRows, numCols);

        // Update the board after spawning the shape
        UpdateBoard(numRows, numCols);

        // Assuming you have initialized imageView here
        // ...

        // Add OnClickListener if needed
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                // Handle click event based on the position
            }
        });
    }

    private void initializeGameBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // Set initial values for the board (0 for empty)
                board[i][j] = 0;
            }
        }
    }

    // Method to spawn a new Shape on the board that randomize a shape
    private void createShape(int numRows, int numCols) {
        int[][] selectedShape = TetrisShapes.getRandomShape();

        int initialRow = 0;
        int initialCol = numCols / 2 - selectedShape[0].length / 2;
        putShapeOnBoard(selectedShape, initialRow, initialCol);
    }

    // Method to set a shape on the game board
    private void putShapeOnBoard(int[][] shape, int row, int col) {
        // Implement logic to set the Tetrimino on the board
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    // Check boundaries and set the Tetrimino on the board
                    if (row + i < board.length && col + j < board[0].length) {
                        board[row + i][col + j] = 1;
                    }
                }
            }
        }
    }

    public void UpdateBoard(int numRows, int numCols) {
        // Alternate between "hello_pic" and "background" for each column
        for (int r = 0; r < numRows; r++) {
            // Iterate through columns
            for (int c = 0; c < numCols; c++) {
                ImageChanger(c, r);
            }

        }
    }

    /*
     menu for colors:
     0 - background
     1 - red
     2 - yellow
     3 - blue
     4 - green
     5 - purple
     */
    public void ImageChanger(int c, int r) {
        imageView = (ImageView) findViewById(getResources().getIdentifier("c" + c + "r" + r, "id", getPackageName()));

        if (board[c][r] == 0) {
            imageView.setImageResource(R.drawable.tetris_background);
        }
        if (board[c][r] == 1) {
            imageView.setImageResource(R.drawable.red_block);
        }
        if (board[c][r] == 2) {
            imageView.setImageResource(R.drawable.yellow_block);
        }
        if (board[c][r] == 3) {
            imageView.setImageResource(R.drawable.blue_block);
        }
        if (board[c][r] == 4) {
            imageView.setImageResource(R.drawable.green_block);
        }
        if (board[c][r] == 5) {
            imageView.setImageResource(R.drawable.purple_block);
        }

    }
}
