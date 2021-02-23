package Model;

import Controller.Debug_Controller;

public class PuzzleGenerator {
	static int[][] mat; // contains puzzle
	static int N; // number of columns/rows.
	int SRN; // square root of N
	int K; // No. Of hidden digits

	// Constructor
	PuzzleGenerator(int N, int K) {
		PuzzleGenerator.N = N;
		this.K = K;

		// Compute square root of N
		Double SRNd = Math.sqrt(N);
		SRN = SRNd.intValue();

		mat = new int[N][N];
	}

	// Sudoku Generator
	public void fillValues() {
		// Fill the diagonal of SRN x SRN matrices
		fillDiagonal();

		// Fill remaining blocks
		fillRemaining(0, SRN);

		// Remove Randomly K digits to make game
		removeKDigits();
	}

	// Fill the diagonal SRN number of SRN x SRN matrices
	void fillDiagonal() {

		for (int i = 0; i < N; i = i + SRN)

			// for diagonal box, start coordinates->i==j
			fillBox(i, i);
	}

	// Returns false if given 3 x 3 block contains num.
	boolean unUsedInBox(int rowStart, int colStart, int num) {
		for (int i = 0; i < SRN; i++)
			for (int j = 0; j < SRN; j++)
				if (mat[rowStart + i][colStart + j] == num)
					return false;

		return true;
	}

	// Fill a 3 x 3 matrix.
	void fillBox(int row, int col) {
		int num;
		for (int i = 0; i < SRN; i++) {
			for (int j = 0; j < SRN; j++) {
				do {
					num = randomGenerator(N);
				} while (!unUsedInBox(row, col, num));

				mat[row + i][col + j] = num;
			}
		}
	}

	// Random generator
	int randomGenerator(int num) {
		return (int) Math.floor((Math.random() * num + 1));
	}

	// Check if safe to put in cell
	boolean CheckIfSafe(int i, int j, int num) {
		return (unUsedInRow(i, num) && unUsedInCol(j, num) && unUsedInBox(i - i % SRN, j - j % SRN, num));
	}

	// check in the row for existence
	boolean unUsedInRow(int i, int num) {
		for (int j = 0; j < N; j++)
			if (mat[i][j] == num)
				return false;
		return true;
	}

	// check in the row for existence
	boolean unUsedInCol(int j, int num) {
		for (int i = 0; i < N; i++)
			if (mat[i][j] == num)
				return false;
		return true;
	}

	// A recursive function to fill remaining
	// matrix
	boolean fillRemaining(int i, int j) {
		// System.out.println(i+" "+j);
		if (j >= N && i < N - 1) {
			i = i + 1;
			j = 0;
		}
		if (i >= N && j >= N)
			return true;

		if (i < SRN) {
			if (j < SRN)
				j = SRN;
		} else if (i < N - SRN) {
			if (j == (int) (i / SRN) * SRN)
				j = j + SRN;
		} else {
			if (j == N - SRN) {
				i = i + 1;
				j = 0;
				if (i >= N)
					return true;
			}
		}

		for (int num = 1; num <= N; num++) {
			if (CheckIfSafe(i, j, num)) {
				mat[i][j] = num;
				if (fillRemaining(i, j + 1))
					return true;

				mat[i][j] = 0;
			}
		}
		return false;
	}

	// Remove the K no. of digits to
	// complete game
	public void removeKDigits() {
		int count = K;
		while (count != 0) {
			int cellId = randomGenerator(N * N);

			// System.out.println(cellId);
			// extract coordinates i and j
			int i = (cellId / N);
			int j = cellId % 9;
			if (j != 0)
				j = j - 1;

			// System.out.println(i+" "+j);
			try {
				if (mat[i][j] != 0) {
					count--;
					mat[i][j] = 0;
				}
			} catch (Exception e) {

			}

		}
	}

	// Print sudoku
	public void printSudoku() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(mat[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public static int[][] getRowsArray() {
		return mat;
	}

	public static int[][] createSquaresArray() {
		int[][] to_return = new int[N][N];
		int current_square = 0;
		int i = 0;
		int a = 0;
		int b = 0;
		int c = 3;
		int d = 3;

		while (current_square != 9) {
			a = c - 3;
			i = 0;
			while (a < c) {
				b = d - 3;
				while (b < d) {
					if (Debug_Controller.enabled()) {
						System.out.println("a -> " + a + " b -> " + b);
					}

					to_return[current_square][i] = mat[a][b];
					i++;
					b++;
				}
				a++;
			}
			if (d >= 9) {
				d = 3;
				c = c + 3;
			} else if (d < 9) {
				d = d + 3;
			}

			current_square++;
		}

		return to_return;
	}

	public static void init(int numbers_to_hide) {
		int N = 9, K = numbers_to_hide;
		PuzzleGenerator puzzleGenerator = new PuzzleGenerator(N, K);
		puzzleGenerator.fillValues();

		if (Debug_Controller.enabled()) {
			puzzleGenerator.printSudoku();
		}
	}

}
