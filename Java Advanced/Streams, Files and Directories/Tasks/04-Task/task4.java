package lab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        String path = "D:\\folderDemo\\04. Java-Advanced-Streams-Fil" +
                "es-and-Directories-Resources\\04. Java-Advanced-Files-an" +
                "d-Streams-Lab-Resources"+"\\input.txt";

        String writePath = "D:\\folderDemo\\04. Java-Advanced-Streams-Fi" +
                "les-and-Directories-Resources\\04. Java-Advanced-Fi" +
                "les-and-Streams-Lab-Resources"+"\\writePathDemo.txt";


        try (FileInputStream streamIn = new FileInputStream(path);
             FileOutputStream streamOut = new FileOutputStream(writePath)){

            Scanner scanner = new Scanner(streamIn);
            PrintStream writer = new PrintStream(streamOut);
            PrintStream consolePrinter = new PrintStream(System.out);

            while (scanner.hasNext()){
                if(scanner.hasNextInt()){
                    int number = scanner.nextInt();
                    writer.println(number);
                }
                scanner.next();
            }

        }catch (IOException e){
            System.out.println("bad");
        }


    }
}
