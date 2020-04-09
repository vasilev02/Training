package lab;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class task6 {
    public static void main(String[] args) {

        String path = "D:\\folderDemo\\04. Java-Advan" +
                "ced-Streams-Files-and-Directories-Resou" +
                "rces\\04. Java-Advanced-Files-and-Streams-Lab-Resources"+"\\input.txt";

        String printFolder = "D:\\folderDemo\\04. Java-Advanced-Strea" +
                "ms-Files-and-Directories-Resources\\04. Java-Advanced-F" +
                "iles-and-Streams-Lab-Resources"+"\\writePathDemo.txt";

        try (FileReader streamIn = new FileReader(path);
             FileWriter streamOut = new FileWriter(printFolder)){

            BufferedReader reader = new BufferedReader(streamIn);
            PrintWriter writer = new PrintWriter(streamOut);
            List<String> data = new ArrayList<>();
            String line = reader.readLine();
            while (line!=null){
                data.add(line);
                line = reader.readLine();
            }

            Collections.sort(data);

            for (int i = 0; i <data.size() ; i++) {
                writer.println(data.get(i));
            }

        }catch (IOException e){
            System.out.println("error");
        }

    }
}
