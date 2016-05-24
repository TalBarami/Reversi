package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal on 24/05/2016.
 */
public class MainMenu extends JPanel implements ActionListener {
    public MainFrame mainFrame;
    public JButton playButton;
    public JButton resumeButton;
    public JButton loadButton;
    public JButton saveButton;
    public JButton settingsButton;
    public JButton quitButton;

    public MainMenu(MainFrame mainframe){
        super(new BorderLayout());
        this.mainFrame=mainframe;
        add(new JLabel (MainFrame.Logo,JLabel.CENTER),BorderLayout.NORTH);

        playButton = new JButton(new ImageIcon("images/PLAY.png"));
        playButton.setBorder(new EmptyBorder(0,0,0,0));
        resumeButton = new JButton(new ImageIcon("images/RESUME.png"));
        resumeButton.setBorder(new EmptyBorder(0,0,0,0));
        saveButton = new JButton(new ImageIcon("images/SAVE.png"));
        saveButton.setBorder(new EmptyBorder(0,0,0,0));
        loadButton = new JButton(new ImageIcon("images/LOAD.png"));
        loadButton.setBorder(new EmptyBorder(0,0,0,0));
        settingsButton = new JButton(new ImageIcon("images/SETTINGS.png"));
        settingsButton.setBorder(new EmptyBorder(0,0,0,0));
        quitButton = new JButton(new ImageIcon("images/QUIT.png"));
        quitButton.setBorder(new EmptyBorder(0,0,0,0));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS));
        buttonsPanel.add(playButton);
        buttonsPanel.add(resumeButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(quitButton);

        playButton.setAlignmentX(CENTER_ALIGNMENT);
        resumeButton.setAlignmentX(CENTER_ALIGNMENT);
        loadButton.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        quitButton.setAlignmentX(CENTER_ALIGNMENT);


        playButton.addActionListener(this);
        resumeButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        settingsButton.addActionListener(this);
        quitButton.addActionListener(this);

        resumeButton.setEnabled(mainFrame.game != null);

        add(buttonsPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(playButton))
            mainFrame.playGame();
        else if(e.getSource().equals(resumeButton))
            mainFrame.resumeGame();
        else if(e.getSource().equals(settingsButton))
            mainFrame.settingsMenu();
        else if(e.getSource().equals(quitButton))
            System.exit(0);
        else if(e.getSource().equals(saveButton))
            mainFrame.saveGame();
        else if(e.getSource().equals(loadButton))
            mainFrame.loadGame();
    }

}
