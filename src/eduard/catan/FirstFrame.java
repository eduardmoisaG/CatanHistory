package eduard.catan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class FirstFrame extends JFrame implements ActionListener {

    private MenuBar menuBar;
    private Menu meniu;
    private PlayersNumberFrame playersNumberFrame;
    public FirstFrame(String titlu) {
        super(titlu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(300, 150);

        menuBar = new MenuBar();

        meniu = new Menu("Options");
        meniu.add(new MenuItem("AddGame"));
        meniu.add(new MenuItem("History"));
        menuBar.add(meniu);
        meniu.addActionListener(this);
        this.setMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("AddGame")) {
            playersNumberFrame = new PlayersNumberFrame();
        }
        if (command.equals("History")) {
            try {
                new HistoryFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public PlayersNumberFrame getPlayersNumberFrame() {
        return playersNumberFrame;
    }

    public void getPlayersNumber() {
        if (playersNumberFrame != null) {
            if (playersNumberFrame.getCatanGame() != null) {
                System.out.println(playersNumberFrame.getCatanGame().getPlayersNumber());
            }
        }
    }
}
