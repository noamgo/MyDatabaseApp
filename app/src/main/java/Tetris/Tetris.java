package Tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Tetris extends View {

    private static final int BLOCK_SIZE = 100;
    private static final int WELL_WIDTH = 10;
    private static final int WELL_HEIGHT = 20;

    private Paint paint;
    private int[][] well;

    public Tetris(Context context) {
        super(context);
        paint = new Paint();
        well = new int[WELL_HEIGHT][WELL_WIDTH];
        // Initialize well array or handle game logic here
    }

    public Tetris(){
        super(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWell(canvas);
    }

    private void drawWell(Canvas canvas) {
        for (int i = 0; i < WELL_HEIGHT; i++) {
            for (int j = 0; j < WELL_WIDTH; j++) {
                drawBlock(canvas, j * BLOCK_SIZE, i * BLOCK_SIZE, well[i][j]);
            }
        }
    }

    private void drawBlock(Canvas canvas, int x, int y, int color) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColorForBlock(color));
        canvas.drawRect(x, y, x + BLOCK_SIZE, y + BLOCK_SIZE, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(x, y, x + BLOCK_SIZE, y + BLOCK_SIZE, paint);
    }

    private int getColorForBlock(int color) {
        // Implement logic to map color codes to actual colors
        switch (color) {
            // Add cases for different colors or use a Colors enum as shown in previous examples
            default:
                return Color.GRAY;
        }
    }
}