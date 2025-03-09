import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 700;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    JButton restartButton = new JButton("Restart Game");

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true); // Now window is resizable
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(currentPlayer + "'s turn.");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 100));
                tile.setFocusable(false);

                tile.addActionListener(e -> {
                    if (gameOver) return;
                    JButton tileClicked = (JButton) e.getSource();
                    if (tileClicked.getText().equals("")) {
                        tileClicked.setText(currentPlayer);
                        turns++;
                        checkWinner();
                        if (!gameOver) {
                            currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                            textLabel.setText(currentPlayer + "'s turn.");
                        }
                    }
                });
            }
        }

        restartButton.setFont(new Font("Arial", Font.BOLD, 25));
        restartButton.addActionListener(e -> restartGame());
        frame.add(restartButton, BorderLayout.SOUTH);
    }

    void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().equals(board[r][1].getText()) &&
                    board[r][1].getText().equals(board[r][2].getText()) &&
                    !board[r][0].getText().equals("")) {
                highlightWinner(board[r][0], board[r][1], board[r][2]);
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals(board[1][c].getText()) &&
                    board[1][c].getText().equals(board[2][c].getText()) &&
                    !board[0][c].getText().equals("")) {
                highlightWinner(board[0][c], board[1][c], board[2][c]);
                return;
            }
        }

        if (board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText()) &&
                !board[0][0].getText().equals("")) {
            highlightWinner(board[0][0], board[1][1], board[2][2]);
            return;
        }

        if (board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText()) &&
                !board[0][2].getText().equals("")) {
            highlightWinner(board[0][2], board[1][1], board[2][0]);
            return;
        }

        if (turns == 9) {
            JOptionPane.showMessageDialog(frame, "It's a Tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            gameOver = true;
        }
    }

    void highlightWinner(JButton a, JButton b, JButton c) {
        a.setForeground(Color.green);
        b.setForeground(Color.green);
        c.setForeground(Color.green);
        textLabel.setText(currentPlayer + " is the winner!");
        JOptionPane.showMessageDialog(frame, currentPlayer + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        gameOver = true;
    }

    void restartGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(Color.darkGray);
                board[r][c].setForeground(Color.white);
            }
        }
        currentPlayer = playerX;
        textLabel.setText(currentPlayer + "'s turn.");
        gameOver = false;
        turns = 0;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
