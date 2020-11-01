import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DiabloPlayerInfo {

    public final static String DATABASE_URL = "jdbc:mysql://localhost:3306/diablo";

    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","1234");

        Connection connection = DriverManager.getConnection(DATABASE_URL, properties);

        String query = "SELECT u.user_name,first_name, last_name, COUNT(ug.game_id) AS 'count_games'\n" +
                "FROM users AS u\n" +
                "JOIN users_games ug\n" +
                "on u.id = ug.user_id\n" +
                "WHERE u.user_name = ?\n" +
                "GROUP BY u.user_name;";

        PreparedStatement statement = connection.prepareStatement(query);

        Scanner scanner = new Scanner(System.in);

        String userNameInput = scanner.nextLine();

        statement.setString(1,userNameInput);

        ResultSet result = statement.executeQuery();

        if (result.next()){

            System.out.println("User: " + result.getString("user_name"));
            System.out.printf("%s %s has played %s games",result.getString("first_name")
            ,result.getString("last_name"),result.getInt("count_games"));

        }else{

            System.out.println("No such user exists");

        }

    }
}
