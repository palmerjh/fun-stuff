import java.util.*;
public class TicTacToe {

	
	// ******************* for gameBoard[][], 'C' signifies Cat's game
	public static void main(String[] args) {
		char[][][][] smallSquare = new char[3][3][3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						smallSquare[i][j][k][l] = ' ';
					}
				}
			}
		}
		char[][] bigSquare = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				bigSquare[i][j] = ' ';
			}
		}
		
		Scanner keyboard = new Scanner(System.in);
		Random rand = new Random();
		System.out.print("Hello! How many human players are playing? (0 to 2) ");
		int numberOfPlayers = keyboard.nextInt();
		while (numberOfPlayers < 0 || numberOfPlayers > 2) {
			System.out.print("Sorry, number must be between 0 and 2. Try again. ");
			numberOfPlayers = keyboard.nextInt();			
		}
		boolean firstPlayerIsHuman = false;
		int firstDifficultyLevel;
		int secondDifficultyLevel;
		if (numberOfPlayers == 1) {
			System.out.print("\nCool! This means that there is one human player and one computer"
					+ " player.\nDo you want the human player to be the first player to "
					+ "go? (Y|N) ");
			char answer = keyboard.next().trim().toLowerCase().charAt(0);
			if (answer == 'y') {
				firstPlayerIsHuman = true;
			}
			System.out.print("What is the difficulty level of the computer player? (0-9 or type anything else"
					+ " for random");
			if (!keyboard.hasNextInt()) {
				firstDifficultyLevel = -1;
			} else {
				firstDifficultyLevel = keyboard.nextInt();
				if (firstDifficultyLevel < 0 || firstDifficultyLevel > 9) {
					firstDifficultyLevel = -1;
				}
			}
		} else if (numberOfPlayers == 0) {
			System.out.print("\nCool! This means that there are two computer players. What is the difficulty\n"
					+ "level of the first computer? (0-9 or type anything else for random) ");
			if (!keyboard.hasNextInt()) {
				firstDifficultyLevel = -1;
			} else {
				firstDifficultyLevel = keyboard.nextInt();
				if (firstDifficultyLevel < 0 || firstDifficultyLevel > 9) {
					firstDifficultyLevel = -1;
				}
			}
			System.out.print("What is the difficulty level of the second computer player? (0-9 or type anything"
					+ " else for random");
			if (!keyboard.hasNextInt()) {
				secondDifficultyLevel = -1;
			} else {
				secondDifficultyLevel = keyboard.nextInt();
				if (secondDifficultyLevel < 0 || secondDifficultyLevel > 9) {
					secondDifficultyLevel = -1;
				}
			}
		}
		int[] nextMove = new int[2];
		boolean[] gameIsOver;
		boolean playerOne = true;
		do {
			printBoard(smallSquare, bigSquare);
			move(numberOfPlayers, keyboard, rand, smallSquare, bigSquare, nextMove, 
					playerOne, firstPlayerIsHuman);
			gameIsOver = endGameDetermination(bigSquare);
			playerOne = playerOne == false;
		} while (!gameIsOver[0]);
		
		printBoard(smallSquare, bigSquare);
		printResults(gameIsOver);
	}

	public static void move(int numberOfPlayers, Scanner keyboard, Random rand, 
			char[][][][] smallSquare, char[][] bigSquare, int[] nextMove,
			boolean playerOne, boolean firstPlayerIsHuman) {
		
		if (playerOne) {
			System.out.println("\n\nPlayer One:\n");
		} else {
			System.out.println("\n\nPlayer Two:\n");
		}
		//if (numberOfPlayers == 0)
		int move = humanMove(smallSquare, keyboard, nextMove);
		//if (numberOfPlayers == 2) {
		//	move = humanMove(smallSquare, keyboard, nextMove);
		// insert computer stuff here
		//}
		
	//	int move = computerMove(smallSquare, difficultyLevel, nextMove);
		int i = move / 1000 - 1;
		int j = (move / 100) % 10 - 1;
		int k = (move / 10) % 10 - 1;
		int l = move % 10 - 1;	
		if (playerOne) {
			smallSquare[i][j][k][l] = 'X';
		} else {
			smallSquare[i][j][k][l] = 'O';
		}
		nextMove[0] = k + 1;
		nextMove[1] = l + 1;
		char[][] specifiedSetOfSmallSquares = new char[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				specifiedSetOfSmallSquares[row][col] = smallSquare[i][j][row][col];
			}
		}
		
		boolean[] threeInARow = thereIsAThreeInARow(specifiedSetOfSmallSquares); // threeInARow[1] == true
		// signifies 'X' and threeInARow[1] == false signifies 'O'
		if (threeInARow[0]) {
			if (threeInARow[1]) {
				bigSquare[i][j] = 'X';
			} else {
				bigSquare[i][j] = 'O';
			}
		}		
	}

	public static int humanMove(char[][][][] smallSquare, Scanner keyboard,
			int[] nextMove) {
		int[] availableMoves;
		if (nextMove[0] == 0) {
			availableMoves = new int[0];
		} else {
			availableMoves = availableMoveDetermination(smallSquare, nextMove);
		}
		System.out.print("Where would you like to move? ");
		int move = keyboard.nextInt();
		if (move / 100 == 0) {
			int j = (move % 10 + 2) / 3;
			int l = move % 10 - 3 * (j - 1);
			int i = (move / 10 + 2) / 3;
			int k = move / 10 - 3 * (i - 1);
			move = 1000 * i + 100 * j + 10 * k + l;
		}
		while (!(nextMove[0] == 0 || validMove(move, availableMoves))) {
			System.out.println("Move is not valid because either it is illegal or the space is already occupied.");
			System.out.print("Try again: ");
			move = keyboard.nextInt();
			if (move / 100 == 0) {
				int j = (move % 10 + 2) / 3;
				int l = move % 10 - 3 * (j - 1);
				int i = (move / 10 + 2) / 3;
				int k = move / 10 - 3 * (i - 1);
				move = 1000 * i + 100 * j + 10 * k + l;
			}
		}
		return move;
		
	}

	public static boolean validMove(int move, int[] availableMoves) {
		for (int x : availableMoves) {
			if (move == x) {
				return true;
			}
		}
		return false;
	}

	public static int[] availableMoveDetermination(char[][][][] smallSquare, int[] nextMove) {	
		int r = nextMove[0] - 1;
		int c = nextMove[1] - 1;
		int numOfAvailMoves = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (smallSquare[r][c][i][j] == ' ') {
					numOfAvailMoves++;
				}
			}
		}
		if (numOfAvailMoves != 0) {
			int[] availMoves = new int[numOfAvailMoves];
			int index = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (smallSquare[r][c][i][j] == ' ') {
						availMoves[index] = 1000 * (r + 1) + 100 * (c + 1) + 10 * (i + 1) + (j + 1);
						index++;
					}
				}
			}
			return availMoves;
		} //***********************add C
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						if (smallSquare[i][j][k][l] == ' ') {
							numOfAvailMoves++;
						}
					}
				}
			}
		}
		
		int[] availMoves = new int[numOfAvailMoves];
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						if (smallSquare[r][c][i][j] == ' ') {
							availMoves[index] = 1000 * i + 100 * j + 10 * (k + 1) + (l + 1);
							index++;
						}
					}
				}
			}
		}
		return availMoves;	
	}

	public static void printResults(boolean[] gameIsOver) {
		// TODO Auto-generated method stub
		
	}

	public static boolean[] endGameDetermination(char[][] gameBoard) {
		boolean[] endGame = new boolean[3];
		boolean[] threeInARow = thereIsAThreeInARow(gameBoard); // threeInARow[1] == true
				// signifies 'X' and threeInARow[1] == false signifies 'O'
		boolean[] allSquaresFilled = allSquaresAreFilled(gameBoard);
		if (threeInARow[0]) {
			endGame[0] = true;
			endGame[1] = threeInARow[1]; // 3 'X's? (false --> 3 'O's)
		} else if (allSquaresFilled[0]) {
			endGame[0] = true;
			endGame[1] = allSquaresFilled[1]; // 'X' > 'O'?
			endGame[2] = allSquaresFilled[2]; // 'X' == 'O'? (if so, discount endGame[1])
		}  
		return endGame;
	}

	public static boolean[] thereIsAThreeInARow(char[][] gameBoard) {
		boolean[] threeInaRow = new boolean[2];
		if (gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][1] == gameBoard[0][2] && 
				gameBoard[0][0] != 'C' && gameBoard[0][0] != ' ') {
			threeInaRow[0] = true;
			if (gameBoard[0][0] == 'X') {
				threeInaRow[1] = true;
			}
		} else if (gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2] && 
				gameBoard[1][0] != 'C' && gameBoard[1][0] != ' ') {
			threeInaRow[0] = true;
			if (gameBoard[1][0] == 'X') {
				threeInaRow[1] = true;
			}
		} else if (gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2] && 
				gameBoard[2][0] != 'C' && gameBoard[2][0] != ' ') {
			threeInaRow[0] = true;
			if (gameBoard[2][0] == 'X') {
				threeInaRow[1] = true;
			}
		} else if (gameBoard[0][0] == gameBoard[1][0] && gameBoard[1][0] == gameBoard[2][0] && 
				gameBoard[0][0] != 'C' && gameBoard[0][0] != ' ') {
			threeInaRow[0] = true;
			if (gameBoard[0][0] == 'X') {
				threeInaRow[1] = true;
			}
		} else if (gameBoard[0][1] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][1] && 
				gameBoard[0][1] != 'C' && gameBoard[0][1] != ' ') {
			threeInaRow[0] = true;
			if (gameBoard[0][1] == 'X') {
				threeInaRow[1] = true;
			}
		} else if (gameBoard[0][2] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][2] && 
				gameBoard[0][2] != 'C' && gameBoard[0][2] != ' ') {
			threeInaRow[0] = true;
			if (gameBoard[0][2] == 'X') {
				threeInaRow[1] = true;
			}
		} else if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && 
				gameBoard[0][0] != 'C' && gameBoard[0][0] != ' ') {
			threeInaRow[0] = true;
			if (gameBoard[0][0] == 'X') {
				threeInaRow[1] = true;
			}
		} else if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0] && 
				gameBoard[0][2] != 'C' && gameBoard[0][2] != ' ') {
			threeInaRow[0] = true;
			if (gameBoard[0][2] == 'X') {
				threeInaRow[1] = true;
			}
		}
		return threeInaRow;
	}

	public static boolean[] allSquaresAreFilled(char[][] gameBoard) {
		int x_Count = 0, o_Count = 0;
		boolean[] squaresFilled = {true, false, false}; // first: are all squares filled?
			// second: if so, are there more 'X's than 'O's? (assuming third == false)
			// third: is there a tie? (if so, second boolean is discounted)
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == ' ') {
					squaresFilled[0] = false;
				} else if (gameBoard[i][j] == 'X') {
					x_Count++;
				} else if (gameBoard[i][j] == 'O') {
					o_Count++;
				}
			}
		}
		squaresFilled[1] = x_Count > o_Count;
		squaresFilled[2] = x_Count == o_Count;	
		
		return squaresFilled;
	}

	public static void printBoard(char[][][][] smallSquare, char[][] gameBoard) {
		System.out.println("\n         1            2            3     ");
		System.out.println("     1   2   3    1   2   3    1   2   3 ");
		System.out.printf("   1 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[0][0][0][0], smallSquare[0][0][0][1], smallSquare[0][0][0][2],
				smallSquare[0][1][0][0], smallSquare[0][1][0][1], smallSquare[0][1][0][2],
				smallSquare[0][2][0][0], smallSquare[0][2][0][1], smallSquare[0][2][0][2]);
		System.out.println("    -----------||-----------||-----------");
		System.out.printf("1  2 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[0][0][1][0], smallSquare[0][0][1][1], smallSquare[0][0][1][2],
				smallSquare[0][1][1][0], smallSquare[0][1][1][1], smallSquare[0][1][1][2],
				smallSquare[0][2][1][0], smallSquare[0][2][1][1], smallSquare[0][2][1][2]);
		System.out.println("    -----------||-----------||-----------");
		System.out.printf("   3 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[0][0][2][0], smallSquare[0][0][2][1], smallSquare[0][0][2][2],
				smallSquare[0][1][2][0], smallSquare[0][1][2][1], smallSquare[0][1][2][2],
				smallSquare[0][2][2][0], smallSquare[0][2][2][1], smallSquare[0][2][2][2]);
		System.out.println("    =====================================");
		System.out.printf("   1 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[1][0][0][0], smallSquare[1][0][0][1], smallSquare[1][0][0][2],
				smallSquare[1][1][0][0], smallSquare[1][1][0][1], smallSquare[1][1][0][2],
				smallSquare[1][2][0][0], smallSquare[1][2][0][1], smallSquare[1][2][0][2]);
		System.out.println("    -----------||-----------||-----------");
		System.out.printf("2  2 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[1][0][1][0], smallSquare[1][0][1][1], smallSquare[1][0][1][2],
				smallSquare[1][1][1][0], smallSquare[1][1][1][1], smallSquare[1][1][1][2],
				smallSquare[1][2][1][0], smallSquare[1][2][1][1], smallSquare[1][2][1][2]);
		System.out.println("    -----------||-----------||-----------");
		System.out.printf("   3 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[1][0][2][0], smallSquare[1][0][2][1], smallSquare[1][0][2][2],
				smallSquare[1][1][2][0], smallSquare[1][1][2][1], smallSquare[1][1][2][2],
				smallSquare[1][2][2][0], smallSquare[1][2][2][1], smallSquare[1][2][2][2]);
		System.out.println("    =====================================");
		System.out.printf("   1 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[2][0][0][0], smallSquare[2][0][0][1], smallSquare[2][0][0][2],
				smallSquare[2][1][0][0], smallSquare[2][1][0][1], smallSquare[2][1][0][2],
				smallSquare[2][2][0][0], smallSquare[2][2][0][1], smallSquare[2][2][0][2]);
		System.out.println("    -----------||-----------||-----------");
		System.out.printf("3  2 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[2][0][1][0], smallSquare[2][0][1][1], smallSquare[2][0][1][2],
				smallSquare[2][1][1][0], smallSquare[2][1][1][1], smallSquare[2][1][1][2],
				smallSquare[2][2][1][0], smallSquare[2][2][1][1], smallSquare[2][2][1][2]);
		System.out.println("    -----------||-----------||-----------");
		System.out.printf("   3 %c | %c | %c || %c | %c | %c || %c | %c | %c \n", 
				smallSquare[2][0][2][0], smallSquare[2][0][2][1], smallSquare[2][0][2][2],
				smallSquare[2][1][2][0], smallSquare[2][1][2][1], smallSquare[2][1][2][2],
				smallSquare[2][2][2][0], smallSquare[2][2][2][1], smallSquare[2][2][2][2]);
		System.out.println("\nOverall board situation:\n");
		System.out.printf(" %c | %c | %c \n", gameBoard[0][0], gameBoard[0][1], gameBoard[0][2]);
		System.out.println("-----------");
		System.out.printf(" %c | %c | %c \n", gameBoard[1][0], gameBoard[1][1], gameBoard[1][2]);
		System.out.println("-----------");
		System.out.printf(" %c | %c | %c \n", gameBoard[2][0], gameBoard[2][1], gameBoard[2][2]);
				
	}

}
