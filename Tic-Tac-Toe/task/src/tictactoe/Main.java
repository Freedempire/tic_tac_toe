package tictactoe;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public enum GameState {
        UNFINISHED("Game not finished"),
        DRAW("Draw"),
        X_WINS("X wins"),
        O_WINS("O wins"),
        IMPOSSIBLE("Impossible");

        final String msg;

        GameState(String msg) {
            this.msg = msg;
        }

        // override the toString method
        public String toString() {
            return msg;
        }
    }

    static final int WIDTH = 3;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] field = new char[WIDTH][WIDTH];
        boolean xTurn = true;
        GameState gameState;

        // initialize filed
        initField(field);

        while ((gameState = checkState(field)) == GameState.UNFINISHED) {
            outputField(field);
            while (!makeMove(field, readInCoords(), xTurn));
            xTurn = !xTurn;
        }

        // output the final result
        outputField(field);
        System.out.println(gameState);
    }

    public static void outputField(char[][] field) {
        System.out.println("---------");

        for (int i = 0; i < WIDTH; i++) {
            System.out.print("| ");
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");
    }

    public static GameState checkState(char[][] field) {
        // check difference between X and O
        int xCount = 0;
        int oCount = 0;
        int eCount = 0;

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (field[i][j] == 'X')
                    xCount++;
                else if (field[i][j] == 'O')
                    oCount++;
                else
                    eCount++;
            }
        }

        if (xCount - oCount > 1 || oCount - xCount > 1)
            return GameState.IMPOSSIBLE;

        int fullXRowCount = 0;
        int fullORowCount = 0;
        char[] fullXRow = {'X', 'X', 'X'};
        char[] fullORow = {'O', 'O', 'O'};

        // expand the original field with rows, columns and diagonals
        char[][] fieldExpand = new char[WIDTH * 2 + 2][WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                fieldExpand[i][j] = field[i][j];
                fieldExpand[j + WIDTH][i] = field[i][j];
                if (i == j)
                    fieldExpand[WIDTH * 2][i] = field[i][j];
                if (i + j == 2)
                    fieldExpand[WIDTH * 2 + 1][i] = field[i][j];
            }
        }

        // check full X and O rows
        for (char[] row : fieldExpand) {
            if (Arrays.equals(row, fullXRow))
                fullXRowCount++;
            else if (Arrays.equals(row, fullORow))
                fullORowCount++;
        }

        // determine the state
        if (fullXRowCount > 0 && fullORowCount > 0)
            return GameState.IMPOSSIBLE;
        else if (fullXRowCount > 0)
            return GameState.X_WINS;
        else if (fullORowCount > 0)
            return GameState.O_WINS;
        else if (fullXRowCount == 0 && fullORowCount == 0 && eCount > 0)
            return GameState.UNFINISHED;
        else
            return GameState.DRAW;
    }

    public static int[] readInCoords() {
        int[] coords = new int[2];
        for(int i = 0; i < 2;) {
            if (i == 0)
                System.out.print("Enter the coordinates: ");

            if (!input.hasNextInt()) {
                System.out.println("You should enter numbers!");
                input.nextLine();
                i = 0;
                continue;
            }

            int num = input.nextInt();

            if (num < 1 || num > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                input.nextLine();
                i = 0;
                continue;
            }

            if (i == 0) {
                coords[1] = num - 1; // convert col number to standard index
            } else {
                coords[0] = WIDTH - num;
            }

            i++;
        }

        return coords;
    }

    public static boolean makeMove(char[][] field, int[] coords, boolean xTurn) {
        if (field[coords[0]][coords[1]] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else if (xTurn){
            field[coords[0]][coords[1]] = 'X';
            return true;
        } else {
            field[coords[0]][coords[1]] = 'O';
            return true;
        }
    }

    public static void initField(char[][] field) {
        for (char[] row : field) {
            Arrays.fill(row, ' ');
        }
    }
}