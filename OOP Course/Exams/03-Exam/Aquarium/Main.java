package aquarium;

import aquarium.core.Controller;
import aquarium.core.ControllerImpl;
import aquarium.core.Engine;
import aquarium.core.EngineImpl;
import aquarium.models.aquariums.Aquarium;
import aquarium.models.aquariums.FreshwaterAquarium;
import aquarium.models.aquariums.SaltwaterAquarium;
import aquarium.models.fish.BaseFish;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;

import java.lang.reflect.Field;


public class Main {
    public static void main(String[] args) {



        Controller controller = new ControllerImpl();
        controller.addAquarium("FreshwaterAquarium","hello");
        controller.addAquarium("FreshwaterAquarium","my");
        controller.addAquarium("FreshwaterAquarium","name");
        System.out.println(controller.report());


    }
}
