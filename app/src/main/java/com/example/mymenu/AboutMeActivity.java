package com.example.mymenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AboutMeActivity extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    int[][] board;
    Button btnDown, btnLeft, btnRight, btnRotate;
    int colorIdCount;

    // store the location of current shape
    int currentShapeRow = 0;
    int currentShapeCol = 0;

    private Handler handler = new Handler();
    private final int DELAY_MS = 700; // 0.7 second delay


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


        btnDown = findViewById(R.id.btnDown);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveDown();
            }
        });

        btnLeft = findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveLeft();

            }
        });

        btnRight = findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveRight();
            }
        });

        btnRotate = findViewById(R.id.btnRotate);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateShape();
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

    private boolean isCollisionDown(int[][] shape, int col, int row) {
        int lowestRow = shape.length - 1;
        for (int j = 0; j < shape[0].length; j++) {
            // If the current cell of the shape is filled
            if (shape[lowestRow][j] != 0) {
                // Check if the cell below is out of bounds or occupied
                if (row + lowestRow + 1 >= board.length || board[row + lowestRow + 1][col + j] != 0) {
                    return true; // Collision downwards detected
                }
            }
        }

        return false; // No collision downwards
    }

    private boolean isCollisionRight(int[][] shape, int col, int row) {
        int rightestCol = shape[0].length - 1;
        for (int i = 0; i < shape.length; i++) {
            // If the current cell of the shape is filled
            if (shape[i][rightestCol] != 0) {
                // Check if the cell below is out of bounds or occupied
                if (col + rightestCol + 1 >= board[0].length || board[row + i][col + rightestCol + 1] != 0) {
                    return true; // Collision downwards detected
                }
            }
        }

        return false;
    }

    private boolean isCollisionLeft(int[][] shape, int col, int row) {
        for (int i = 0; i < shape.length; i++) {
            // If the current cell of the shape is filled
            if (shape[i][0] != 0) {
                // Check if the cell below is out of bounds or occupied
                if (col - 1 < 0 || board[row + i][col - 1] != 0) {
                    return true; // Collision downwards detected
                }
            }
        }
        return false;
    }

    //check all sides at once
    private boolean isCollision(int[][] shape, int col, int row) {
        if (isCollisionDown(shape, col, row) || isCollisionRight(shape, col, row) || isCollisionLeft(shape, col, row)) {
            return true;
        }
        return false;
    }

    private void moveDown() {
        if (!isCollisionDown(TetrisShapes.getCurrentShape(), currentShapeCol, currentShapeRow)) {
            clearCurrentShape(); // Clear the current shape from its previous position

            // Iterate over the shape's cells and move each cell down if the cell below is empty
            for (int i = TetrisShapes.getCurrentShape().length - 1; i >= 0; i--) {
                for (int j = 0; j < TetrisShapes.getCurrentShape()[0].length; j++) {
                    if (TetrisShapes.getCurrentShape()[i][j] != 0) {
                        // Remove the condition for board bounds check if you're sure it won't go out of bounds
                        board[currentShapeRow + i + 1][currentShapeCol + j] = board[currentShapeRow + i][currentShapeCol + j];
                        board[currentShapeRow + i][currentShapeCol + j] = 0;
                    }
                }
            }

            // Update the current shape position
            currentShapeRow++;

            putShapeOnBoard(TetrisShapes.getCurrentShape(), currentShapeRow, currentShapeCol); // Put the shape in its new position
            UpdateBoard(); // Update the board graphics
        } else {
            // Current shape has stopped, create a new shape
            createShape(board.length, board[0].length);
            currentShapeRow = 0;
            currentShapeCol = board[0].length / 2 - TetrisShapes.getCurrentShape()[0].length / 2;
            putShapeOnBoard(TetrisShapes.getCurrentShape(), currentShapeRow, currentShapeCol);
        }
    }

    private void moveLeft() {
        if (!isCollisionLeft(TetrisShapes.getCurrentShape(), currentShapeCol, currentShapeRow)) {
            clearCurrentShape(); // Clear the current shape from its previous position

            // Iterate over the shape's cells and move each cell to the left if the cell to the left is empty
            for (int i = 0; i < TetrisShapes.getCurrentShape().length; i++) {
                for (int j = 0; j < TetrisShapes.getCurrentShape()[0].length; j++) {
                    if (TetrisShapes.getCurrentShape()[i][j] != 0) {
                        board[currentShapeRow + i][currentShapeCol + j - 1] = board[currentShapeRow + i][currentShapeCol + j];
                        board[currentShapeRow + i][currentShapeCol + j] = 0;
                    }
                }
            }

            // Update the current shape position
            currentShapeCol--;

            putShapeOnBoard(TetrisShapes.getCurrentShape(), currentShapeRow, currentShapeCol); // Put the shape in its new position
            UpdateBoard(); // Update the board graphics
        }
    }

    private void moveRight() {
        if (!isCollisionRight(TetrisShapes.getCurrentShape(), currentShapeCol, currentShapeRow)) {
            clearCurrentShape(); // Clear the current shape from its previous position

            // Iterate over the shape's cells and move each cell to the right if the cell to the right is empty
            for (int i = 0; i < TetrisShapes.getCurrentShape().length; i++) {
                for (int j = TetrisShapes.getCurrentShape()[0].length - 1; j >= 0; j--) {
                    if (TetrisShapes.getCurrentShape()[i][j] != 0) {
                        board[currentShapeRow + i][currentShapeCol + j + 1] = board[currentShapeRow + i][currentShapeCol + j];
                        board[currentShapeRow + i][currentShapeCol + j] = 0;
                    }
                }
            }

            // Update the current shape position
            currentShapeCol++;

            putShapeOnBoard(TetrisShapes.getCurrentShape(), currentShapeRow, currentShapeCol); // Put the shape in its new position
            UpdateBoard(); // Update the board graphics
        }
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

    // Rotate the current shape clockwise
    private void rotateShape() {
        if (!isCollision(TetrisShapes.getCurrentShape(), currentShapeCol, currentShapeRow)) {
            // Clear the current shape from its previous position
            clearCurrentShape();

            // Get the current shape and its dimensions
            int[][] currentShape = TetrisShapes.getCurrentShape();
            int rows = currentShape.length;
            int cols = currentShape[0].length;

            // Create a new array for the rotated shape
            int[][] rotatedShape = new int[cols][rows];


            // Rotate the shape clockwise
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    rotatedShape[j][rows - 1 - i] = currentShape[i][j];
                }
            }


            // Update the current shape with the rotated shape
            TetrisShapes.setCurrentShape(rotatedShape);

            // Put the rotated shape on the board
            putShapeOnBoard(rotatedShape, currentShapeRow, currentShapeCol);

            // Update the board graphics
            UpdateBoard();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAutoMoveDown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAutoMoveDown();
    }

    private void startAutoMoveDown() {
        handler.postDelayed(autoMoveDownRunnable, DELAY_MS);
    }

    private void stopAutoMoveDown() {
        handler.removeCallbacks(autoMoveDownRunnable);
    }

    private Runnable autoMoveDownRunnable = new Runnable() {
        @Override
        public void run() {
            moveDown();
            handler.postDelayed(this, DELAY_MS); // Schedule the next move after delay
        }
    };
}
