import javax.swing.*;

public class Lab07C_Battleship {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BattleshipGame game = new BattleshipGame();
            game.startGame();
        });
    }
}
