package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal on 24/05/2016.
 */
public class SettingsMenu extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    public MainFrame mainFrame;
    public String whitePlayerName;
    public String blackPlayerName;
    public int H;
    public int W;
    public boolean whitePlayerType;
    public boolean blackPlayerType;

    public JButton cancel;
    public JButton apply;
    public JSpinner spinnerW;
    public JSpinner spinnerH;
    public JRadioButton human1;
    public JRadioButton comp1;
    public JRadioButton human2;
    public JRadioButton comp2;
    public JTextField whitePlayerNameText;
    public JTextField blackPlayerNameText;

    public SettingsMenu(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        whitePlayerName="Entities.Player 1";
        blackPlayerName="Entities.Player 2";
        H=W=8;
        whitePlayerType=blackPlayerType=false;
        this.cancel = new JButton(new ImageIcon("images/cancel.png"));
        cancel.setBorder(new EmptyBorder(0,0,0,0));
        cancel.addActionListener(this);
        this.apply = new JButton(new ImageIcon("images/apply.png"));
        apply.setBorder(new EmptyBorder(0,0,0,0));
        apply.addActionListener(this);
        setLayout(new BorderLayout());
        add(new JLabel(MainFrame.Settings),BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        JPanel sizePanel=new JPanel(new FlowLayout());
        JLabel sizeLabel=new JLabel("Choose size for board");
        SpinnerModel spinnerModelH = new SpinnerNumberModel(H,4,16,1);
        JLabel sizeH = new JLabel("		Height: ");
        spinnerH = new JSpinner(spinnerModelH);
        SpinnerModel spinnerModelW = new SpinnerNumberModel(W,4,16,1);
        JLabel sizeW = new JLabel("		Width: ");
        spinnerW = new JSpinner(spinnerModelW);
        sizePanel.add(sizeLabel);
        sizePanel.add(sizeH);
        sizePanel.add(spinnerH);
        sizePanel.add(sizeW);
        sizePanel.add(spinnerW);

        JPanel whitePlayerPanel=new JPanel(new FlowLayout());
        JLabel whitePlayerLabel=new JLabel("WHITE PLAYER:		",JLabel.LEFT);
        human1 = new JRadioButton("Human", true);
        comp1 = new JRadioButton("Comp");
        ButtonGroup group1=new ButtonGroup();
        group1.add(human1);
        group1.add(comp1);
        JLabel whitePlayerNameLabel = new JLabel("Name : 	 ", JLabel.CENTER);
        whitePlayerNameText = new JTextField(whitePlayerName,15);
        whitePlayerPanel.add(whitePlayerLabel);
        whitePlayerPanel.add(human1);
        whitePlayerPanel.add(comp1);
        whitePlayerPanel.add(whitePlayerNameLabel);
        whitePlayerPanel.add(whitePlayerNameText);

        JPanel blackPlayerPanel=new JPanel(new FlowLayout());
        JLabel blackPlayerLabel=new JLabel("BLACK PLAYER:		",JLabel.LEFT);
        human2 = new JRadioButton("Human", true);
        comp2 = new JRadioButton("Comp");
        ButtonGroup group2=new ButtonGroup();
        group2.add(human2);
        group2.add(comp2);
        JLabel  blackPlayerNameLabel = new JLabel("Name : 	 ", JLabel.CENTER);
        blackPlayerNameText = new JTextField(blackPlayerName,15);
        blackPlayerPanel.add(blackPlayerLabel);
        blackPlayerPanel.add(human2);
        blackPlayerPanel.add(comp2);
        blackPlayerPanel.add(blackPlayerNameLabel);
        blackPlayerPanel.add(blackPlayerNameText);

        settingsPanel.add(sizePanel);
        settingsPanel.add(whitePlayerPanel);
        settingsPanel.add(blackPlayerPanel);

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(apply);
        buttons.add(cancel);
        settingsPanel.add(buttons);

        add(settingsPanel,BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(apply)){
            H = (Integer)spinnerH.getValue();
            W = (Integer)spinnerW.getValue();
            whitePlayerType = comp1.isSelected();
            blackPlayerType = comp2.isSelected();
            whitePlayerName = whitePlayerNameText.getText();
            blackPlayerName = blackPlayerNameText.getText();
            mainFrame.mainMenu.resumeButton.setEnabled(false);
            mainFrame.mainMenu();
        }
        else if(e.getSource().equals(cancel)){
            spinnerH.setValue(H);
            spinnerW.setValue(W);
            human1.setSelected(!whitePlayerType);
            comp1.setSelected(whitePlayerType);
            human2.setSelected(!blackPlayerType);
            comp2.setSelected(blackPlayerType);
            whitePlayerNameText.setText(whitePlayerName);
            blackPlayerNameText.setText(blackPlayerName);
            mainFrame.mainMenu();
        }

    }
}