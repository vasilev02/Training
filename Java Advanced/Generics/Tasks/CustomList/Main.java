package customList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        CustomList<String> list = new CustomList<>();

        String[] command = buff.readLine().split("\\s+");

        while (!command[0].equals("END")){

            switch (command[0]){
                case"Add":
                    list.add(command[1]);
                    break;

                case "Remove":
                    list.remove(Integer.parseInt(command[1]));
                    break;

                case "Contains":
                    list.contains(command[1]);
                    break;

                case "Swap":
                    list.swap(Integer.parseInt(command[1]),Integer.parseInt(command[2]));
                    break;

                case "Greater":
                    list.countGreaterThan(command[1]);
                    break;

                case"Max":
                    list.getMax();
                    break;

                case"Min":
                    list.getMin();
                    break;

                case"Print":
                    list.Print();
                    break;

                case"Sort":
                    list.sort();
                    break;
            }

            command = buff.readLine().split("\\s+");
        }

    }
}
