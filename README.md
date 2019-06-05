# sudoku-solver
## Description
Console application for solving Sudoku boards. 
* displays graphical representation of the sudoku board and instructions in the console
* allows to input values into Board and shows updated board after each insertion
* insertion pattern: row,column,value,row,column,value... ex. 2,6,7,6,3,5)
* type "SUDOKU" to solve (capitalization does not matter)
* each insertion confirm by pressing 'Enter'

## Development guidelines
* `./gradlew clean build` - build application (tests will be triggered)
* `./gradlew customRun -q --console=plain` - start console application
