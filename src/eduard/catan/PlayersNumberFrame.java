package eduard.catan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PlayersNumberFrame extends JFrame implements ActionListener {
    private CatanGame catanGame;
    private DatabaseActions databaseActions = new DatabaseActions();
    private JTextField numberOfPlayers;
    private JButton okButton;
    public PlayersNumberFrame() {
        super("Players number");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 1;
        gc.gridy = 1;
        add(new JLabel("Insert players number: "), gc);

        numberOfPlayers = new JTextField(2);
        gc.gridx = 2;
        gc.gridy = 1;
        add(numberOfPlayers, gc);

        okButton = new JButton("Ok");
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.insets = new Insets(10, 0, 0, 0);
        add(okButton, gc);

        okButton.addActionListener(this);
        setSize(200, 160);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String players = numberOfPlayers.getText();
        int intValue = 0;
        if (players.length() != 0) {
            intValue = Integer.parseInt(players);
        }
        if (players.length() != 0 && intValue != 0) {
            try {
                databaseActions.addGame(intValue);
            } catch (SQLException ex) {
                System.err.println("Probleme cu baza de date!");
            }
            catanGame = new CatanGame(intValue, databaseActions.getGameNumber());
            new AddPlayerFrame(catanGame);



            this.dispose();
        }
    }


    public void setCatanGame(CatanGame catanGame) {
        this.catanGame = catanGame;
    }

    public CatanGame getCatanGame() {
        return catanGame;
    }
}
