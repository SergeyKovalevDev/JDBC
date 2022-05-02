import java.sql.*;
import java.util.Scanner;

public class Main {
    private final static String HOST = "localhost"  ; // сервер базы данных
    private final static String DATABASE_NAME = "testdb"  ;// имя базы
    private final static String USERNAME = "postgres"; // учетная запись пользователя
    private final static String PASSWORD = "usb19pgs80"; // пароль пользователя
    static Connection connection;

    public static void main(String[] args){

        //Строка для соединения с бд
        String url="jdbc:postgresql://"+HOST+"/"+ DATABASE_NAME +"?user="+USERNAME+"&password="+PASSWORD;
        try {
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            if (connection == null)
                System.err.println("Нет соединения с БД!");
            else {
                System.out.println("Соединение с БД установлено корректно");
                if(checkValue(new Scanner(System.in).nextInt())){
                    System.out.println("Число есть в таблице");
                }else{
                    System.out.println("Число отсутствует в таблице");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkValue(int checkedValue){
        String SQL = "Select * from test where ID=?;";
        try(PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, checkedValue);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}