import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public final static String DATABASE_URL = "jdbc:mysql://localhost:3306/soft_uni";

    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","1234");

        Connection connection = DriverManager.getConnection(DATABASE_URL, properties);

        String query = "SELECT first_name, last_name\n" +
                "FROM employees\n" +
                "WHERE salary > ?;";

        PreparedStatement statement = connection.prepareStatement(query);

        Scanner scanner = new Scanner(System.in);

        String salary = scanner.nextLine();
        statement.setDouble(1, Double.parseDouble(salary));


        ResultSet result = statement.executeQuery();

        while (result.next()){

            System.out.printf(result.getString("first_name") + " " + result.getString("last_name")).println();

        }

    }
}
