package exercise;

import javax.security.sasl.SaslClient;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,String> mapping = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("stop")){

            String mail = scanner.nextLine();
            if(mail.endsWith("uk")||mail.endsWith("com")||mail.endsWith("us")){
                input = scanner.nextLine();
                continue;
            }else{
                mapping.put(input,mail);
            }

            input = scanner.nextLine();
        }

        mapping.entrySet().stream().forEach(entry->{

            System.out.printf("%s -> %s%n",entry.getKey(),entry.getValue());

        });

    }
}
