package teams;

public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age,double salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setSalary(salary);
    }

    public void increaseSalary(double bonus){
        Double result = null;
        bonus /= 100.0;
        if(this.getAge()<30){
            bonus /= 2.0;
            result = this.getSalary() * (1 + bonus);
        }else{
            result = this.getSalary() * (1 + bonus);
        }
        setSalary(result);
    }

    public double getSalary() {
        return this.salary;
    }

    private void setSalary(double salary) {

        if(salary<460.0){
            throw new IllegalArgumentException("Salary must be at least 460.0");
        }
        this.salary = salary;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.length()<3){
            throw new IllegalArgumentException("FirstName must be at least 3 symbols");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {

        if(lastName.length()<3){
            throw new IllegalArgumentException("SecondName must be at least 3 symbols");
        }
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {

        if(age<=0){
            throw new IllegalArgumentException("Age must be positive");
        }
        this.age = age;
    }

    private String getFullName(){
        return getFirstName()+" "+getLastName();
    }

    @Override
    public String toString() {
        return String.format("%s gets %f leva",getFullName(),getSalary());
    }
}
