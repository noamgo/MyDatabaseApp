package com.example.mymenu;

public class TetrisShapes {

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
    public static int[][] getRandomShape() {
        int random = (int) (Math.random() * 6);
        if (random == 0) {
            return SHAPE_I;
        } else if (random == 1) {
            return SHAPE_O;
        } else if (random == 2) {
            return SHAPE_T;
        } else if (random == 3) {
            return SHAPE_S;
        } else if (random == 4) {
            return SHAPE_Z;
        } else if (random == 5) {
            return SHAPE_J;
        }
        return SHAPE_L;
    }
}

