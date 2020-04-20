package implementations;

import java.util.ArrayDeque;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {

        solveWithDFSRecursion(this.startRow,this.startCol);

    }

    private void solveWithBFS(){
        ArrayDeque<int[]> coordinates = new ArrayDeque<>();

        coordinates.offer(new int[]{this.startRow,this.startCol});

        while (!coordinates.isEmpty()){

            int[] rowAndCol = coordinates.poll();
            int row= rowAndCol[0];
            int col = rowAndCol[1];

            this.matrix[row][col] = this.fillChar;

            if(row+1<this.matrix.length && this.matrix[row+1][col] == toBeReplaced){
                coordinates.offer(new int[]{row+1,col});
            }
            if(col+1<this.matrix[row].length && this.matrix[row][col+1] == toBeReplaced){
                coordinates.offer(new int[]{row,col+1});
            }
            if(row-1>=0 && this.matrix[row-1][col] == toBeReplaced){
                coordinates.offer(new int[]{row-1,col});
            }
            if(col-1>=0 && this.matrix[row][col-1] == toBeReplaced){
                coordinates.offer(new int[]{row,col-1});
            }

        }
    }

    private void solveWithDFSRecursion(int row, int col){

        if(row<0 || row>=this.matrix.length ||col<0 || col>=this.matrix[row].length || this.matrix[row][col] != toBeReplaced){
            return;
        }

        this.matrix[row][col] = fillChar;

        solveWithDFSRecursion(row+1,col);
        solveWithDFSRecursion(row,col+1);
        solveWithDFSRecursion(row-1,col);
        solveWithDFSRecursion(row,col-1);

    }

    public String toOutputString() {

        StringBuilder sb = new StringBuilder();

        for (int row = 0; row <this.matrix.length ; row++) {

            for (int col = 0; col <this.matrix[row].length ; col++) {

                sb.append(this.matrix[row][col]);

            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
