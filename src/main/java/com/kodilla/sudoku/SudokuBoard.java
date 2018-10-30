package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuBoard {
    private List<SudokuRow> rows = new ArrayList<>(9);

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            rows.add(new SudokuRow());
        }
    }

    public List<SudokuRow> getRows() {
        return rows;
    }

    public boolean insertValue(int column, int row, int value) {
        SudokuElement element;
        if (1 <= column && column <= 9) {
            if (1 <= row && row <= 9) {
                element = rows.get(row - 1).getElements().get(column - 1);
                if (element.getPossibleValues().contains(value)) {
                    element.setValue(value);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return rows.stream()
                .map(SudokuRow::toString)
    }
}
