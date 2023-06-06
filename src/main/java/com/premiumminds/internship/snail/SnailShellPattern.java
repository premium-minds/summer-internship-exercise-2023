package com.premiumminds.internship.snail;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<int[]> future = executorService.submit(() -> {
      List<Integer> result = new ArrayList<Integer>();

      int line, col, begin, end, size;
      line = 0;
      begin = 0;
      col = 0;
      end = matrix.length - 1;
      size = matrix.length;

      while (begin < size && end >= begin && begin != end){
        while (col >= begin && line == begin && col <= end){
          result.add(matrix[line][col]);
          line++;
        }

        col--;

        while (col >= begin && line == end){
          result.add(matrix[line][col]);
          col--;
        }

        line--;

        while (line > begin && col == begin){
          result.add(matrix[line][col]);
          line--;
        }

        begin++;
        end--;
      }




      int[] resultArray = new int[size];
        for (int i = 0; i < size; i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;

    });

    executorService.shutdown();
    return future;
  };
}
