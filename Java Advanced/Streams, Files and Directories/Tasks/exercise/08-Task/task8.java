package exercise;

import java.io.File;

public class task8 {
    public static void main(String[] args) {

        String path = "D:\\folderDemo\\04. Java-Advanced-Files-and-" +
                "Streams-Exercises-Resources\\Exercises Resources";

        File file = new File(path);

        File[] files = file.listFiles();

        int sum=0;
        for (int i = 0; i <files.length ; i++) {
            sum+=files[i].length();
        }
        System.out.printf("Folder size: %d",sum);
    }
}
