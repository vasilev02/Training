package foodShortage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        List<Rebel> rebels = new ArrayList<>();
        List<Citizen> citizens = new ArrayList<>();

        int n = Integer.parseInt(buff.readLine());

        for (int i = 0; i <n ; i++) {
            String[] data = buff.readLine().split("\\s+");

            if(data.length==3){
                Rebel rebel = new Rebel(data[0],Integer.parseInt(data[1]),data[2]);
                rebels.add(rebel);
            }else{
                Citizen citizen = new Citizen(data[0],Integer.parseInt(data[1]),
                        data[2],data[3]);
                citizens.add(citizen);
            }
        }

        String command = buff.readLine();
        while (!command.equals("End")){

            Rebel rebel = null;
            Citizen citizen = null;

            for (Rebel current : rebels) {

                if(current.getName().equals(command)){
                    rebel = current;
                    rebel.buyFood();
                    break;
                }

            }

            if(rebel==null){

                for (Citizen current : citizens) {

                    if(current.getName().equals(command)){
                        citizen = current;
                        citizen.buyFood();
                        break;
                    }

                }

            }

            command = buff.readLine();
        }
        int sum = 0;
        for (Citizen citizen : citizens) {
            sum+=citizen.getFood();
        }

        for (Rebel rebel : rebels) {
            sum+=rebel.getFood();
        }

        System.out.print(sum);
    }
}
