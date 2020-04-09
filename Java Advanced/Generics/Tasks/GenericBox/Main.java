package genericBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(buff.readLine());

        for (int i = 0; i <n ; i++) {
            Box b = new Box(buff.readLine());
            System.out.println(b.toString());
        }

    }
}
