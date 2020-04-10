package repository;

public class Person {

    private String name;
    private int age;
    private String birthDate;

    public Person(String name,int age,String birthDate){
        this.name = name;
        this.age=age;
        this.birthDate=birthDate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Name: %s",this.name));
        sb.append(System.lineSeparator());
        sb.append(String.format("Age: %d",this.age));
        sb.append(System.lineSeparator());
        sb.append(String.format("BirthDate: %s",this.birthDate));
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
