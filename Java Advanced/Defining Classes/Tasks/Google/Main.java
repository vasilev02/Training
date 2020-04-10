package google;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Person> people = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("End")){

            if(input.contains(" company ")){

                String[] companyData = input.split("\\s+");

                String name = companyData[0];
                String companyName = companyData[2];
                String department = companyData[3];
                String salary = companyData[4];
                Company currentCompany = new Company(companyName,department,salary);

                if(people.containsKey(name)){
                    Person person = people.get(name);
                    person.setCurrentCompany(currentCompany);
                    people.put(name,person);
                }else{
                    Person person = new Person(name);
                    person.setCurrentCompany(currentCompany);
                    people.put(name,person);
                }

            }else if(input.contains(" car ")){

                String[] carData = input.split("\\s+");
                String name = carData[0];
                String carModel = carData[2];
                String carSpeed = carData[3];

                Car currentCar = new Car(carModel,carSpeed);

                if(people.containsKey(name)){
                    Person person = people.get(name);
                    person.setCurrentCar(currentCar);
                    people.put(name,person);
                }else{
                    Person person = new Person(name);
                    person.setCurrentCar(currentCar);
                    people.put(name,person);
                }

            }else if(input.contains(" pokemon ")){

                String[] pokemonData = input.split("\\s+");
                String name = pokemonData[0];
                String pokemonName = pokemonData[2];
                String pokemonType = pokemonData[3];
                String pok = pokemonName+" "+pokemonType;

                Pokemon pokemons = new Pokemon();
                pokemons.add(pok);

                if(people.containsKey(name)){

                    Person person = people.get(name);
                    Pokemon poks = person.getCurrentPokemons();
                    if(poks!=null){
                        poks.add(pok);
                    }else{
                        person.setCurrentPokemons(pokemons);
                    }

                    people.put(name,person);

                }else{

                    Person person = new Person(name);
                    person.setCurrentPokemons(pokemons);
                    people.put(name,person);

                }



            }else if(input.contains(" parents ")){

                String[] parentData = input.split("\\s+");
                String name = parentData[0];
                String parent = parentData[2]+" "+parentData[3];

                Parents currentParent = new Parents();
                currentParent.add(parent);

                if(people.containsKey(name)){

                    Person person = people.get(name);
                    Parents parents = person.getCurrentParents();
                    if(parents!=null){
                        parents.add(parent);
                    }else{
                        person.setCurrentParents(currentParent);
                    }

                    people.put(name,person);

                }else{

                    Person person = new Person(name);
                    person.setCurrentParents(currentParent);
                    people.put(name,person);

                }

            }else if(input.contains(" children ")){

                String[] childrenData = input.split("\\s+");
                String name = childrenData[0];
                String parent = childrenData[2]+" "+childrenData[3];

                Children currentChildren = new Children();
                currentChildren.add(parent);

                if(people.containsKey(name)){

                    Person person = people.get(name);
                    Children children = person.getCurrentChildren();
                    if(children!=null){
                        children.add(parent);
                    }else{
                        person.setCurrentChildren(currentChildren);
                    }

                    people.put(name,person);

                }else{

                    Person person = new Person(name);
                    person.setCurrentChildren(currentChildren);
                    people.put(name,person);

                }

            }

            input = scanner.nextLine();
        }

        String name = scanner.nextLine();

        for (Map.Entry<String, Person> entry : people.entrySet()) {

            if(name.equals(entry.getKey())){
                Person person = people.get(name);
                System.out.println(name);

                System.out.println("Company:");
                if(person.getCurrentCompany()!=null){
                    System.out.printf("%s %s ",person.getCurrentCompany().getCompanyName(),person.getCurrentCompany().getDepartment());
                    double salary = Double.parseDouble(person.getCurrentCompany().getSalary());
                    System.out.printf("%.2f%n",salary);
                }

                System.out.println("Car:");
                if(person.getCurrentCar()!=null) {
                    System.out.printf("%s %s%n", person.getCurrentCar().getCarModel(), person.getCurrentCar().getCarSpeed());
                }

                System.out.println("Pokemon:");
                if(person.getCurrentPokemons()!=null) {
                    Pokemon pokemon = person.getCurrentPokemons();
                    List<String> pokemonList = pokemon.getPokemonList();
                    for (String s : pokemonList) {
                        System.out.println(s);
                    }
                }

                System.out.println("Parents:");
                if(person.getCurrentCompany()!=null) {
                    Parents parents = person.getCurrentParents();
                    List<String> parentsList = parents.getParentsList();
                    for (String s : parentsList) {
                        System.out.println(s);
                    }
                }
                System.out.println("Children:");
                if(person.getCurrentCompany()!=null) {
                    Children children = person.getCurrentChildren();
                    List<String> childrenList = children.getChildrenList();
                    for (String s : childrenList) {
                        System.out.println(s);
                    }
                }
            }

        }

    }
}
