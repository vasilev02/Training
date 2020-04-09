package lab;

import java.io.FileInputStream;
import java.io.IOException;

public class task3 {
    public static void main(String[] args) {

        String path = "D:\\folderDemo\\04. Java-Advanced-Streams-Files" +
                "-and-Directories-Resources\\04. Java-Advanced" +
                "-Files-and-Streams-Lab-Resources"+"\\input.txt";

        try (FileInputStream stream = new FileInputStream(path)){

            int oneByte = stream.read();

            while (oneByte>=0){

                if(oneByte==10 || oneByte==32){
                    System.out.print((char)oneByte);
                }else{
                    System.out.print(oneByte);
                }
                oneByte = stream.read();
            }

        }catch (IOException e){
            System.out.println("Bad");
        }

    }
}
