package dragonArmy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<Dragon>> allDragons = new LinkedHashMap<>();

        int n = Integer.parseInt(buff.readLine());

        for (int i = 0; i <n ; i++) {

            String[] dragonData = buff.readLine().split("\\s+");

            String type = dragonData[0];
            String name = dragonData[1];
            int damage = 45;
            if(!dragonData[2].equals("null")) {
                damage = Integer.parseInt(dragonData[2]);
            }

            int health = 250;
            if(!dragonData[3].equals("null")) {
                health = Integer.parseInt(dragonData[3]);
            }

            int armor = 10;
            if(!dragonData[4].equals("null")) {
                armor = Integer.parseInt(dragonData[4]);
            }

            Dragon dragon = new Dragon(type,name,damage,health,armor);

            //TODO DOING MAP
            if(allDragons.containsKey(type)){

                List<Dragon> list = allDragons.get(type);

                boolean checker = false;

                for (int j = 0; j <list.size() ; j++) {

                    Dragon current = list.get(j);

                    if(current.getName().equals(dragon.getName())){
                        list.set(j,dragon);
                        checker = true;
                    }
                }
                if(checker == false){
                    list.add(dragon);
                }

            }else{

                List<Dragon> list = new ArrayList<>();
                list.add(dragon);
                allDragons.put(type,list);

            }

        }

        for (Map.Entry<String, List<Dragon>> currentType : allDragons.entrySet()) {

            List<Dragon> list = currentType.getValue();

            double damage = getDamageValue(list);
            double health = getHealthValue(list);
            double armor = getArmorValue(list);

            System.out.printf("%s::(%.2f/%.2f/%.2f)%n",currentType.getKey(),damage,health,armor);

            list.stream().sorted((a,b) -> {

                return a.getName().compareTo(b.getName());

            }).forEach(e -> {

                System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n",e.getName(),e.getDamage(),
                        e.getHealth(),e.getArmor());

            });

        }


    }

    private static double getArmorValue(List<Dragon> list) {

        double sum = 0.0;

        for (Dragon dragon : list) {
            sum+=dragon.getArmor();
        }
        return sum/list.size();

    }

    private static double getHealthValue(List<Dragon> list) {

        double sum = 0.0;

        for (Dragon dragon : list) {
            sum+=dragon.getHealth();
        }
        return sum/list.size();
    }


    private static double getDamageValue(List<Dragon> list) {

        double sum = 0.0;

        for (Dragon dragon : list) {
            sum+=dragon.getDamage();
        }
        return sum/list.size();
    }

    private static class Dragon {

        private String type;
        private String name;
        private int damage;
        private int health;
        private int armor;

        public Dragon(String type, String name, int damage, int health, int armor) {
            this.type = type;
            this.name = name;
            this.damage = damage;
            this.health = health;
            this.armor = armor;
        }

        public String getName() {
            return this.name;
        }

        public int getDamage() {
            return this.damage;
        }

        public int getHealth() {
            return this.health;
        }

        public int getArmor() {
            return this.armor;
        }
    }


}
