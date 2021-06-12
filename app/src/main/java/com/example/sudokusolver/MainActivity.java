package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private SudokuBoard sudokuBoard;
    private Solver solver;
    private Button solveBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudokuBoard=findViewById(R.id.SudokuBoard);
        solver=sudokuBoard.getSolver();
        solveBTN=findViewById(R.id.solve);
    }

    public void BTNOne(View view) {
        solver.setNumBoard(1);
        sudokuBoard.invalidate();
    }

    public void BTNTwo(View view) {
        solver.setNumBoard(2);
        sudokuBoard.invalidate();
    }

    public void BTNThree(View view) {
        solver.setNumBoard(3);
        sudokuBoard.invalidate();
    }

    public void BTNFour(View view) {
        solver.setNumBoard(4);
        sudokuBoard.invalidate();
    }

    public void BTNFive(View view) {
        solver.setNumBoard(5);
        sudokuBoard.invalidate();
    }

    public void BTNSix(View view) {
        solver.setNumBoard(6);
        sudokuBoard.invalidate();
    }

    public void BTNSeven(View view) {
        solver.setNumBoard(7);
        sudokuBoard.invalidate();
    }

    public void BTNEight(View view) {
        solver.setNumBoard(8);
        sudokuBoard.invalidate();
    }

    public void BTNNine(View view) {
        solver.setNumBoard(9);
        sudokuBoard.invalidate();
    }

    public void solve(View view) {
        if(solveBTN.getText().toString().equals("solve")){
            solveBTN.setText(R.string.reset);
            solver.getEmptyBoxIndexes();


            SolveBoardThread solveBoardThread= new SolveBoardThread();
            new Thread(solveBoardThread).start();

            sudokuBoard.invalidate();

        }
        else{
            solveBTN.setText(R.string.solve);
            solver.reset();
        }

    }

    class SolveBoardThread implements Runnable{

        @Override
        public void run() {
            solver.solveBoard(sudokuBoard);
        }
    }

    public void BTNClear(View view) {
        solver.setNumBoard(0);
        sudokuBoard.invalidate();
    }
}