package exercise;

import java.io.*;

public class task1 {
    public static void main(String[] args) {

        String path = "D:\\folderDemo\\04. Java-Advanced-Files-and-Streams" +
                "-Exercises-Resources"+"\\input.txt";

        String printFolder = "D:\\folderDemo\\04. Java-Advanced-Files-and-Streams-" +
                "Exercises-Resources"+"\\judge.txt";


        try (FileReader streamIn = new FileReader(path);
             FileWriter streamOut = new FileWriter(printFolder)){

            BufferedReader reader = new BufferedReader(streamIn);
            PrintWriter writer = new PrintWriter(streamOut);

            String line = reader.readLine();
            long sum = 0;
            while (line!=null){
                String[] letters = line.split("");

                for (int i = 0; i <letters.length ; i++) {
                    int current = letters[i].charAt(0);
                    sum+=current;
                }

                line = reader.readLine();
            }
            writer.println(sum);


        }catch (IOException e){
            System.out.println("error");
        }

    }
}
