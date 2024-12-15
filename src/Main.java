import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static void printBoard(char[][] array) {
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                if (array[row][column] == '\0') {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + array[row][column] + " ");
                }
                if (column < array[row].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < array.length - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    private static boolean checkIfFieldIsEmpty(int number, char[][] array) {
        int row = (number - 1) / array.length;
        int column = (number - 1) % array.length;
        return array[row][column] == '\0';
    }

    private static void markFieldWithSymbol(int number, char param3, char[][] array) {
        int row = (number - 1) / array.length;
        int column = (number - 1) % array.length;
        array[row][column] = param3;

    }

    private static boolean checkHorizontal(char[][] array, char s) {
        for (char[] rows : array) {
            int count = 0;
            for (char field : rows) {
                if (field == s) {
                    count++;
                }
                if (count == array[0].length) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkVertical(char[][] array, char s) {
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

    private static boolean checkDiagonal(char[][] array, char s) {
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

    private static boolean checkWinner(char[][] array, char s) {
        return checkHorizontal(array, s) || checkVertical(array, s) ||
                checkDiagonal(array, s);
    }

    private static boolean checkDraw(char[][] array) {
        for (char[] rows : array) {
            for (int field : rows) {
                if (field == '\0') {
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
                if (!checkIfFieldIsEmpty(number, board)) {
                    System.out.println("Field is not empty!");
                    continue;
                }
                markFieldWithSymbol(number, currentSymbol, board);
                printBoard(board);
                if (checkWinner(board, currentSymbol)) {
                    System.out.println(currentPlayer + " is winner.");
                    winner = false;
                } else if (checkDraw(board)) {
                    System.out.println("It's a draw! End of the game.");
                    winner = false;
                }
                // swap players
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

