package eduard.catan;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection = null;
    private String databaseName = "catandb";
    private String url = "jdbc:mysql://localhost:3306/" + databaseName;
    private String username = "root";
    private String password = encryption("Lqfklvrduhd5;");
    private static Database instance = new Database();
    private Database() {

    }

    public void connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static Database getInstance() {
        return instance;
    }

    private String encryption(String text) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            sb.append((char)(-3 + text.charAt(i)));
        }
        return sb.toString();
    }
}
