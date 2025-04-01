import javax.swing.*;
import java.util.Random;
import java.awt.Color;

public class BattleshipGame {
    private GameBoard board;
    private BattleshipGui gui;
    private int missCount;
    private int hitCount;
    private int totalMisses;
    private int totalHits;
    private static final int MAX_HITS = 3;
    private static final int MAX_MISSES = 5;

    public BattleshipGame() {
        System.out.println("Creating BattleshipGame");
        board = new GameBoard();
        gui = new BattleshipGui(this,board);
        System.out.println("Board and GUI created");
    }

    public void startGame() {
        gui.createAndShowGUI();
        resetGame();
    }

    public void resetGame() {
        System.out.println("Resetting Game!");
        if(gui == null) {
            System.out.println("Error: GUI is not initialized.");
            return;
        } if(board == null) {
            System.out.println("Error: GameBoard is not initialized.");
            return;
        }
        System.out.println("GUI and GameBoard properly created!");
        board.initializeBoard();
        System.out.println("GameBoard was initialized and reset!");
        missCount = 0;
        hitCount = 0;
        totalMisses = 0;
        totalHits = 0;
        System.out.println("Counters have been reset!");
        gui.resetBoard(board);
        System.out.println("Board GUI has been reset!");
        gui.updateStatus(totalMisses, totalHits, hitCount, missCount);
        System.out.println("GUI counters updated!");
    }

    public void processMove(int row, int col) {
        if(board.isHit(row, col)) {
            totalHits++;
            missCount = 0;
            gui.getFrame().getContentPane().getComponentAt(row, col).setBackground(Color.RED);
            if(board.isShipSunk(row, col)) {
                JOptionPane.showMessageDialog(gui.getFrame(), "Ship Sunk!");
            }
            if(totalHits == 17) {
                JOptionPane.showMessageDialog(gui.getFrame(), "You win! All ships were sunk!");
                resetGame();
                return;
            }
        } else {
            totalMisses++;
            missCount++;
            gui.getFrame().getContentPane().getComponentAt(row,col).setBackground(Color.YELLOW);
            if(missCount >= MAX_MISSES) {
                hitCount++;
                missCount = 0;
            }
            if(hitCount >= MAX_HITS) {
                JOptionPane.showMessageDialog(gui.getFrame(), "Game over! You lost.");
                resetGame();
                return;
            }
        }
        gui.updateStatus(totalMisses, totalHits, hitCount, missCount);
    }
}
