package lab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class task2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String path = "D:\\folderDemo\\04. Java-Advanced-Streams-" +
                "Files-and-Directories-Resources\\04." +
                " Java-Advanced-Files-and-Streams-Lab-Resources"+"\\input.txt";

        try (FileInputStream stream = new FileInputStream(path)){

            Set<String> symbols = new HashSet<>();
            symbols.add(",");
            symbols.add(".");
            symbols.add("!");
            symbols.add("?");
            int oneByte = stream.read();
            while (oneByte>=0){

                String current = (char)oneByte+"";
                if(!symbols.contains(current)){
                    System.out.print(current);
                }
                oneByte = stream.read();
            }

        }catch (IOException e){
            System.out.println("BAD");
        }

    }
}
