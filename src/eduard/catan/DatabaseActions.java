package eduard.catan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseActions {
    private Database database = Database.getInstance();
    private Connection conn;
    private int gameNumber;

    public void addGame(int playersNumber) throws SQLException {
        try {
            database.connect();
            conn = database.getConnection();

            int gameIndex = 1;
            PreparedStatement stmt = conn.prepareStatement("select max(game_id) as max from games");
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                gameIndex = resultSet.getInt("max");
            }

            setGameNumber(gameIndex + 1);

            stmt = conn.prepareStatement("ALTER TABLE games AUTO_INCREMENT = ?");
            stmt.setInt(1, gameIndex);
            stmt.executeUpdate();


            stmt = conn.prepareStatement("insert into games(players_nr) values(?)");
            stmt.setInt(1, playersNumber);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
    }

    public void addResults(int gameNumber, String name, int points) {
        try {
            database.connect();
            Connection conn = database.getConnection();
            int player_id;
            PreparedStatement stmt = conn.prepareStatement("select player_id from players where trim(name) = ?");
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if ((resultSet.next())) {
                player_id = resultSet.getInt("player_id");
                stmt = conn.prepareStatement("insert into results values(?, ?, ?)");
                System.out.println("/////// " + gameNumber + "//////////");
                stmt.setInt(1, gameNumber);
                stmt.setInt(2, player_id);
                stmt.setInt(3, points);
                stmt.executeUpdate();
                conn.close();
            }
            else {

                int playerIndex = 1;
                stmt = conn.prepareStatement("select max(player_id) as max from players");
                resultSet = stmt.executeQuery();
                if(resultSet.next()) {
                    playerIndex = resultSet.getInt("max");
                }

                stmt = conn.prepareStatement("ALTER TABLE players AUTO_INCREMENT = ?");
                stmt.setInt(1, playerIndex);
                stmt.executeUpdate();

                stmt = conn.prepareStatement("insert into players(name) values (?)");
                stmt.setString(1, name);
                stmt.executeUpdate();

                stmt = conn.prepareStatement("insert into results values(?, ?, ?)");
                stmt.setInt(1, gameNumber);
                stmt.setInt(2, playerIndex + 1);
                stmt.setInt(3, points);
                stmt.executeUpdate();

                conn.close();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Result> getResults() throws Exception {
        ArrayList<Result> resultsList = new ArrayList<Result>();
        database.connect();
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select games.game_id as \"Game\", players.name as \"Name\", results.points as \"Points\" from games\n" +
                "inner join results on games.game_id = results.game_id  \n" +
                "inner join players on players.player_id = results.player_id\n" +
                "order by games.game_id ");
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            resultsList.add(new Result(resultSet.getInt("Game"), resultSet.getString("Name"), resultSet.getInt("Points")));
        }
        conn.close();
        return resultsList;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }
    public int getGameNumber() {
        return this.gameNumber;
    }
}
