package exercise;

import java.io.*;
import java.util.*;

public class task6 {
    public static void main(String[] args) {

        String pathWords = "D:\\folderDemo\\04. Java-Advanced-Files-and" +
                "-Streams-Exercises-Resources"+"\\words.txt";

        String pathText = "D:\\folderDemo\\04. Java-Advanced-Files-and" +
                "-Streams-Exercises-Resources"+"\\text.txt";

        String printFolder = "D:\\folderDemo\\04. Java-Advanced-Files" +
                "-and-Streams-Exercises-Resources"+"\\judge.txt";

        try (FileReader streamInWords = new FileReader(pathWords);
             FileReader streamInText = new FileReader(pathText);
             FileWriter streamOut = new FileWriter(printFolder)){

            BufferedReader wordsReader = new BufferedReader(streamInWords);
            BufferedReader textReader = new BufferedReader(streamInText);
            PrintWriter writer = new PrintWriter(streamOut);

            Map<String,Integer> mapping = new HashMap<>();

            Set<String> words = new HashSet<>();
            String[] wordsFulFill = wordsReader.readLine().split("\\s+");
            words.addAll(Arrays.asList(wordsFulFill));

            String line = textReader.readLine();
            while (line!=null){

                String[] wordsToCheck = line.split("\\s+");

                for (int i = 0; i <wordsToCheck.length ; i++) {
                    String curr = wordsToCheck[i];
                    if(words.contains(curr)){

                        if(mapping.containsKey(curr)){
                            Integer value = mapping.get(curr);
                            mapping.put(curr,value+1);
                        }else{
                            mapping.put(curr,1);
                        }

                    }

                }

                line = textReader.readLine();
            }

            mapping.entrySet().stream().sorted((a,b)->{

                int result = b.getValue().compareTo(a.getValue());
                return result;
            }).forEach(entry->{

                writer.printf("%s - %d%n",entry.getKey(),entry.getValue());

            });


        }catch (IOException e){
            System.out.println("error");
        }


    }
}
