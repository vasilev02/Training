package carsFuel;

public class Car {

    private String model;
    private double fuelMoney;
    private double costKM;
    private int distance;

    public Car(String model, double fuelMoney, double costKM, int distance) {
        this.model = model;
        this.fuelMoney = fuelMoney;
        this.costKM = costKM;
        this.distance = distance;
    }

    public double getCostKM() {
        return costKM;
    }

    public double drive(int km,double price){

        double sum = km*price;
        if(sum>this.fuelMoney){
            System.out.println("Insufficient fuel for the drive");
        }else{
            this.fuelMoney=this.fuelMoney-sum;
            this.distance+=km;
            return sum;
        }
        return sum;
    }

    public String toString(){
        return String.format("%s %.2f %d",this.model,this.fuelMoney,this.distance);
    }

}
