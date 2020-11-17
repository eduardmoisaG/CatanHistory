package eduard.catan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class HistoryFrame extends JFrame {
    private JTable table;
    DefaultTableModel tableModel;
    DatabaseActions databaseActions = new DatabaseActions();
    ArrayList<Result> resultsHistory;
    public HistoryFrame() throws Exception {
        super("Catan history of all time");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(300, 400);

        resultsHistory = databaseActions.getResults();
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setFont(new Font("Serif", Font.BOLD, 15));
        table.setRowHeight(15);
        tableModel.addColumn("Game");
        tableModel.addColumn("Name");
        tableModel.addColumn("Points");
        int i = 0;
        int resultId = 0;
        for (Result result : resultsHistory) {

            if (result.getGameId()!= resultId) {
                tableModel.insertRow(i, new Object[] {"Jocul: " + result.getGameId()});
                i++;
                resultId = result.getGameId();
            }
            tableModel.insertRow(i, new Object[] {"   ", result.getName(), result.getPoints()});
            i++;


        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);


    }
}
