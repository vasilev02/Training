package exercise;

import java.io.*;

public class task3 {
    public static void main(String[] args) {

        String path = "D:\\folderDemo\\04. Java-Advanced-Files-and-Streams-" +
                "Exercises-Resources"+"\\input.txt";

        String printFolder = "D:\\folderDemo\\04. Java-Advanced-Files" +
                "-and-Streams-Exercises-Resources"+"\\judge.txt";

        try (FileReader streamIn = new FileReader(path);
             FileWriter streamOut = new FileWriter(printFolder) ){

            BufferedReader reader = new BufferedReader(streamIn);
            PrintWriter writer = new PrintWriter(streamOut);

            String line = reader.readLine();
            while (line!=null){
                writer.println(line.toUpperCase());
                line = reader.readLine();
            }

        }catch (IOException e){
            System.out.println("error");
        }

    }
}
