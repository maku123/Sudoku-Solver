package com.example.sudokusolver;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SudokuBoard extends View {
    private final Paint brush=new Paint();
    private int dimension;
    private int cell;
    private Solver solver=new Solver();

    public SudokuBoard(Context context) {
        super(context);
    }

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        dimension=Math.min(getMeasuredHeight(),getMeasuredWidth());
        cell=dimension/9;
        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        brush.setAntiAlias(true);

        drawCell(canvas);

        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeWidth(20);
        brush.setColor(Color.BLACK);
        canvas.drawRect(0,0,dimension,dimension,brush);

        drawLine(canvas);

        drawText(canvas);

    }

    private void drawText(Canvas canvas) {

        Paint paint=new Paint();
        paint.setTextSize(cell);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(solver.getBoard()[i][j]!=0){
                    String text=Integer.toString(solver.getBoard()[i][j]);
                    canvas.drawText(text,cell*i+cell/4,cell*j+cell-cell/8,paint);
                }
            }
        }

        paint.setColor(Color.GRAY);
        for(ArrayList<Integer> num:solver.getEmptyBoxIndex()){
            int row= num.get(0);
            int col= num.get(1);
            String text=Integer.toString(solver.getBoard()[row][col]);

            canvas.drawText(text,row*cell+cell/4,col*cell+cell-cell/8,paint);
        }

    }

    private void drawCell(Canvas canvas) {
        if(solver.getSelectedRow()!=-1 && solver.selectedColumn!=-1){
            brush.setStyle(Paint.Style.FILL);
            brush.setColor(Color.LTGRAY);
            canvas.drawRect(solver.getSelectedRow()*cell,solver.getSelectedColumn()*cell
            ,(solver.getSelectedRow()+1)*cell,(solver.getSelectedColumn()+1)*cell,brush);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            solver.setSelectedRow((int) (x/cell));
            solver.setSelectedColumn((int) (y/cell));
        }

        return super.onTouchEvent(event);
    }

    private void drawLine(Canvas canvas) {
        brush.setStyle(Paint.Style.STROKE);
        brush.setColor(Color.BLACK);
        for(int i=1;i<9;i++){
            if(i%3==0){
                brush.setStrokeWidth(8);
            }
            else{
                brush.setStrokeWidth(4);
            }
            canvas.drawLine(cell*i,0,cell*i,dimension,brush);
            canvas.drawLine(0,cell*i,dimension,cell*i,brush);
        }
    }

    public Solver getSolver() {
        return solver;
    }


}
