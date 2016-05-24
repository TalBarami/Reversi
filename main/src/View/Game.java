package View;

import Logic.GameBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal on 24/05/2016.
 */
public class Game extends JPanel implements ActionListener {
    public MainFrame mainFrame;
    public GameBoard gameBoard;
    public JLabel player1Score;
    public JLabel player2Score;
    public JLabel currentTurn;
    public JButton mainMenu;

    public Game(MainFrame mainFrame){
        super(new BorderLayout());
        this.mainFrame = mainFrame;
        this.gameBoard = new GameBoard(this);
        this.player1Score = new JLabel();
        this.player2Score = new JLabel();
        this.currentTurn = new JLabel();
        this.mainMenu = new JButton(MainFrame.MainMenauICON);
        mainMenu.setBorder(new EmptyBorder(0,0,0,0));
        mainMenu.addActionListener(this);
        add(gameBoard, BorderLayout.CENTER);
        add(new JLabel (MainFrame.Logo,JLabel.CENTER),BorderLayout.NORTH);
        JPanel scorePanel=new JPanel(new GridLayout(1,3));

        JPanel player1Panel=new JPanel();
        player1Panel.setLayout(new BoxLayout(player1Panel,BoxLayout.Y_AXIS));
        JLabel player1Name=new JLabel(mainFrame.settingsMenu.whitePlayerName,JLabel.CENTER);
        JLabel player1Icon=new JLabel(MainFrame.whiteDiscIcon,JLabel.CENTER);
        player1Panel.add(player1Name,CENTER_ALIGNMENT);
        player1Panel.add(player1Score,CENTER_ALIGNMENT);
        player1Panel.add(player1Icon,CENTER_ALIGNMENT);

        JPanel player2Panel=new JPanel();
        player2Panel.setLayout(new BoxLayout(player2Panel,BoxLayout.Y_AXIS));
        JLabel player2Name=new JLabel(mainFrame.settingsMenu.blackPlayerName,JLabel.CENTER);
        JLabel player2Icon=new JLabel(MainFrame.blackDiscIcon,JLabel.CENTER);
        player2Panel.add(player2Name,CENTER_ALIGNMENT);
        player2Panel.add(player2Score,CENTER_ALIGNMENT);
        player2Panel.add(player2Icon,CENTER_ALIGNMENT);

        JPanel middlePanel=new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel,BoxLayout.Y_AXIS));
        middlePanel.add(currentTurn);
        middlePanel.add(mainMenu);

        scorePanel.add(player1Panel);
        scorePanel.add(middlePanel);
        scorePanel.add(player2Panel);

        add(scorePanel, BorderLayout.SOUTH);
        if(gameBoard.currentPlayer.isComp)
            gameBoard.playMove();
        updateScores();
    }

    public void updateScores(){
        gameBoard.white.score = gameBoard.countDiscs(1);
        gameBoard.black.score = gameBoard.countDiscs(-1);
        player1Score.setText("Score: "+gameBoard.white.score);
        player2Score.setText("Score: "+gameBoard.black.score);
        currentTurn.setText(gameBoard.currentPlayer.name+" it's you turn now");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.mainMenu();
    }
}
