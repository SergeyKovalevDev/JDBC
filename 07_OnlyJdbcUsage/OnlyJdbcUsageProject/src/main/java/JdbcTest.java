import java.sql.*;

public class JdbcTest {
    private final Settings settings;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JdbcTest() {
        settings = new Settings();
        this.init();
    }

    private void init() {
        settings.load(Settings.class.getClassLoader().getResourceAsStream("db.properties"));
        this.dbUrl = this.settings.getProperty("DB_URL");
        this.dbUser = this.settings.getProperty("DB_USER");
        this.dbPassword = this.settings.getProperty("DB_PASSWORD");
    }

    public boolean testConnection() {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean createItemTable() {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
        Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS items (" +
                        "id serial," +
                        "item_name varchar(200)," +
                        "description varchar(2000)," +
                        "create_time timestamp default now()," +
                        "PRIMARY KEY (id))");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean dropItemTable() {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS items CASCADE");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public int insertIntoItems() {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate("INSERT INTO items (item_name, description) VALUES ('tvset', 'the best tvset')");
        } catch (SQLException e) {
            return -1;
        }
    }

    public boolean selectFromItems() {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM items WHERE item_name = 'tvset'";
            if (statement.execute(sqlQuery)) {
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    System.out.print(resultSet.getInt("id") + "\t");
                    System.out.print(resultSet.getString("item_name") + "\t");
                    System.out.print(resultSet.getString("description") + "\t");
                    System.out.println(resultSet.getTimestamp("create_time"));
                }
                resultSet.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    // Метод Statement складывает строки значений и строки запроса.
    // А вот PreparedStatement имеет по сути шаблон запроса, и данные в него вставляются с учётом кавычек.
    // Данный метод позволяет защититься от SQL инъекций.
    public boolean preparedStatementSelectFromItems(int id) {
        String sqlQuery = "SELECT * FROM items WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id") + "\t");
                System.out.print(resultSet.getString("item_name") + "\t");
                System.out.print(resultSet.getString("description") + "\t");
                System.out.println(resultSet.getTimestamp("create_time"));
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void main(String[] args) throws SQLException {
        JdbcTest jdbcTest = new JdbcTest();
        System.out.println(jdbcTest.testConnection() ? "Connection OK" : "Connection error");
//        System.out.println(jdbcTest.createItemTable() ? "Table created" : "Table creating error");
//        System.out.println(jdbcTest.dropItemTable() ? "Table dropped" : "Table dropping error");
//        int rows = jdbcTest.insertIntoItems();
//        System.out.println(rows == -1 ? "Insert error" : rows + " rows changed");
        System.out.println(jdbcTest.selectFromItems() ? "Query OK" : "Query error");
        System.out.println(jdbcTest.preparedStatementSelectFromItems(2) ? "Prepared query OK" : "Prepared query error");
    }
}
