package exercise;

import java.io.*;

public class task7 {
    public static void main(String[] args) {

        String pathOne = "D:\\folderDemo\\04. Java-Advanced-Files-and-" +
                "Streams-Exercises-Resources"+"\\inputOne.txt";

        String pathTwo = "D:\\folderDemo\\04. Java-Advanced-Files-and-" +
                "Streams-Exercises-Resources"+"\\inputTwo.txt";

        String printFolder = "D:\\folderDemo\\04. Java-Advanced-Files-and-" +
                "Streams-Exercises-Resources"+"\\judge.txt";


        try (FileReader firstStreamIn = new FileReader(pathOne);
             FileReader secondStreamIn = new FileReader(pathTwo);
             FileWriter streamOut = new FileWriter(printFolder)){

            BufferedReader readerOne = new BufferedReader(firstStreamIn);
            BufferedReader readerTwo = new BufferedReader(secondStreamIn);
            PrintWriter writer = new PrintWriter(streamOut);

            String line = readerOne.readLine();
            while (line!=null){
                writer.println(line);
                line = readerOne.readLine();
            }

            String line2 = readerTwo.readLine();
            while (line2!=null){
                writer.println(line2);
                line2 = readerTwo.readLine();
            }


        }catch (IOException e){
            System.out.println("error");
        }

    }
}
