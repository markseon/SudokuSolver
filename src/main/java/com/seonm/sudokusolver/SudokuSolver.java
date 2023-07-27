/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.seonm.sudokusolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mark Seon
 */
public class SudokuSolver {

    private int[][] grid;
    public final int GRID_SIZE;
    static List<Cell> graph;


    public SudokuSolver() {
        GRID_SIZE = 9;
    }

    private boolean isSafe(int row, int col, int num) {
        // Check row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // Check subgrid
        int subgridRow = row - row % 3;
        int subgridCol = col - col % 3;

        for (int i = subgridRow; i < subgridRow + 3; i++) {
            for (int j = subgridCol; j < subgridCol + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private void initialiseGraph() {
        graph = new ArrayList<>();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grid[row][col] == 0) {
                    Cell cell = new Cell(row, col);
                    
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isSafe(row, col, num)) {
                            cell.getCandidates().add(num);
                        }
                    }
                    graph.add(cell);
                }
            }
        }
    }

    private boolean solve() {
        if (graph.isEmpty()) {
            return true;
        }

        Collections.sort(graph);
        Cell cell = graph.get(0);
        int row = cell.getRow();
        int col = cell.getCol();

        for (int i = 0; i < cell.getCandidates().size(); i++) {
            int num = cell.getCandidates().get(i);
            if (isSafe(row, col, num)) {
                grid[row][col] = num;
                graph.remove(cell);
                if (solve()) {
                    return true;
                }
                graph.add(cell);
                Collections.sort(graph);
                grid[row][col] = 0;
            }
        }

        return false;
    }

    public int[][] solveGrid(int[][] aGrid) {
        grid = Arrays.stream(aGrid).map(a -> Arrays.copyOf(a, a.length)).toArray(int[][] :: new);
        initialiseGraph();
        if (solve()) {
            return grid;
        }
        return aGrid;
    }

    private class Cell implements Comparable<Cell> {

        private int row, col;
        private List<Integer> candidates;

        private Cell(int row, int col) {
            this.row = row;
            this.col = col;
            candidates = new ArrayList<>();
        }

        @Override
        public int compareTo(Cell otherCell) {
            return candidates.size() - otherCell.getCandidates().size();
        }

        private int getRow() {
            return row;
        }

        private int getCol() {
            return col;
        }

        private List<Integer> getCandidates() {
            return candidates;
        }
    }

}
