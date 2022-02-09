package sudoku;

import java.util.Map;

public class ConcreteSudokuSolver extends AbstractSudokuSolver {
	private static final String NL = System.lineSeparator();

	public ConcreteSudokuSolver(String dataFile, String yicesCommand, String autoYicesFile) {
		super(dataFile, yicesCommand, autoYicesFile);
	}

	@Override
	protected String generateYicesCode(int[][] board) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(";; Auto-Generated Yices Code for Solving Sudoku Problems" + NL);

		// If a position i,j has zero value, it means its empty
		// A position can have values from 1-9
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				buffer.append("(define v" + i + j + " :: int)" + NL);
			}
		}

		buffer.append(NL);
		buffer.append(NL);

		// Step 2 - Define that all positions can have value in the range [1-9]
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				buffer.append("(assert (and (<= 1 v" + i + j + ") (>= 9 v" + i + j + ")))" + NL);
			}
		}

		buffer.append(NL);
		buffer.append(NL);

		// Step 3: Assign known values from the partial board to the variables
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int num = board[i][j];
				if (num != 0) {
					buffer.append("(assert (= v" + i + j + " " + num + "))" + NL);
				}
			}
		}

		buffer.append(NL);
		buffer.append(NL);

		// Step 4 - Defines rules for the outer-most 9x9 square
		buffer.append("; defining rules for rows ----------------------" + NL);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				buffer.append("(assert ");
				for (int k = 1; k < board[0].length - 1; k++) {
					buffer.append("(and ");
				}
				for (int k = 0; k < board[0].length; k++) {
					if (k == 0 && j != 0 || k == 1 && j == 0)
						buffer.append("(not(= v" + i + j + " v" + i + k + ")) ");
					else if (!(k == j))
						buffer.append("(not(= v" + i + j + " v" + i + k + "))) ");

				}
				buffer.append(")" + NL);
			}
			buffer.append(NL);
		}
		buffer.append(NL);
		buffer.append(NL);

		buffer.append("; defining rules for columns ----------------------" + NL);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				buffer.append("(assert ");
				for (int k = 1; k < board[0].length - 1; k++) {
					buffer.append("(and ");
				}
				for (int k = 0; k < board[0].length; k++) {
					if (k == 0 && i != 0 || k == 1 && i == 0)
						buffer.append("(not(= v" + i + j + " v" + k + j + ")) ");
					else if (!(k == i))
						buffer.append("(not(= v" + i + j + " v" + k + j + "))) ");

				}
				buffer.append(")" + NL);
			}
			buffer.append(NL);
		}

		buffer.append(NL);
		buffer.append(NL);

		// Step 5 - Define the rules for 9 inner 3x3 squares
		buffer.append(";; defining rules from small 9x9s-----------------------------" + NL);
		for (int i = 0; i < board.length; i = i + 3) {
			for (int j = 0; j < board[0].length; j = j + 3) {
				for (int m = 0; m < 3; m++) {
					for (int n = 0; n < 3; n++) {
						buffer.append("(assert ");
						for (int k = 1; k < 8; k++) {
							buffer.append("(and ");
						}
						if (m == 0 && n == 0)
							buffer.append("(not(= v" + i + j + " v" + i + (j + 1) + ")) ");
						else
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + i + j + ")) ");
						// 8 other spots
						if (!((i + m) == i && (j + 1) == (j + n)) && !((m == 0) && (n == 0)))
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + i + (j + 1) + "))) ");
						if (!((i + m) == i && (j + 2) == (j + n)))
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + i + (j + 2) + "))) ");
						if (!((i + m) == (i + 1) && j == (j + n)))
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + (i + 1) + j + "))) ");
						if (!((i + m) == (i + 1) && (j + 1) == (j + n)))
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + (i + 1) + (j + 1) + "))) ");
						if (!((i + m) == (i + 1) && (j + 2) == (j + n)))
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + (i + 1) + (j + 2) + "))) ");
						if (!((i + m) == (i + 2) && j == (j + n)))
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + (i + 2) + j + "))) ");
						if (!((i + m) == (i + 2) && (j + 1) == (j + n)))
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + (i + 2) + (j + 1) + "))) ");
						if (!((i + m) == (i + 2) && (j + 2) == (j + n)))
							buffer.append("(not(= v" + (i + m) + (j + n) + " v" + (i + 2) + (j + 2) + "))) ");

						buffer.append(")" + NL);
					}

				}
				buffer.append(NL);
			}
		}

		buffer.append(NL);
		buffer.append(NL);

		// Step 6: Check and show model
		buffer.append(NL);
		buffer.append(";; Check and show the identified solution" + NL);
		buffer.append("(check)" + NL);
		buffer.append("(show-model)" + NL);

		// Step 5: Return the Yices code for use by the superclass
		return buffer.toString();

	}

	@Override
	protected void interpretResult(Map<String, String> result, int[][] board) {
		if (result == null)
			return;

		// For each <K,V> in result.entrySet(), set board[x][y] = V
		// Clearly K must have knowledge of x and y in the String itself for
		// this to work
		result.entrySet().stream().forEach(e -> {
			String key = e.getKey();
			String value = e.getValue();

			int x = Integer.parseInt(key.substring(1, 2)); // key.substring(1,
															// 2));
			int y = Integer.parseInt(key.substring(2, 3)); // key.substring(2,
															// 3));

			int v = Integer.parseInt(value);

			// Step 4 - Set x,y position of board to the value returned by Yices
			board[x][y] = v;
		});
	}
}
