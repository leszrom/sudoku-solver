package com.kodilla.sudoku;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class SudokuAlgorithm {
    private SudokuBoard board;
    private ArrayDeque<SudokuBackup> backtrack = new ArrayDeque<>();

    public SudokuAlgorithm(SudokuBoard board) {
        this.board = board;
    }

    public Stream<SudokuElement> streamElements() {
        return board.getRows().stream()
                .flatMap(row -> row.getElements().stream());
    }

    public boolean isNotSolved() {
        return streamElements()
                .anyMatch(element -> element.getValue() == SudokuElement.EMPTY);
    }

    public boolean isUnresolvable() {
        return streamElements()
                .filter(element -> element.getValue() == SudokuElement.EMPTY)
                .anyMatch(element -> element.getPossibleValues().isEmpty());
    }

    public void solveSudoku() {
        System.out.println(board);
        while (isNotSolved()) {
            SudokuElement elementToFill = getElementToFill();
            addToBacktrack(board, elementToFill);
            board.insertValue(
                    elementToFill.getRowNumber(),
                    elementToFill.getColumnNumber(),
                    elementToFill.getPossibleValues().get(0));

            while (isUnresolvable()) {
                this.board = backtrack.pop().getUpdatedBoard();
            }
        }
        System.out.println(UserInterface.SOLVED_SUDOKU);
        System.out.println(board);
    }

    private SudokuElement getElementToFill() {
        return streamElements()
                .filter(element -> element.getValue() == SudokuElement.EMPTY)
                .filter(element -> element.getPossibleValues().size() == 1)
                .findFirst()
                .orElseGet(() -> {
                    return streamElements()
                            .filter(element -> element.getValue() == SudokuElement.EMPTY)
                            .findFirst().orElseThrow(NoSuchElementException::new);
                });
    }

    private void addToBacktrack(SudokuBoard board, SudokuElement element) {
        SudokuBoard clonedBoard = board;
        try {
            clonedBoard = board.deepCopy();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        backtrack.push(new SudokuBackup(clonedBoard, element.getRowNumber(), element.getColumnNumber(), element.getPossibleValues().get(0)));
    }
}
