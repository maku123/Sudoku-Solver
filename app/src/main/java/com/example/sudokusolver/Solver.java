package com.example.sudokusolver;

import android.os.CountDownTimer;

import java.util.ArrayList;

public class Solver {
    int selectedRow,selectedColumn;
    int[][] board;
    ArrayList<ArrayList<Integer>> emptyBoxIndex;
    
    public Solver() {
        selectedRow=-1;
        selectedColumn=-1;
        board=new int[9][9];
        
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                board[i][j]=0;
            }
        }

        emptyBoxIndex=new ArrayList<>();
    }

    public int[][] getBoard() {
        return board;
    }

    public void reset(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                board[i][j]=0;
            }
        }
        emptyBoxIndex=new ArrayList<>();
    }

    public boolean check(int r,int c){
        for(int i=0;i<9;i++){
            if(this.board[i][c]==this.board[r][c] && i!=r)return false;
            if(this.board[r][i]==this.board[r][c] && i!=c)return false;
        }
        for(int i=(r/3)*3;i<(r/3)*3+3;i++){
            for(int j=(c/3)*3;j<(c/3)*3+3;j++){
                if(this.board[i][j]==this.board[r][c] && i!=r && j!=c)return false;
            }
        }
        return true;
    }


    public boolean solveBoard(SudokuBoard display){
        int r=-1,c=-1;
        for(int i=0;i<9;i++){
            int f=0;
            for(int j=0;j<9;j++){
                if(this.board[i][j]==0){
                    f=1;
                    r=i;
                    c=j;
                    break;
                }
            }
            if(f==1)break;
        }
        if(r==-1 && c==-1)return true;

        for(int i=1;i<10;i++){
            this.board[r][c]=i;
            display.invalidate();

            if(check(r,c)){
                if(solveBoard(display)){
                    return true;
                }
            }
            this.board[r][c]=0;
            display.invalidate();
        }
        return false;
    }

    public void getEmptyBoxIndexes(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if (this.board[i][j]==0){
                    emptyBoxIndex.add(new ArrayList<>());
                    emptyBoxIndex.get(this.emptyBoxIndex.size()-1).add(i);
                    emptyBoxIndex.get(this.emptyBoxIndex.size()-1).add(j);
                }
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getEmptyBoxIndex() {
        return emptyBoxIndex;
    }

    public void setNumBoard(int num){
        if(this.selectedColumn!=-1 && this.selectedRow!=-1){
            this.board[this.selectedRow][this.selectedColumn]=num;
        }
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }
}
