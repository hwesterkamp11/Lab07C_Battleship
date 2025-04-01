import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleshipGui extends JFrame {
    private JFrame frame;
    private JButton[][] buttons = new JButton[10][10];
    private JLabel missLabel, hitLabel, totalMissLabel, totalHitLabel;
    private BattleshipGame game;
    private GameBoard board;

    public BattleshipGui(BattleshipGame game, GameBoard board) {
        this.game = game;
        this.board = board;
    }

    public void createAndShowGUI() {
        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(10,10));
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton("~");
                final int row = i, col = j;
                buttons[i][j].addActionListener(e -> handleButtonClick(row, col));
                boardPanel.add(buttons[i][j]);
            }
        }
        JPanel statusPanel = new JPanel(new GridLayout(2,2));
        missLabel = new JLabel("Misses: 0");
        hitLabel = new JLabel("Hits: 0");
        totalMissLabel = new JLabel("Total Misses: 0");
        totalHitLabel = new JLabel("Total Hits: 0");
        statusPanel.add(missLabel);
        statusPanel.add(hitLabel);
        statusPanel.add(totalMissLabel);
        statusPanel.add(totalHitLabel);

        JPanel controlPanel = new JPanel();
        JButton playAgain = new JButton("Play Again");
        playAgain.addActionListener(e -> game.resetGame());
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> System.exit(0));
        controlPanel.add(playAgain);
        controlPanel.add(quit);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private void handleButtonClick(int row, int col) {
        game.processMove(row, col);
        buttons[row][col].setEnabled(false);
    }

    public void updateStatus(int totalMiss, int totalHit, int hitCount, int missCount) {
        missLabel.setText("Misses: " + missCount);
        hitLabel.setText("Hits: " + hitCount);
        totalMissLabel.setText("Total Misses: " + totalMiss);
        totalHitLabel.setText("Total Hits: " + totalHit);
    }

    public void resetBoard(GameBoard board) {
        if(buttons == null) {
            System.out.println("Error: Buttons array is null.");
            return;
        }
        System.out.println("Buttons array is created!");
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(buttons[i][j] == null) {
                    System.out.println("Error: Buttons array " + i + " " + j + " null.");
                    return;
                }
            }
        }
        System.out.println("Buttons array is not null!");
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                buttons[i][j].setText("~");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
