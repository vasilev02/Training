package lab;

import java.util.*;

public class task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String,Map<String,List<String>>> mapping = new LinkedHashMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {
            String[] data = scanner.nextLine().split("\\s+");
            String continent = data[0];
            String country = data[1];
            String town = data[2];

            if(mapping.containsKey(continent)){
                Map<String,List<String>> current = mapping.get(continent);

                if(current.containsKey(country)){
                    List<String> list = current.get(country);
                    list.add(town);
                    current.put(country,list);
                    mapping.put(continent,current);
                }else{
                    List<String> list = new ArrayList<>();
                    list.add(town);
                    current.put(country,list);
                    mapping.put(continent,current);
                }


            }else{
                Map<String,List<String>> current = new LinkedHashMap<>();
                List<String> list = new ArrayList<>();
                list.add(town);
                current.put(country,list);
                mapping.put(continent,current);
            }

        }

        mapping.entrySet().stream().forEach(entry->{

            System.out.printf("%s:%n",entry.getKey());

            Map<String,List<String>> countries = mapping.get(entry.getKey());

            for (Map.Entry<String, List<String>> currentTown : countries.entrySet()) {
                System.out.printf("  %s -> ",currentTown.getKey());

                List<String> cities = countries.get(currentTown.getKey());
                for (int i = 0; i <cities.size() ; i++) {
                    if(i==cities.size()-1){
                        System.out.println(cities.get(i));
                    }else{
                        System.out.print(cities.get(i)+", ");
                    }

                }

            }


        });


    }
}
