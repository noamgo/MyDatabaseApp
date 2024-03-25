package com.example.mymenu;

import java.util.Arrays;

public class TetrisShapes {
    // store the location of current shape
    private static int[][] currentShape;


    // Tetris shapes
    public static final int[][] SHAPE_I = {
            {1, 1, 1, 1}
    };

    public static final int[][] SHAPE_O = {
            {1, 1},
            {1, 1}
    };

    public static final int[][] SHAPE_T = {
            {0, 1, 0},
            {1, 1, 1}
    };

    public static final int[][] SHAPE_S = {
            {0, 1, 1},
            {1, 1, 0}
    };

    public static final int[][] SHAPE_Z = {
            {1, 1, 0},
            {0, 1, 1}
    };

    public static final int[][] SHAPE_J = {
            {1, 0, 0},
            {1, 1, 1}
    };

    public static final int[][] SHAPE_L = {
            {0, 0, 1},
            {1, 1, 1}
    };

    // function that return random shape
    // function that return random shape
    public static int[][] getRandomShape() {
        int random = (int) (Math.random() * 6);
        if (random == 0) {
            currentShape = SHAPE_I;
        } else if (random == 1) {
            currentShape = SHAPE_O;
        } else if (random == 2) {
            currentShape = SHAPE_T;
        } else if (random == 3) {
            currentShape = SHAPE_S;
        } else if (random == 4) {
            currentShape = SHAPE_Z;
        } else if (random == 5) {
            currentShape = SHAPE_J;
        } else {
            currentShape = SHAPE_L;
        }
        return currentShape;
    }

    public static int[][] getCurrentShape() {
        return currentShape;
    }

    public static void setCurrentShape(int[][] shape) {
        currentShape = shape;
    }


}

