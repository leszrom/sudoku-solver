package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuElement extends Prototype {
    public static int EMPTY = -1;
    private int value = EMPTY;
    private List<Integer> possibleValues = new ArrayList<>(9);
    private int rowNumber;
    private int columnNumber;
    private int blockNumber;

    public SudokuElement(final int rowNumber, final int columnNumber) {
        for (int n = 1; n <= 9; n++) {
            possibleValues.add(n);
        }
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.blockNumber = SudokuBlock.getBlockNumber(rowNumber, columnNumber);
    }

    public int getValue() {
        return value;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void deleteFromPossibleValues(int value) {
        possibleValues.remove(Integer.valueOf(value));
    }

    @Override
    public String toString() {
        String s = " " + value + " ";
        if (value == EMPTY)
            s = "   ";
        return s;
    }

    protected SudokuElement deepCopy() throws CloneNotSupportedException {
        SudokuElement clonedElement = (SudokuElement) super.clone();
        clonedElement.possibleValues = new ArrayList<>(9);
        clonedElement.possibleValues.addAll(possibleValues);
        return clonedElement;
    }
}