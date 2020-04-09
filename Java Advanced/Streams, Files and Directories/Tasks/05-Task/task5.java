package lab;

import java.io.*;

public class task5 {

    public static void main(String[] args) {

        String path = "D:\\folderDemo\\04. Java-Advanced-Streams-Fil" +
                "es-and-Directories-Resources\\04. Java-Advanced-F" +
                "iles-and-Streams-Lab-Resources"+"\\input.txt";

        String printFolder = "D:\\folderDemo\\04. Java-Advanced-Streams-Files-a" +
                "nd-Directories-Resources\\04. Java-Advanced-Files-an" +
                "d-Streams-Lab-Resources"+"\\writePathDemo.txt";

        try (FileReader streamIn = new FileReader(path);
             FileWriter streamOut = new FileWriter(printFolder)){

            BufferedReader reader = new BufferedReader(streamIn);
            PrintWriter writer = new PrintWriter(streamOut);

            int counter=1;
            String line = reader.readLine();
            while (line!=null){
                if(counter%3==0){
                    writer.println(line);
                }
                counter++;
                line = reader.readLine();
            }


        }catch (IOException e){
            System.out.println("error");
        }

    }

}
