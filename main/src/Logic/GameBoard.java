package Logic;

import Entities.Player;
import View.Game;
import View.MainFrame;
import Entities.Point;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal on 24/05/2016.
 */
public class GameBoard extends JPanel {
    public final int H;
    public final int W;

    public Game game;
    public Player white;
    public Player black;
    public Player currentPlayer;
    public Point[][] discs;
    public JButton [][]buttons;

    public GameBoard(Game game){
        super(new GridLayout(game.mainFrame.settingsMenu.H,game.mainFrame.settingsMenu.W));
        this.game = game;
        white = new Player(game.mainFrame.settingsMenu.whitePlayerName, game.mainFrame.settingsMenu.whitePlayerType, 1, 2);
        black = new Player(game.mainFrame.settingsMenu.blackPlayerName, game.mainFrame.settingsMenu.blackPlayerType, -1, 2);
        if(!white.isComp && black.isComp)
            this.currentPlayer = white;
        else if(white.isComp && !black.isComp)
            this.currentPlayer = black;
        else
            this.currentPlayer = (Math.random() >= 0.5) ? white : black;

        this.H=game.mainFrame.settingsMenu.H;
        this.W=game.mainFrame.settingsMenu.W;
        JButton boardButton;
        discs = new Point[H][W];
        buttons = new JButton[H][W];
        for (int i = 0; i < H; i++)
            for(int j = 0; j < W; j++){
                discs[i][j] = new Point(i,j);
                boardButton = new JButton();
                GameListener gameListener = new GameListener(this, i,j, boardButton);
                boardButton.addActionListener(gameListener);
                boardButton.addMouseListener(gameListener);
                add(boardButton);
                buttons[i][j] = boardButton;
            }
        discs[H/2][W/2].value = 1;
        discs[H/2-1][W/2].value = -1;
        discs[H/2-1][W/2-1].value = 1;
        discs[H/2][W/2-1].value = -1;
        buttons[H/2][W/2].setIcon(MainFrame.whiteDiscIcon);
        buttons[H/2-1][W/2].setIcon(MainFrame.blackDiscIcon);
        buttons[H/2][W/2-1].setIcon(MainFrame.blackDiscIcon);
        buttons[H/2-1][W/2-1].setIcon(MainFrame.whiteDiscIcon);
    }

    public void placeDisc(int x, int y, int value){
        discs[x][y].value = currentPlayer.color;
        if(value == 1)
            buttons[x][y].setIcon(MainFrame.whiteDiscIcon);
        else if(value == -1)
            buttons[x][y].setIcon(MainFrame.blackDiscIcon);
        else
            buttons[x][y].setIcon(null);
    }

    public void paintCell(int x, int y, int value){
        if(value == 1)
            buttons[x][y].setBackground(Color.YELLOW);
        else if(value == -1)
            buttons[x][y].setBackground(Color.BLUE);
        else
            buttons[x][y].setBackground(null);
    }

