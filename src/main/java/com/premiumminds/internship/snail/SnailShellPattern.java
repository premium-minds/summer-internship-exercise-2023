package com.premiumminds.internship.snail;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


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
    public Future<int[]> getSnailShell(final int[][] matrix) {
        // Initialize new executor with 1 thread to submit all other callables
        final ExecutorService initExecutor = Executors.newSingleThreadExecutor();

        // Initialize new executor with 4 threads or the number of processors available
        final ExecutorService executor = Executors.newFixedThreadPool(Math.min(Runtime.getRuntime().availableProcessors(), 4));

        // Submit a new callable that submits all other callables

        return initExecutor.submit(new Callable<int[]>() {
            @Override
            public int[] call() throws Exception {

                // get matrix length
                final int len = matrix.length;
                // initialize sorted array with size N*N
                final int[] sorted = new int[len * len];

                // Half of the length of the matrix
                final int halfLen = (int) Math.floor((len + 1) / 2.0);

                // List to keep track of all the callables
                List<Callable<int[]>> matrixParts = new ArrayList<>();

                // Horizontal right rows
                matrixParts.add(new Callable<int[]>() {
                    @Override
                    public int[] call() {
                        if (len == 0) return sorted;

                        int copyLen = len;
                        int destPos = 0;
                        // for each row
                        for (int i = 0; i < halfLen; i++) {  // for every row until half of the matrix

                            // copy horizontal right
                            System.arraycopy(matrix[i], i, sorted, destPos, copyLen);
                            destPos += copyLen * 4 - 4;
                            copyLen -= 2;
                        }
                        return sorted;
                    }
                });

                // Vertical down columns
                matrixParts.add(new Callable<int[]>() {
                    @Override
                    public int[] call() {
                        if (len == 0) return sorted;

                        int copyLen = len;
                        int destPos = len;
                        // for each column
                        for (int i = 0; i < halfLen; i++) {
                            copyLen -= 2;
                            // copy vertical down
                            for (int j = i + 1; j <= copyLen + i; j++) {
                                sorted[destPos] = matrix[j][len - 1 - i];
                                destPos += 1;
                            }
                            destPos += copyLen * 3 + 2; // One small copy len already accounted for
                        }

                        return sorted;
                    }
                });

                // Horizontal left rows
                matrixParts.add(new Callable<int[]>() {
                    @Override
                    public int[] call() {
                        if (len == 0) return sorted;

                        int copyLen = len;
                        int destPos = len * 2 - 2;
                        // for each row
                        for (int i = 0; i < halfLen; i++) {
                            // copy horizontal left
                            for (int j = i; j < copyLen + i; j++) {
                                sorted[destPos] = matrix[len - 1 - i][len - 1 - j];
                                destPos += 1;
                            }
                            destPos += copyLen * 3 - 8;
                            copyLen -= 2;
                        }


                        return sorted;
                    }
                });

                // Vertical up colums
                matrixParts.add(new Callable<int[]>() {
                                    @Override
                                    public int[] call() {
                                        if (len == 0) return sorted;

                                        int copyLen = len;
                                        int destPos = len * 3 - 2;
                                        // for each column
                                        for (int i = 0; i < halfLen; i++) {
                                            copyLen -= 2;
                                            // copy vertical up
                                            for (int j = i + 1; j <= copyLen + i; j++) {
                                                sorted[destPos] = matrix[len - 1 - j][i];
                                                destPos += 1;
                                            }
                                            destPos += copyLen * 3 - 2;
                                        }

                                        return sorted;
                                    }


                                }
                );

                executor.invokeAll(matrixParts);  // Wait for all callables to finish
                return sorted;
            }
        });

    }

}
