package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Tal on 24/05/2016.
 */
public class MainFrame  extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final ImageIcon whiteDiscIcon = new ImageIcon("whiteDisc.png");
    public static final ImageIcon blackDiscIcon = new ImageIcon("blackDisc.png");
    public static final ImageIcon Logo = new ImageIcon("REVERSI.gif");
    public static final ImageIcon Settings = new ImageIcon("settingsMenu.gif");
    public static final ImageIcon MainMenauICON = new ImageIcon("mainMenu.png");

    public MainMenu mainMenu;
    public Game game;
    public SettingsMenu settingsMenu;

    public MainFrame(){
        super("REVERSI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.settingsMenu = new SettingsMenu(this);
        this.mainMenu = new MainMenu(this);

        this.setSize(800,800);
        mainMenu();
    }

    public static void main(String[] args){
        MainFrame main=new MainFrame();
    }

    public void playGame(){
        game = new Game(this);
        getContentPane().removeAll();
        getContentPane().add(game);
        revalidate();
        repaint();
        setVisible(true);
        mainMenu.resumeButton.setEnabled(true);
        setTitle("Reversi");
        setSize(800,800);
    }

    public void resumeGame(){
        getContentPane().removeAll();
        getContentPane().add(game);
        revalidate();
        repaint();
        setVisible(true);
        setTitle("Reversi");
        setSize(800,800);
    }

    public void mainMenu(){
        getContentPane().removeAll();
        getContentPane().add(mainMenu);
        revalidate();
        repaint();
        setVisible(true);
        setTitle("Main Menu");
    }

    public void settingsMenu(){
        getContentPane().removeAll();
        getContentPane().add(settingsMenu);
        revalidate();
        repaint();
        setVisible(true);
        setTitle("Settings");
        pack();
    }

    public void loadGame(){
        File selectedFile=null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
        try{
            FileInputStream fis = new FileInputStream(selectedFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
        }
        catch(IOException exp){
            System.out.println("bla bla");
        }
    }

    public void saveGame(){
        JPanel savePanel=new JPanel();
        savePanel.setLayout(new FlowLayout());
        final JButton applySave = new JButton("Save");
        final JButton cancelSave = new JButton("Cancel");
        final TextField fileName = new TextField(16);
        savePanel.add(new JLabel("Please enter your file name: "));
        savePanel.add(fileName);
        savePanel.add(applySave);
        savePanel.add(cancelSave);
        final JDialog saveDialog=new JDialog();
        saveDialog.add(savePanel);
        saveDialog.setSize(new Dimension(700, 100));
        saveDialog.show();
        ActionListener act = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(applySave)){
                    try {
                        String boardSize = game.gameBoard.H+","+game.gameBoard.W;
                        String whiteName=settingsMenu.whitePlayerName;
                        String blackName=settingsMenu.blackPlayerName;
                        String whiteType=settingsMenu.whitePlayerType+"";
                        String blackType=settingsMenu.whitePlayerType+"";
                        String currentPlayer=game.gameBoard.currentPlayer.color+"";

                        File file = new File(fileName.getText()+".txt");

                        if (file.exists())
                            JOptionPane.showMessageDialog(null, "File is already exists.");
                        else
                        {
                            file.createNewFile();
                            FileWriter fw = new FileWriter(file.getAbsoluteFile());
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(boardSize);
                            bw.newLine();
                            bw.write(whiteName);
                            bw.newLine();
                            bw.write(blackName);
                            bw.newLine();
                            bw.write(whiteType);
                            bw.newLine();
                            bw.write(blackType);
                            bw.newLine();
                            bw.write(currentPlayer);
                            bw.newLine();
                            for(int i=0; i<game.gameBoard.H; i++){
                                String board="";
                                for(int j=0; j<game.gameBoard.W; j++)
                                    board+=game.gameBoard.discs[i][j].value+",";
                                bw.write(board.substring(0, board.length()-1));
                                bw.newLine();
                            }
                            bw.close();
                            JOptionPane.showMessageDialog(null, "File successfully saved.");
                        }
                        saveDialog.dispose();
                    }
                    catch (IOException exp) {
                        exp.printStackTrace();
                    }
                }
                else if(e.getSource().equals(cancelSave))
                    saveDialog.dispose();
            }
        };
        applySave.addActionListener(act);
        cancelSave.addActionListener(act);

    }

}
