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
        import java.util.Random;

        import Tetris.Tetris;

        public class AboutMeActivity extends AppCompatActivity {

            Intent intent;
            ImageView imageView;
            int[][] board;
            Button back;
            int colorIdCount;

            // store the location of current shape
            int currentShapeRow = 0;
            int currentShapeCol = 0;

            @Override
            protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.about_me_screen);

                // Assuming you have a GridLayout defined in your layout XML file with id gridLayout
                GridLayout gridLayout = findViewById(R.id.gridLayout);

                // Define the number of rows and columns
                int numRows = 20;
                int numCols = 10;

                colorIdCount = 1;

                board = new int[numRows][numCols];

                // Initialize the game board
                initializeGameBoard();

                createShape(numRows, numCols);
                UpdateBoard();


                back = findViewById(R.id.btnBack);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!isColisionDown(TetrisShapes.getCurrentShape(), currentShapeCol, currentShapeRow + 2)) {
                            moveDown();
                        }
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

                //set random color for shape
                selectedShape = changeToRandomColor(selectedShape);

                putShapeOnBoard(selectedShape, initialRow, initialCol);
            }

            private int[][] changeToRandomColor(int[][] shape) {
                for (int i = 0; i < shape.length; i++) {
                    for (int j = 0; j < shape[0].length; j++) {
                        if (shape[i][j] == 1) {
                            shape[i][j] = colorIdCount;
                        }
                    }
                }
                updateColorIdCount();
                return shape;
            }


            private void updateColorIdCount() {
                colorIdCount++;
                if (colorIdCount > 5) {
                    colorIdCount = 1;
                }
            }

            // Method to set a shape on the game board
            private void putShapeOnBoard(int[][] shape, int row, int col) {
                // Store the position of the current shape
                currentShapeRow = row;
                currentShapeCol = col;

                // Implement logic to set the Tetrimino on the board
                for (int i = 0; i < shape.length; i++) {
                    for (int j = 0; j < shape[0].length; j++) {
                        if (shape[i][j] != 0) {
                            // Check boundaries and set the Tetrimino on the board
                            if (row + i < board.length && col + j < board[0].length) {
                                board[row + i][col + j] = shape[i][j];
                            }
                        }
                    }
                }

                // Update the board graphics
                UpdateBoard();
            }

            private void UpdateBoard() {
                // Alternate between "hello_pic" and "background" for each column
                for (int row = 0; row < board.length; row++) {
                    // Iterate through columns
                    for (int col = 0; col < board[0].length; col++) {
                        ImageChanger(row, col);
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
            private void ImageChanger(int r, int c) {
                // Get the ID of the ImageView based on row and column indices
                int imageViewId = getResources().getIdentifier("c" + c + "r" + r, "id", getPackageName());
                ImageView imageView = findViewById(imageViewId);

                if (imageView != null) {
                    // Set the image resource based on the value in the board array
                    switch (board[r][c]) {
                        case 0:
                            imageView.setImageResource(R.drawable.tetris_background);
                            break;
                        case 1:
                            imageView.setImageResource(R.drawable.red_block);
                            break;
                        case 2:
                            imageView.setImageResource(R.drawable.yellow_block);
                            break;
                        case 3:
                            imageView.setImageResource(R.drawable.blue_block);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.green_block);
                            break;
                        case 5:
                            imageView.setImageResource(R.drawable.purple_block);
                            break;
                    }
                } else {
                    Log.e("ImageChanger", "ImageView not found for row " + r + " and column " + c);
                }
            }

            private boolean isColisionDown(int[][] shape, int col, int row) {
                for (int i = 0; i < shape.length; i++) {
                    for (int j = 0; j < shape[0].length; j++) {
                        // If the current cell of the shape is filled
                        if (shape[i][j] != 0) {
                            // Check if the cell below is out of bounds or occupied
                            if (row + i + 1 >= board.length || board[row + i + 1][col + j] != 0) {
                                return true; // Collision downwards detected
                            }
                        }
                    }
                }
                return false; // No collision downwards
            }

            private boolean isColisionRight(int[][] shape, int col, int row) {
                for (int i = 0; i < shape.length; i++) {
                    for (int j = 0; j < shape[0].length; j++) {
                        // If the current cell of the shape is filled
                        if (shape[i][j] != 0) {
                            // Check if the cell below is out of bounds or occupied
                            if (col + i + 1 >= board[0].length || board[row + i][col + j + 1] != 0) {
                                return true; // Collision downwards detected
                            }
                        }
                    }
                }
                return false;
            }

            private boolean isColisionLeft(int[][] shape, int col, int row) {
                for (int i = 0; i < shape.length; i++) {
                    for (int j = 0; j < shape[0].length; j++) {
                        // If the current cell of the shape is filled
                        if (shape[i][j] != 0) {
                            // Check if the cell below is out of bounds or occupied
                            if (col + i - 1 >= board[0].length || board[row + i][col + j - 1] != 0) {
                                return true; // Collision downwards detected
                            }
                        }
                    }
                }
                return false;
            }

            //check all sides at once
            private boolean isColision(int[][] shape, int col, int row) {
                if (isColisionDown(shape, col, row) || isColisionRight(shape, col, row) || isColisionLeft(shape, col, row)) {
                    return true;
                }
                return false;
            }

            private void moveDown() {
                clearCurrentShape(); // Clear the current shape from its previous position

                // Start the loop from the second-to-last row to allow movement to the last row
                for (int row = board.length - 1; row >= 1; row--) {
                    for (int col = 0; col < board[0].length; col++) {
                        if (board[row][col] != 0) {
                            // Move the shape down if the cell below is empty
                            if (board[row + 1][col] == 0) {
                                board[row + 1][col] = board[row][col];
                                board[row][col] = 0;
                            }
                        }
                    }
                }

                // Update the current shape position
                currentShapeRow++;

                putShapeOnBoard(TetrisShapes.getCurrentShape(), currentShapeRow, currentShapeCol); // Put the shape in its new position
                UpdateBoard(); // Update the board graphics
            }

            private void clearCurrentShape() {
                // Clear the current shape from its current position on the board
                for (int i = 0; i < TetrisShapes.getCurrentShape().length; i++) {
                    for (int j = 0; j < TetrisShapes.getCurrentShape()[0].length; j++) {
                        if (TetrisShapes.getCurrentShape()[i][j] != 0) {
                            board[currentShapeRow + i][currentShapeCol + j] = 0;
                        }
                    }
                }
            }
        }
