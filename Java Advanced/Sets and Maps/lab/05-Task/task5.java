package lab;

import java.util.*;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<Double>> map = new TreeMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {
            String[] data = scanner.nextLine().split("\\s+");
            String name = data[0];
            double grade = Double.parseDouble(data[1]);
            
            if(map.containsKey(name)){
                List<Double> list = map.get(name);
                list.add(grade);
                map.put(name,list);
            }else{
                List<Double> list = new ArrayList<>();
                list.add(grade);
                map.put(name,list);
            }
            
        }

        for (Map.Entry<String, List<Double>> current : map.entrySet()) {
            System.out.printf("%s -> ",current.getKey());
            List<Double> list = current.getValue();
            double sum = 0.0;
            for (Double currGrade : list) {
                System.out.printf("%.2f ",currGrade);
                sum+=currGrade;
            }
            System.out.printf("(avg: %.2f)%n",sum/list.size());

        }

    }
}
