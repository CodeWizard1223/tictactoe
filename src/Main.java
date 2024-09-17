import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void printBoard(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == '\0') {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + array[i][j] + " ");
                }
                if (j < array[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < array.length - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    public static boolean checkField(int number, char[][] array) {
        int row = (number - 1) / array.length;
        int column = (number - 1) % array.length;
        return array[row][column] == '\0';
    }

    public static void changeSymbol(int number, char param3, char[][] array) {
        int row = (number - 1) / array.length;
        int column = (number - 1) % array.length;
        array[row][column] = param3;

    }

    public static boolean checkHorizontal(char[][] array, char s) {
        for (char[] rows : array) {
            int count = 0;
            for (char cell : rows) {
                if (cell == s) {
                    count++;
                }
                if (count == array[0].length) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkVertical(char[][] array, char s) {
        for (int i = 0; i < array[0].length; i++) {
            int count = 0;
            for (char[] row : array) {
                if (row[i] == s) {
                    count++;
                }
                if (count == array.length) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkDiagonal(char[][] array, char s) {
        boolean primaryDiagonal = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i][i] != s) {
                primaryDiagonal = false;
                break;
            }
        }
        boolean secondaryDiagonal = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i][array.length - 1 - i] != s) {
                secondaryDiagonal = false;
                break;
            }
        }
        return primaryDiagonal || secondaryDiagonal;
    }

    public static boolean checkWinner(char[][] array, char s) {
        return checkHorizontal(array, s) || checkVertical(array, s) ||
                checkDiagonal(array, s);
    }

    public static boolean checkDraw(char[][] array) {
        for (char[] rows : array) {
            for (int cell : rows) {
                if (cell == '\0') {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello in TicTacToe!");
        System.out.println("Player 1, please enter your name: ");
        String player1 = scanner.nextLine();
        System.out.println(player1 + "\n");
        System.out.println("Player 2, please enter your name: ");
        String player2 = scanner.nextLine();
        System.out.println(player2 + "\n");

        char[][] board = new char[3][3];
        printBoard(board);

        String currentPlayer;
        char symbol1 = 'X';
        char symbol2 = 'O';
        char currentSymbol;
        boolean winner = true;
        while (winner) {
            currentPlayer = player1;
            currentSymbol = symbol1;
            System.out.println(currentPlayer + " choose number between 1 - 9 or 0 if you want to finish.\n");
            try {
                int number = scanner.nextInt();
                if (number == 0) {
                    System.out.println("Game ended.");
                    break;
                }
                if (number < 1 || number > 9) {
                    System.out.println("Invalid input.");
                    continue;
                }
                if (!checkField(number, board)) {
                    System.out.println("Cell is not empty!");
                    continue;
                }
                changeSymbol(number, currentSymbol, board);
                printBoard(board);
                if (checkWinner(board, currentSymbol)) {
                    System.out.println(currentPlayer + " is winner.");
                    winner = false;
                } else if (checkDraw(board)) {
                    System.out.println("It's a draw! End of the game.");
                    winner = false;
                }
                player1 = player2;
                player2 = currentPlayer;
                symbol1 = symbol2;
                symbol2 = currentSymbol;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
        System.out.println("Thank you!");
    }
}

