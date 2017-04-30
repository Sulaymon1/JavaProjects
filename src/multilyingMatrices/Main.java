package multilyingMatrices;

import java.util.Random;

public class Main {
    private static int[][] multiplyMatrix(final int[][] firstMatrix,
                                          final int[][] secondMatrix,
                                          int threadCount){
        assert threadCount > 0;

        final int rowCount = firstMatrix.length;             // Число строк результирующей матрицы.
        final int colCount = secondMatrix[0].length;         // Число столбцов результирующей матрицы.
        final int[][] result = new int[rowCount][colCount];  // Результирующая матрица.

        final int cellsForThread = (rowCount * colCount) / threadCount;  // Число вычисляемых ячеек на поток.
        int firstIndex = 0;  // Индекс первой вычисляемой ячейки.
        final Matrix[] matrix = new Matrix[threadCount];  // Массив потоков.

        // Создание и запуск потоков.
        for (int i = threadCount - 1; i >= 0; --i) {
            int lastIndex = firstIndex + cellsForThread;  // Индекс последней вычисляемой ячейки.
            if (i == 0)
                lastIndex = rowCount * colCount;
            matrix[i] = new Matrix(firstMatrix, secondMatrix, result, firstIndex, lastIndex);
            matrix[i].start();
            firstIndex = lastIndex;
        }

        // Ожидание завершения потоков.
        try {
            for (final Matrix matrices : matrix)
                matrices.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }


    final static int FIRST_MATRIX_ROWS  = 4;
    final static int FIRST_MATRIX_COLS  = 5;
    final static int SECOND_MATRIX_ROWS = FIRST_MATRIX_COLS;
    final static int SECOND_MATRIX_COLS = 3;

    public static void main(String[] args)
    {
        final int[][] firstMatrix  = new int[FIRST_MATRIX_ROWS][FIRST_MATRIX_COLS];
        final int[][] secondMatrix = new int[SECOND_MATRIX_ROWS][SECOND_MATRIX_COLS];

        randomMatrix(firstMatrix);
        randomMatrix(secondMatrix);

        printMatrix(firstMatrix);
        System.out.println();
        printMatrix(secondMatrix);

        int[][] result = multiplyMatrix(firstMatrix, secondMatrix, Runtime.getRuntime().availableProcessors());
        printMatrix(result);

    }

    public static void printMatrix(int[][] result){
        for (int i = 0; i < result.length; i++ ){
            for (int j = 0; j < result[0].length ; j++)
                System.out.printf("%d ",result[i][j]);
            System.out.println();
        }
    }

    private static void randomMatrix(int[][] matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.length ; i++)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[i][j] = random.nextInt(10);
    }


}