    public int checkSequence(int x, int y, int k){
        boolean isValid = false;
        int c = 0;
        if(discs[x][y].value != 0)
            return 0;
        switch(k){
            case 0:{ // bottom
                if(x>H-3) break;
                for(int i=x+1; i<H-1 && !isValid; i++){
                    c++;
                    if(discs[i][y].value == -currentPlayer.color && discs[i+1][y].value == currentPlayer.color)
                        isValid = true;
                    else if(discs[i][y].value != -currentPlayer.color){
                        c=0;
                        break;
                    }
                }
                break;
            }
            case 1:{ // top
                if(x<2) break;
                for(int i=x-1; i>0 && !isValid; i--){
                    c++;
                    if(discs[i][y].value == -currentPlayer.color && discs[i-1][y].value == currentPlayer.color)
                        isValid = true;
                    else if(discs[i][y].value != -currentPlayer.color){
                        c=0;
                        break;
                    }
                }
                break;
            }
            case 2:{ // left
                if(y<2) break;
                for(int i=y-1; i>0 && !isValid; i--){
                    c++;
                    if(discs[x][i].value == -currentPlayer.color && discs[x][i-1].value == currentPlayer.color)
                        isValid = true;
                    else if(discs[x][i].value != -currentPlayer.color){
                        c=0;
                        break;
                    }
                }
                break;
            }
            case 3:{ // right
                if(y>W-3) break;
                for(int i=y+1; i<W-1 && !isValid; i++){
                    c++;
                    if(discs[x][i].value == -currentPlayer.color && discs[x][i+1].value == currentPlayer.color)
                        isValid = true;
                    else if(discs[x][i].value != -currentPlayer.color){
                        c=0;
                        break;
                    }
                }
                break;
            }
            case 4:{ // bot-right
                if(x>H-3 || y>W-3) break;
                for(int i=1; (x+i < H-1 && y+i < W-1) && !isValid; i++){
                    c++;
                    if(discs[x+i][y+i].value == -currentPlayer.color && discs[x+i+1][y+i+1].value == currentPlayer.color)
                        isValid = true;
                    else if(discs[x+i][y+i].value != -currentPlayer.color){
                        c=0;
                        break;
                    }
                }
                break;
            }
            case 5:{ // bot-left
                if(x>H-3 || y<2) break;
                for(int i=1; (x+i < H-1 && y-i > 0) && !isValid; i++){
                    c++;
                    if(discs[x+i][y-i].value == -currentPlayer.color && discs[x+i+1][y-i-1].value == currentPlayer.color)
                        isValid = true;
                    else if(discs[x+i][y-i].value != -currentPlayer.color){
                        c=0;
                        break;
                    }
                }
                break;
            }
            case 6:{ // top-right
                if(x<2 || y>W-3) break;
                for(int i=1; (x-i > 0 && y+i < W-1) && !isValid; i++){
                    c++;
                    if(discs[x-i][y+i].value == -currentPlayer.color && discs[x-i-1][y+i+1].value == currentPlayer.color)
                        isValid = true;
                    else if(discs[x-i][y+i].value != -currentPlayer.color){
                        c=0;
                        break;
                    }
                }
                break;
            }
            case 7:{ // top-left
                if(x<2 || y<2) break;
                for(int i=1; (x-i > 0 && y-i > 0) && !isValid; i++){
                    c++;
                    if(discs[x-i][y-i].value == -currentPlayer.color && discs[x-i-1][y-i-1].value == currentPlayer.color)
                        isValid = true;
                    else if(discs[x-i][y-i].value != -currentPlayer.color){
                        c=0;
                        break;
                    }
                }
                break;
            }
        }
        if(isValid)
            return c;
        return 0;
    }

    public boolean isValidMove(int x, int y){
        boolean isValid = false;
        int[] moves = getMove(x, y);
        for(int i=0; i<moves.length && !isValid; i++)
            if(moves[i] != 0) isValid=true;
        return isValid;
    }

    public int[] getMove(int x, int y){
        int [] ans = new int[8];
        for(int i=0; i<8; i++)
            ans[i] = checkSequence(x,y,i);
        return ans;
    }

    public void paintSequence(int x, int y, boolean clear){
        int[] moves = getMove(x, y);
        int value = currentPlayer.color;
        if(clear)
            value = 0;
        for(int k=0; k<moves.length; k++){
            for(int i=0; i<moves[k]+1; i++){
                switch(k){
                    case 0:
                        paintCell(x+i, y, value);
                        break;
                    case 1:
                        paintCell(x-i, y, value);
                        break;
                    case 2:
                        paintCell(x, y-i, value);
                        break;
                    case 3:
                        paintCell(x, y+i, value);
                        break;
                    case 4:
                        paintCell(x+i, y+i, value);
                        break;
                    case 5:
                        paintCell(x+i, y-i, value);
                        break;
                    case 6:
                        paintCell(x-i, y+i, value);
                        break;
                    case 7:
                        paintCell(x-i, y-i, value);
                        break;
                }
            }
        }
    }

    public void playMove(int x, int y){
        int[] moves = getMove(x, y);
        paintSequence(x, y, true);
        for(int k=0; k<moves.length; k++){
            for(int i=0; i<moves[k]+1; i++){
                switch(k){
                    case 0:
                        placeDisc(x+i, y, currentPlayer.color);
                        break;
                    case 1:
                        placeDisc(x-i, y, currentPlayer.color);
                        break;
                    case 2:
                        placeDisc(x, y-i, currentPlayer.color);
                        break;
                    case 3:
                        placeDisc(x, y+i, currentPlayer.color);
                        break;
                    case 4:
                        placeDisc(x+i, y+i, currentPlayer.color);
                        break;
                    case 5:
                        placeDisc(x+i, y-i, currentPlayer.color);
                        break;
                    case 6:
                        placeDisc(x-i, y+i, currentPlayer.color);
                        break;
                    case 7:
                        placeDisc(x-i, y-i, currentPlayer.color);
                        break;
                }
            }
        }
        nextTurn();
    }

