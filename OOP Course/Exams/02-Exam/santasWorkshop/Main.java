package santasWorkshop;

import santasWorkshop.models.*;

public class Main {
    public static void main(String[] args) {

        Dwarf dwarf = new Sleepy("Junji");
        dwarf.work();
        System.out.println(dwarf.getEnergy());

    }
}
