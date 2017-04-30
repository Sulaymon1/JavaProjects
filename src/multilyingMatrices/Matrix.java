package multilyingMatrices;

public class Matrix extends  Thread{
    private int[][] firstMatrix;
    private int[][] secondMatrix;
    private int[][] resultMatrix;
    private int firstIndex;
    private int lastIncdex;


    public Matrix (int[][] firstMatrix,
                   int[][] secondMatrix,
                   int[][] resultMatrix,
                   int firstIndex,
                   int lastIncdex){
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.firstIndex = firstIndex;
        this.lastIncdex = lastIncdex;
    }

    public void calcValue ( int row , int column){
        for (int i = 0; i < secondMatrix[0].length; i++)
            resultMatrix[row][column] += firstMatrix[row][i] * secondMatrix[i][column];
    }


    @Override
    public void run (){
        int colCount = secondMatrix[0].length;
        for (int i = firstIndex; i < lastIncdex; i++)
            calcValue(i/colCount, i%colCount);
        System.out.println(getName() + " finished!");
    }
}
