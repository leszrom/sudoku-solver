package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuBoard extends Prototype {
    private static final int SUDOKU_FIRST_ROW = 1;
    private static final int SUDOKU_LAST_ROW = 9;
    private static final int SUDOKU_FIRST_COLUMN = 1;
    private static final int SUDOKU_LAST_COLUMN = 9;

    private List<SudokuRow> rows = new ArrayList<>(SUDOKU_LAST_ROW);

    public SudokuBoard() {
        for (int n = SUDOKU_FIRST_ROW; n <= SUDOKU_LAST_ROW; n++) {
            rows.add(new SudokuRow(n));
        }
    }

    public List<SudokuRow> getRows() {
        return rows;
    }

    public boolean insertValue(int row, int column, int value) {
        SudokuElement element;
        if (SUDOKU_FIRST_COLUMN <= column && column <= SUDOKU_LAST_COLUMN) {
            if (SUDOKU_FIRST_ROW <= row && row <= SUDOKU_LAST_ROW) {
                element = rows.get(row - 1).getElements().get(column - 1);
                if (element.getPossibleValues().contains(value)) {
                    element.setValue(value);
                    element.getPossibleValues().removeAll(element.getPossibleValues());
                    updatePossibleValues(row, column, value);
                    return true;
                }
            }
        }
        System.out.println(UserInterface.CANT_INSERT + value);
        return false;
    }

    public void updatePossibleValues(int row, int column, int value) {
        int block = SudokuBlock.getBlockNumber(row, column);

        getRows().stream()
                .flatMap(sudokuRow -> sudokuRow.getElements().stream())
                .filter(sudokuElement -> sudokuElement.getColumnNumber() == column)
                .forEach(sudokuElement -> sudokuElement.deleteFromPossibleValues(value));

        getRows().stream()
                .flatMap(sudokuRow -> sudokuRow.getElements().stream())
                .filter(sudokuElement -> sudokuElement.getRowNumber() == row)
                .forEach(sudokuElement -> sudokuElement.deleteFromPossibleValues(value));

        getRows().stream()
                .flatMap(sudokuRow -> sudokuRow.getElements().stream())
                .filter(sudokuElement -> sudokuElement.getBlockNumber() == block)
                .forEach(sudokuElement -> sudokuElement.deleteFromPossibleValues(value));
    }

    @Override
    public String toString() {
        return rows.stream()
                .map(SudokuRow::toString)
                .collect(Collectors.joining("\n", "", "\n --- --- --- --- --- --- --- --- ---"));
    }

    public SudokuBoard deepCopy() throws CloneNotSupportedException {
        SudokuBoard cloneBoard = (SudokuBoard) super.clone();
        cloneBoard.rows = new ArrayList<>(SUDOKU_LAST_ROW);
        for (SudokuRow theRow : rows) {
            cloneBoard.rows.add(theRow.deepCopy());
        }
        return cloneBoard;
    }
}
