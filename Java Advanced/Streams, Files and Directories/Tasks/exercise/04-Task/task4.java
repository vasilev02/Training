package exercise;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class task4 {
    public static void main(String[] args) {

        String path = "D:\\folderDemo\\04. Java-Advanced-Files-" +
                "and-Streams-Exercises-Resources"+"\\input.txt";

        String printFolder = "D:\\folderDemo\\04. Java-Advanced-Files-" +
                "and-Streams-Exercises-Resources"+"\\judge.txt";

        try (FileReader streamIn = new FileReader(path);
             FileWriter streamOut = new FileWriter(printFolder)){

            BufferedReader reader = new BufferedReader(streamIn);
            PrintWriter writer = new PrintWriter(streamOut);

            int counterVowels = 0;
            int counterPunkt = 0;
            int others = 0;

            Set<Character> vowels = new HashSet<>();
            vowels.add('a');
            vowels.add('e');
            vowels.add('i');
            vowels.add('o');
            vowels.add('u');

            Set<Character> punctuations = new HashSet<>();
            punctuations.add(',');
            punctuations.add('.');
            punctuations.add('!');
            punctuations.add('?');

            String line = reader.readLine();
            while (line!=null){

                String[] letters = line.split("");

                for (int i = 0; i <letters.length ; i++) {
                    char current = letters[i].charAt(0);
                    if(punctuations.contains(current)){
                        counterPunkt++;
                    }else if(vowels.contains(current)){
                        counterVowels++;
                    }else{
                        if(current!=' '){
                            others++;
                        }
                    }

                }

                line = reader.readLine();
            }

            writer.printf("Vowels: %d%n",counterVowels);
            writer.printf("Consonants: %d%n",others);
            writer.printf("Punctuation: %d",counterPunkt);


        }catch (IOException e){
            System.out.println("error");
        }

    }
}
