import java.sql.*;

public class TransactionsTest {
    private final Settings settings;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public TransactionsTest() {
        settings = new Settings();
        this.init();
    }

    private void init() {
        settings.load(Settings.class.getClassLoader().getResourceAsStream("db.properties"));
        this.dbUrl = this.settings.getProperty("DB_URL");
        this.dbUser = this.settings.getProperty("DB_USER");
        this.dbPassword = this.settings.getProperty("DB_PASSWORD");
    }

    public boolean transactionInsertIntoItems() {
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword)) {
            connection.setAutoCommit(false);
            System.out.println("Соединение с БД установлено корректно");
            String sqlQuery = "INSERT INTO items (item_name, description) VALUES (?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(1, "box");
                preparedStatement.setString(2, "best box");
//                preparedStatement.executeUpdate();
                preparedStatement.addBatch();

                preparedStatement.setString(1, "lamp");
                preparedStatement.setString(2, "worst lamp");
//                preparedStatement.executeUpdate();
                preparedStatement.addBatch();

                int[] count = preparedStatement.executeBatch();
                connection.commit();
                System.out.println("Transaction OK");
                return true;
            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Transaction error. Changes rollback");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Нет соединения с БД!");
            return false;
        }
    }

    public static void main(String[] args) {
        TransactionsTest tt = new TransactionsTest();
        System.out.println(tt.transactionInsertIntoItems());
    }
}
