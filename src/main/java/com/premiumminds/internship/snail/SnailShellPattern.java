package com.premiumminds.internship.snail;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 *
 * Last edited by josedsilva20 on 06-08-2023
 */
class SnailShellPattern implements ISnailShellPattern {

  // Declared here to use them in all methods
  // (instead of a big method with all variables inside)
  private int line, col, begin = 0;
  private int end, size;
  private int[][] matrix;
  private List<Integer> result = new ArrayList<>();


  /**
   * Method to get snailshell pattern
   * 
   * @param startMatrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] startMatrix) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<int[]> future = executorService.submit(() -> {

      // Link private matrix to the original one
      matrix = startMatrix;

      // checks if is an empty matrix
      if (matrix == null || matrix.length == 0) {
        return new int[0];
      }

      //checks if is a non-square matrix
      for (int[] ints : matrix)
        if (ints.length < matrix.length)
          return new int[0];

      end = matrix.length - 1;
      size = matrix.length;

      // Makes a full turn
      while (begin < size && end >= begin && begin != end){
        addFirstLine();

        line++; // Jump to next line
        col--;  // Reestablish column number to n - 1

        addLastColumn();

        col--;  // Advance to middle columns
        line--; // Reestablish line number to n - 1

        addLastLine();

        col++;  // Go to first column
        line--; // Go up in snail

        addFirstColumn();

        // Change to (n-1)x(n-1) Matrix
        begin++;
        end--;
        line++;
        col++;
      }

      // Add the element in the middle of matrix
      if (begin == end)
        result.add(matrix[begin][end]);

      // Prepare and copy the returnable array
      int[] resultArray = new int[result.size()];
      for (int i = 0; i < result.size(); i++) {
        resultArray[i] = result.get(i);
      }

      return resultArray;

    });

    executorService.shutdown();
    return future;
  };

  public void addFirstLine(){
    while (col >= begin && line == begin && col <= end){
      result.add(matrix[line][col]);
      col++;
    }
  }

  public void addLastColumn(){
    while (col == end && line <= end){
      result.add(matrix[line][col]);
      line++;
    }
  }

  public void addLastLine(){
    while (col >= begin && line == end){
      result.add(matrix[line][col]);
      col--;
    }
  }

  public void addFirstColumn(){
    while (line > begin && col == begin){
      result.add(matrix[line][col]);
      line--;
    }
  }
}
