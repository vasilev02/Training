package exercise;

import java.io.*;

public class task5 {
    public static void main(String[] args) {

        String path = "D:\\folderDemo\\04. Java-Advanced-Files-" +
                "and-Streams-Exercises-Resources"+"\\inputLineNumbers.txt";

        String printFolder = "D:\\folderDemo\\04. Java-Advanced-File" +
                "s-and-Streams-Exercises-Resources"+"\\judge.txt";

        try (FileReader streamIn = new FileReader(path);
             FileWriter streamOut = new FileWriter(printFolder)){

            BufferedReader reader = new BufferedReader(streamIn);
            PrintWriter writer = new PrintWriter(streamOut);

            int counter= 1;

            String line = reader.readLine();
            while (line!=null){

                writer.printf("%d. %s%n",counter,line);
                counter++;
                line = reader.readLine();
            }


        }catch (IOException e){
            System.out.println("error");
        }

    }
}