    public void playMove(){
        int[] move = calculateNextMove();
        playMove(move[0],move[1]);
    }

    public int countMoves(){
        int c = 0;
        for(int i=0; i<H; i++)
            for(int j=0; j<W; j++)
                if(isValidMove(i,j))
                    c++;
        return c;
    }

    public boolean hasValidMoves(){
        if(countMoves() > 0)
            return true;
        return false;
    }

    public int countDiscs(int player){
        int count = 0;
        for(int i=0; i<H; i++)
            for(int j=0; j<W; j++)
                if(discs[i][j].value == player)
                    count++;
        return count;
    }

    public boolean isBoardFull(){
        for(int i =0; i<H; i++)
            for(int j=0; j<W; j++)
                if(discs[i][j].value != 0)
                    return false;
        return true;
    }

    public void switchPlayer(){
        if(currentPlayer.color == 1) currentPlayer = black;
        else currentPlayer = white;
    }


    public void nextTurn(){
        switchPlayer();
        game.updateScores();
        if(!hasValidMoves()){
            JOptionPane.showMessageDialog(null, currentPlayer.name+" has no valid moves, press OK to continue.");
            switchPlayer();
            if(!hasValidMoves() || isBoardFull())
            {
                String winner;
                if(white.score > black.score)
                    winner = white.name;
                else if(black.score > white.score)
                    winner = black.name;
                else winner = "tie";
                if(winner.equals("tie"))
                    JOptionPane.showMessageDialog(null, "View.Game over, it's a tie!");
                else JOptionPane.showMessageDialog(null, "View.Game over, the winner is: "+winner);
                game.mainFrame.mainMenu();
                game.mainFrame.mainMenu.resumeButton.setEnabled(false);
                return;
            }
        }
        if(currentPlayer.isComp && hasValidMoves())
            playMove();
    }


    // Strategy: aiming for the move that'll grant the highest score.
    public int calculateMoveScore(int x, int y){
        int[] move = getMove(x,y);
        int sum = 0;
        for(int i=0; i<move.length; i++)
            sum+=move[i];
        return sum;
    }
    public int[] calculateNextMove(){
        int[][] moves = new int[3][countMoves()];
        int k = 0;
        for(int i=0; i<H; i++) // get all possible moves
            for(int j=0; j<W; j++)
                if(isValidMove(i,j)){
                    moves[0][k]=i;
                    moves[1][k]=j;
                    moves[2][k]=calculateMoveScore(i,j);
                    k++;
                }
        int nextMoveIndex = 0;
        boolean found = false;
        for(int i=1; i<moves[0].length && !found; i++){
            if(!isFlipable(moves[0][i],moves[1][i])){
                found = true;
                nextMoveIndex = i;
            }
            else if(moves[2][i] > moves[2][nextMoveIndex])
                nextMoveIndex = i;
        }
        int[] ans = {moves[0][nextMoveIndex], moves[1][nextMoveIndex]};
        return ans;

    }

    public boolean isFlipable(int x, int y){
        if(discs[x][y].flipable){
            if((x==0 && y==0)||(x==0 && y==W-1)||(x==H-1 && y==0)||(x==H-1 && y==W-1))
                discs[x][y].flipable=false;
            else if(x==0 || x==H-1){
                if ((discs[x][y-1].value==discs[x][y].value && !discs[x][y-1].flipable)
                        || (discs[x][y+1].value==discs[x][y].value && !discs[x][y+1].flipable))
                    discs[x][y].flipable=false;
                else if(discs[x][y-1].value!=discs[x][y].value && discs[x][y+1].value!=discs[x][y].value
                        && !discs[x][y-1].flipable && !discs[x][y+1].flipable)
                    discs[x][y].flipable=false;
            }
            else if(y==0 || y==W-1){
                if ((discs[x-1][y].value==discs[x][y].value && !discs[x-1][y].flipable)
                        || (discs[x+1][y].value==discs[x][y].value && !discs[x+1][y].flipable))
                    discs[x][y].flipable=false;
                else if(discs[x-1][y].value!=discs[x][y].value && discs[x+1][y].value!=discs[x][y].value
                        && !discs[x-1][y].flipable && !discs[x+1][y].flipable)
                    discs[x][y].flipable=false;
            }
        }

        return discs[x][y].flipable;
    }

}