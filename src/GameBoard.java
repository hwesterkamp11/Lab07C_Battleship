import java.util.Random;

public class GameBoard {
    private static final int SIZE = 10;
    private static final int[] SHIP_SIZES = {2,3,3,4,5};
    private char[][] board;
    private boolean[][] hitMarkers;

    public GameBoard() {
        board = new char[SIZE][SIZE];
        hitMarkers = new boolean[SIZE][SIZE];
        initializeBoard();
    }

    public void initializeBoard() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
                hitMarkers[i][j] = false;
            }
        }
        placeShipsRandomly();
    }

    private void placeShipsRandomly() {
        Random rand = new Random();
        for(int shipSize : SHIP_SIZES) {
            boolean placed = false;
            while(!placed) {
                int row = rand.nextInt(SIZE);
                int col = rand.nextInt(SIZE);
                boolean horizontal = rand.nextBoolean();
                if(canPlaceShip(row, col, shipSize, horizontal)) {
                    placeShip(row, col, shipSize, horizontal);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if(horizontal) {
            if(col + size > SIZE) return false;
            for(int i = 0; i < size; i++) {
                if(board[row][col + i] != ' ') return false;
            }
        } else {
            if(row + size > SIZE) return false;
            for(int i = 0; i < size; i++) {
                if(board[row + i][col] != ' ') return false;
            }
        }
        return true;
    }

    private void placeShip(int row, int col, int size, boolean horizontal) {
        for(int i = 0; i < size; i++) {
            if(horizontal) {
                board[row][col + i] = 'S';
            } else {
                board[row + i][col] = 'S';
            }
        }
    }

    public boolean isHit(int row, int col) {
        if(hitMarkers[row][col]) return false;
        hitMarkers[row][col] = true;
        return board[row][col] == 'S';
    }

    public boolean isShipSunk(int row, int col) {
        if(board[row][col] != 'S') return false;

        boolean horizontal = (col > 0 && board[row][col - 1] == 'S') || (col < SIZE - 1 && board[row][col + 1] == 'S');
        boolean vertical = (row > 0 && board[row - 1][col] == 'S') || (row < SIZE - 1 && board[row + 1][col] == 'S');
        if(horizontal) {
            int left = col;
            while(left >= 0 && board[row][left] == 'S') {
                if(!hitMarkers[row][left]) return false;
                left--;
            }
            int right = col;
            while(right < SIZE && board[row][right] == 'S') {
                if(!hitMarkers[row][right]) return false;
                right++;
            }
        } else if(vertical) {
            int up = row;
            while(up >= 0 && board[up][col] == 'S') {
                if(!hitMarkers[up][col]) return false;
                up--;
            }
            int down = row;
            while(down < SIZE && board[down][col] == 'S') {
                if(!hitMarkers[down][col]) return false;
                down++;
            }
        }

        return true;
    }
}
