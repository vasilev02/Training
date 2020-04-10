package google;

public class Person {
    private String name;
    private Company currentCompany;
    private Car currentCar;
    private Pokemon currentPokemons;
    private Parents currentParents;
    private Children currentChildren;

    public Person(String name) {
        this.name = name;
    }

    public void setCurrentCompany(Company currentCompany) {
        this.currentCompany = currentCompany;
    }

    public void setCurrentCar(Car currentCar) {
        this.currentCar = currentCar;
    }

    public void setCurrentPokemons(Pokemon currentPokemons) {
        this.currentPokemons = currentPokemons;
    }

    public Pokemon getCurrentPokemons() {
        return currentPokemons;
    }

    public Parents getCurrentParents() {
        return currentParents;
    }

    public void setCurrentParents(Parents currentParents) {
        this.currentParents = currentParents;
    }

    public Children getCurrentChildren() {
        return currentChildren;
    }

    public void setCurrentChildren(Children currentChildren) {
        this.currentChildren = currentChildren;
    }


    public Company getCurrentCompany() {
        return currentCompany;
    }

    public Car getCurrentCar() {
        return currentCar;
    }
}
