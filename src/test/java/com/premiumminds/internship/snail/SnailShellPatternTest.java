package com.premiumminds.internship.snail;

import static java.lang.Math.pow;
import static org.junit.Assert.assertArrayEquals;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2023.
 */
@RunWith(JUnit4.class)
public class SnailShellPatternTest {

  //constants for small matrix
  public static final int LINE = 5;                          // Dimension
  public static final int ELEMENTS = (int) pow(LINE, 2);        // Number of elements

  public static final int[][] MATRIX = {
              { 1, 2, 3, 4, 5 },
              { 16, 17, 18, 19, 6 },
              { 15, 24, 25, 20, 7 },
              { 14, 23, 22, 21, 8 },
              { 13, 12, 11, 10, 9 }
  };

  //constants for big matrix
  public static final int LINE_2 = 10000;                      // Dimension
  public static final int ELEMENTS_2 = (int) pow(LINE_2, 2);    // Number of elements


  /**
   * The corresponding implementations to test.
   */
  public SnailShellPatternTest() {
  };

  @Test
  public void ScreenLockinPatternTestFirst3Length2Test()
      throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    assertArrayEquals(result, expected);
  }

  /**
   * Tests the result for oddSized matrix
   */
  @Test
  public void OddSizedMatrix()
    throws InterruptedException, ExecutionException, TimeoutException{

    int val = 0;
    int[] expected = new int[ELEMENTS];
    for (int i = 0; i < ELEMENTS; i++)
      expected[i] = ++val;
    Future<int[]> count = new SnailShellPattern().getSnailShell(MATRIX);
    int[] result = count.get(10, TimeUnit.SECONDS);
    assertArrayEquals(result, expected);
  }

  /**
   * Tests the result for non-square matrix
   */

  @Test
  public void NonSquareMatrixTest()
    throws InterruptedException, ExecutionException, TimeoutException{
    int[][] matrix = {{1, 2}, {3, 4, 5}};
    int[] expected = new int[0];
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    assertArrayEquals(result, expected);
  }


   /**
    * This Test only tests the efficiency.
    * If the other tests pass, means this shall too (algorithmically).
    */
  @Test
  public void BigMatrixTest()
          throws InterruptedException, ExecutionException, TimeoutException{
    int[][] matrix = new int[LINE_2][];

    //initialization of each line
    for (int i = 0; i < LINE_2; i++)
        matrix[i] = new int[LINE_2];

    //inicialization of each position [i][j]
    for (int i = 0; i < LINE_2; i++)
      for (int j = 0; j < LINE_2; j++)
        matrix[i][j] = 1;

    // Initializes and completes expected array
    int[] expected = new int[ELEMENTS_2];
    for (int i = 0; i < ELEMENTS_2; i++)
      expected[i] = 1;

    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    assertArrayEquals(result, expected);
  }

  /**
   * Tests an empty matrix
   */
  @Test
  public void EmptyMatrixTest()
    throws InterruptedException, ExecutionException, TimeoutException{
    int[][] matrix = new int[0][];
    int[] expected = new int[0];
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    assertArrayEquals(result, expected);
  }

  /**
   * Tests the result for a single element matrix
   */
  @Test
  public void SingleElementMatrixTest()
          throws InterruptedException, ExecutionException, TimeoutException{
    int[][] matrix = new int[1][];
    int[] expected = {1};
    matrix[0] = new int[]{1};
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    assertArrayEquals(result, expected);
  }

}