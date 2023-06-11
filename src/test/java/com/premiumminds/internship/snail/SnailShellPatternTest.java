package com.premiumminds.internship.snail;

import static org.junit.Assert.assertArrayEquals;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2023.
 */
@RunWith(JUnit4.class)
public class SnailShellPatternTest {

    /**
     * The corresponding implementations to test.
     * <p>
     * If you want, you can make others :)
     */
    public SnailShellPatternTest() {
    }

    @Test
    public void ScreenLockinPatternTestFirst3Length2Test()
            throws InterruptedException, ExecutionException, TimeoutException {
        int[][] matrix = {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
        int[] result = count.get(10, TimeUnit.SECONDS);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(result, expected);
    }

    // Test 4x4 matrix
    @Test
    public void ScreenLockinPatternTestFirst4Length2Test()
            throws InterruptedException, ExecutionException, TimeoutException {
        int[][] matrix = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}};
        Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
        int[] result = count.get(10, TimeUnit.SECONDS);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        assertArrayEquals(result, expected);
    }

    // Test for empty matrix
    @Test
    public void ScreenLockinPatternTestFirst0Length2Test()
            throws InterruptedException, ExecutionException, TimeoutException {
        int[][] matrix = {};
        Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
        int[] result = count.get(10, TimeUnit.SECONDS);
        int[] expected = {};
        assertArrayEquals(result, expected);
    }

    // Test speed but not correctness of the algorithm
    @Test
    public void BigTest()
            throws InterruptedException, ExecutionException {
        int n = 22_000;
        int[][] matrix = new int[n][n];

        Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
        count.get();
    }

}