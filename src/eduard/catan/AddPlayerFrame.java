package eduard.catan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerFrame extends JFrame implements ActionListener {
    private DatabaseActions databaseActions = new DatabaseActions();
    private CatanGame catanGame;
    private int playersCount = 0;
    JTextField name, points;
    JButton insertButton;

    public AddPlayerFrame(CatanGame catanGame) {
        super("Add user");

        this.catanGame = catanGame;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(250, 200);
        setVisible(true);
        name = new JTextField(12);
        points = new JTextField(2);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 1;
        add(new JLabel("Nume: "), gc);

        gc.gridx = 2;
        gc.gridy = 1;
        add(name, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(new JLabel("Puncte: "), gc);

        gc.gridx = 2;
        gc.gridy = 2;
        add(points, gc);

        insertButton = new JButton("Insert");

        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(20, 0, 0, 0);
        add(insertButton, gc);

        insertButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playersCount < catanGame.getPlayersNumber()) {
            databaseActions.addResults(catanGame.getGameNumber(), name.getText(), Integer.parseInt(points.getText()));
            playersCount++;
            name.setText("");
            points.setText("");
        } else {
            this.dispose();
        }

    }
}

