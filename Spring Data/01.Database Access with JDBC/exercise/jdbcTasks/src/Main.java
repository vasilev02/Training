import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);


        Homework homework = new Homework();

        homework.setConnection("root", "1234");

        //homework.getGamesByGivenSalary(salary);
        //homework.getPlayerInfo("nakov");
        //homework.getVillainsNames();
        //homework.getMinionsNames(1);
        //homework.changeTownNameUpper("Bulgaria");
        //homework.printAllMinionsNames();
        //homework.increaseMinionsAge("2 1 4");
        //homework.increaseAge(1);
        //homework.removeVillain(4);

    }
}
