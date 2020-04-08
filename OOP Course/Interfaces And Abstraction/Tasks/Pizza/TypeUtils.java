package piza;

import java.util.Map;

public class TypeUtils {

    public final static Map<String, Double> DOUGH_TYPES =
            Map.of("White", 1.5,
                    "Wholegrain", 1.0);

    public final static Map<String, Double> BAKING_TECHNIQUES =
            Map.of("Crispy", 0.9,
                    "Chewy", 1.1,
                    "Homemade", 1.0);

    public final static Map<String, Double> TOPPING_TYPES =
            Map.of("Meat", 1.2,
                    "Veggies", 0.8,
                    "Cheese", 1.1,
                    "Sauce", 0.9);
}