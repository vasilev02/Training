package lights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        String[] data = buff.readLine().split("\\s+");

        TrafficLights[] values = TrafficLights.values();

        TrafficLights[] lights = new TrafficLights[data.length];
        for (int i = 0; i <data.length ; i++) {
            if(data[i].equals("RED")){
                lights[i]=TrafficLights.RED;
            }else if(data[i].equals("GREEN")){
                lights[i]=TrafficLights.GREEN;
            }else{
                lights[i]=TrafficLights.YELLOW;
            }
        }

        int n = Integer.parseInt(buff.readLine());
        StringBuilder builder = new StringBuilder();
        while (n-->0){

            for (int i = 0; i <lights.length ; i++) {

                if(lights[i].ordinal()+1==3){
                    lights[i]=TrafficLights.RED;
                    builder.append(String.format("%s ",lights[i].name()));
                }else{
                    int number = lights[i].ordinal();
                    number++;
                    TrafficLights light = values[number];
                    lights[i] = light;

                    builder.append(String.format("%s ",lights[i].name()));
                }

            }
            builder.append(System.lineSeparator());
        }
        System.out.println(builder.toString());
    }
}
