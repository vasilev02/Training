package lab;

import java.io.File;
import java.util.*;

public class task7 {
    public static void main(String[] args) {
        String path = "D:\\folderDemo\\04. Java-Advanced-Streams-Files-and-" +
                "Directories-Resources\\04. Java-Advanced-Files-and-Stream" +
                "s-Lab-Resources";

        File file = new File(path);

        File[] files = file.listFiles();

        ArrayDeque<File> toTravers = new ArrayDeque<>(Arrays.asList(files));
        int folder = 0;
        while (toTravers.size()>0){
            File current = toTravers.poll();
            if(current.isDirectory()){
                System.out.println(current.getName());
                List<File> fileList = Arrays.asList(current.listFiles());
                toTravers.addAll(fileList);
                folder++;
            }
        }
        System.out.println(folder+" "+"folders");
    }
}
