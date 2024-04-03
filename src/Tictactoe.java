import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tictactoe {
    JFrame gameWindow;
    boolean XTurn;
    boolean winnerChecker;
    int pressCount;
    int[][] winningPattern;
    JButton[] buttons;
    JPanel boxPanel;

    public Tictactoe(){
        XTurn = true;
        winnerChecker = false;
        pressCount= 0;
        winningPattern = new int[][] {
            {0, 1, 2},
            {0, 3, 6},
            {0, 4, 8},
            {1, 4, 7},
            {2, 5, 8},
            {2, 4, 6},
            {3, 4, 5},
            {6, 7, 8}
        };
        buttons = new JButton[9];
        boxPanel = new JPanel();

    }

    public void gameClass(){
        gameWindow = new JFrame("TICTACTOE");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLayout(new GridLayout(3,1));
        gameWindow.setVisible(true);
        gameWindow.setSize(500, 500);
        gameWindow.add(boxPanel);
        boxPanel.setLayout(new GridLayout(3,3));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
        }
        
        for (int i = 0; i < buttons.length; i++) {
            boxPanel.add(buttons[i]);
        }

        JLabel winningMsg = new JLabel();
        winningMsg.setAlignmentX(JFrame.LEFT_ALIGNMENT);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        gameWindow.add(footerPanel);

        JButton resetButton = new JButton("Reset");
        footerPanel.add(resetButton);
        
        ActionListener pressAction= new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                pressCount++;
                JButton buttonPressed = (JButton) ae.getSource();

                if (pressCount<=9) {
                    if (XTurn == true) {
                        buttonPressed.setText("X");
                        XTurn = false;
                    }
                    else{
                        buttonPressed.setText("O");
                        XTurn = true;
                    }
                }

                winnerChecker = checkWinner(buttons);
                
                if (winnerChecker == true && XTurn == false) {
                    winningMsg.setText("X is the winner");
                    footerPanel.add(winningMsg);
                    return;
                }
                if (winnerChecker == true && XTurn == true) {
                    winningMsg.setText("O is the winner");
                    footerPanel.add(winningMsg);
                    return;
                }
            }
        };

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                resetGame();
            }
        });

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(pressAction);
        }

        footerPanel.add(resetButton);

    }

    public void resetGame(){
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
        }
    }

    public boolean checkWinner(JButton[] buttons){
        for(int[] checkArray : winningPattern){
            String boxOne = buttons[checkArray[0]].getText();
            String boxTwo = buttons[checkArray[1]].getText();
            String boxThree = buttons[checkArray[2]].getText();

            if (!boxOne.equals("") && !boxTwo.equals("") && !boxThree.equals("")) {
                if (boxOne.equals(boxTwo) && boxTwo.equals(boxThree)) {
                    return true;
                }
            }
        }
        return false;
    }
}
