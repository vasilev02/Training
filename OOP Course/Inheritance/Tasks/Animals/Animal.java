package animals;

public abstract class Animal {

    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.name = name;
        setAge(age);
        this.gender = gender;
    }

    public abstract String produceSound();

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setAge(int age) {

        if(age<0){
            throw new IllegalArgumentException("Invalid input!");
        }

        this.age = age;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s",this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());

        sb.append(String.format("%s %d %s",getName(),getAge(),getGender()));
        sb.append(System.lineSeparator());

        sb.append(produceSound());
        sb.append(System.lineSeparator());

        return sb.toString().trim();
    }
}
