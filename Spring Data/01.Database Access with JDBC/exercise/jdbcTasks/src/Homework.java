import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Homework {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";
    private Connection connection;

    public void setConnection(String user, String password) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, properties);

    }


    public void getGamesByGivenSalary(double salaryInput) throws SQLException {

        String query = "SELECT first_name, last_name\n" +
                "FROM employees\n" +
                "WHERE salary > ?;";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setDouble(1, salaryInput);

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            System.out.printf(result.getString("first_name") + " " + result.getString("last_name")).println();

        }

    }

    public void getPlayerInfo(String userInput) throws SQLException {

        String query = "SELECT u.user_name,first_name, last_name, COUNT(ug.game_id) AS 'count_games'\n" +
                "FROM users AS u\n" +
                "JOIN users_games ug\n" +
                "on u.id = ug.user_id\n" +
                "WHERE u.user_name = ?\n" +
                "GROUP BY u.user_name;";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, userInput);

        ResultSet result = statement.executeQuery();

        if (result.next()) {

            System.out.println("User: " + result.getString("user_name"));
            System.out.printf("%s %s has played %s games", result.getString("first_name")
                    , result.getString("last_name"), result.getInt("count_games"));

        } else {

            System.out.println("No such user exists");

        }

    }

    public void getVillainsNames() throws SQLException {

        String query = "SELECT v.name, COUNT(mv.minion_id) AS `count`\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains mv\n" +
                "on v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING `count` > 15\n" +
                "ORDER BY `count` DESC;";

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            System.out.printf("%s %s", result.getString("name"), result.getInt("count"))
                    .println();

        }

    }

    public void getMinionsNames(int inputId) throws SQLException {

        String query = "SELECT name\n" +
                "FROM villains\n" +
                "WHERE id = ?;";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, inputId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {

            System.out.printf("Villain: %s", result.getString("name")).println();

            String queryForMinions = "SELECT m.name, m.age\n" +
                    "FROM minions AS m\n" +
                    "JOIN minions_villains mv\n" +
                    "on m.id = mv.minion_id\n" +
                    "JOIN villains v\n" +
                    "on mv.villain_id = v.id\n" +
                    "WHERE v.id = ?;";

            PreparedStatement statementToFindMinions = connection.prepareStatement(queryForMinions);

            statementToFindMinions.setInt(1, inputId);

            ResultSet resultNames = statementToFindMinions.executeQuery();

            int index = 1;

            while (resultNames.next()) {

                System.out.printf("%d. %s %d",
                        index,
                        resultNames.getString("name"),
                        resultNames.getInt("age"))
                        .println();

                index++;

            }

        } else {

            System.out.println("No villain with ID " + inputId + " exists in the database.");

        }

    }

    public void changeTownNameUpper(String countryInput) throws SQLException {

        String query = "SELECT name\n" +
                "FROM towns\n" +
                "WHERE country = ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, countryInput);

        ResultSet result = statement.executeQuery();

        String updateQuery = "UPDATE `towns` AS t\n" +
                "SET t.name = UPPER(t.name)\n" +
                "WHERE t.country = ?;";

        if (result.next()) {

            PreparedStatement statementToUpdateTowns = connection.prepareStatement(updateQuery);

            statementToUpdateTowns.setString(1, countryInput);

            statementToUpdateTowns.executeUpdate();

            String findCountOfTownsQuery = "SELECT COUNT(*) AS `count`\n" +
                    "FROM `towns` AS t\n" +
                    "WHERE t.country = ?;";

            PreparedStatement statementForCountOfTowns = connection.prepareStatement(findCountOfTownsQuery);

            statementForCountOfTowns.setString(1, countryInput);

            ResultSet resultCount = statementForCountOfTowns.executeQuery();
            resultCount.next();

            int resultNumber = resultCount.getInt(1);

            System.out.printf("%d town names were affected.", resultNumber).println();


            String printTownsQuery = "SELECT t.name\n" +
                    "FROM `towns` AS t\n" +
                    "WHERE t.country = ?;";

            PreparedStatement statementForPrintingTowns = connection.prepareStatement(printTownsQuery);

            statementForPrintingTowns.setString(1, countryInput);

            ResultSet resultTowns = statementForPrintingTowns.executeQuery();

            LinkedList<String> towns = new LinkedList<>();

            while (resultTowns.next()) {

                towns.add(resultTowns.getString("name"));

            }

            System.out.print("[");
            System.out.print(String.join(", ", towns));
            System.out.print("]");


        } else {

            System.out.println("No town names were affected.");

        }

    }

    public void printAllMinionsNames() throws SQLException {

        String query = "SELECT name\n" +
                "FROM `minions`;";

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet result = statement.executeQuery();

        List<String> names = new ArrayList<>();
        int index = 1;

        while (result.next()) {

            if (index % 2 != 0) {

                names.add(result.getString("name"));

            } else {

                names.add(names.size(), result.getString("name"));

            }

            index++;
        }

        for (int i = 0; i < names.size(); i++) {

            System.out.println(names.get(i));

        }


    }

    public void increaseMinionsAge(String dataInput) throws SQLException {

        String[] ids = dataInput.split(" ");

        String query = "UPDATE `minions`\n" +
                "SET name = LOWER(name),age = age + 1\n" +
                "WHERE id = ?;";

        PreparedStatement statement = connection.prepareStatement(query);

        for (int i = 0; i <ids.length ; i++) {

            int id = Integer.parseInt(ids[i]);

            statement.setInt(1,id);

            statement.executeUpdate();
        }

        String queryGetAllNames = "SELECT name, age\n" +
                "FROM `minions`;";

        PreparedStatement statementForNames = connection.prepareStatement(queryGetAllNames);

        ResultSet result = statementForNames.executeQuery();


        while (result.next()){

            System.out.printf("%s %d",result.getString("name"),result.getInt("age"))
                    .println();

        }

    }

    public void increaseAge(int inputId) throws SQLException {

        String query = "CALL usp_get_older(?)";

        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, inputId);

        callableStatement.execute();

    }

    public void removeVillain(int idInput) throws SQLException {

        String query = "SELECT name\n" +
                "FROM villains\n" +
                "WHERE id = ?;";

        String queryReleasedMinions = "SELECT COUNT(minion_id) AS `count`\n" +
                "FROM minions_villains\n" +
                "WHERE villain_id = ?;";

        String deleteFromMappingTable = "DELETE FROM `minions_villains`\n" +
                "WHERE villain_id = ?;";

        String deleteFromVillainTable = "DELETE FROM `villains`\n" +
                "WHERE id = ?;";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, idInput);

        ResultSet result = statement.executeQuery();

        if (result.next()){

            System.out.printf("%s was deleted",result.getString("name")).println();

            PreparedStatement releasedMinionsStatement = connection.prepareStatement(queryReleasedMinions);
            releasedMinionsStatement.setInt(1, idInput);
            ResultSet resultCount = releasedMinionsStatement.executeQuery();

            resultCount.next();

            System.out.printf("%d minions released",resultCount.getInt("count"));

            PreparedStatement deleteStatementFromMappingTable = connection.prepareStatement(deleteFromMappingTable);
            deleteStatementFromMappingTable.setInt(1, idInput);
            deleteStatementFromMappingTable.executeUpdate();

            PreparedStatement deleteStatementFromVillainTable = connection.prepareStatement(deleteFromVillainTable);
            deleteStatementFromVillainTable.setInt(1, idInput);
            deleteStatementFromVillainTable.executeUpdate();


        }else{

            System.out.println("No such villain was found");

        }

    }
}
