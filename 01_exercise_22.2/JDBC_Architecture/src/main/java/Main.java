//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Main {
//
//    private static final String URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=usb19pgs80";
//
//    private static Connection con;
//
//    public static void main(String[] args) {
//        try {
//            con = DriverManager.getConnection(URL);
//            con.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//}

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost/testdb?user=postgres&password=usb19pgs80";

    private static String conok="Соединение с бд установлено";
    private static String conerr="Произошла ошибка подключения к бд";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL)){
            System.out.printf("%s%n",conok);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM test";
            boolean isExecuted=statement.execute(sql);
            if (isExecuted){
                System.out.println("Select executed");
                ResultSet resultSet = statement.executeQuery(sql);
                System.out.println("ID");
                System.out.println("||------------||");
                while (resultSet.next()){
                    System.out.println(resultSet.getInt("ID"));
                }
                System.out.println("||------------||");
                resultSet.close();
            }
            statement.close();
//            connection.close(); закроется автоматически т.к. открытие происходит в блоке try-with-resources
        } catch (SQLException e) {
            System.out.printf("%s%n",conerr);
            e.printStackTrace();
        }
    }
}