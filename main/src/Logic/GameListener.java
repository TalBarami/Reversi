package Logic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 * Created by Tal on 24/05/2016.
 */
public class GameListener implements ActionListener, MouseListener {
    public GameBoard gameBoard;
    private int x;
    private int y;
    private JButton button;

    public GameListener(GameBoard game, int x, int y, JButton button){
        this.gameBoard = game;
        this.x = x;
        this.y = y;
        this.button = button;
    }
    public void actionPerformed(ActionEvent e){
        if(gameBoard.isValidMove(x, y))
            gameBoard.playMove(x, y);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent arg0) {}


    @Override
    public void mouseEntered(java.awt.event.MouseEvent arg0) {
        if(gameBoard.isValidMove(x,y))
            gameBoard.paintSequence(x, y, false);
    }


    @Override
    public void mouseExited(java.awt.event.MouseEvent arg0) {
        if(gameBoard.isValidMove(x,y))
            gameBoard.paintSequence(x, y, true);
    }


    @Override
    public void mousePressed(java.awt.event.MouseEvent arg0) {}


    @Override
    public void mouseReleased(java.awt.event.MouseEvent arg0) {}
}
