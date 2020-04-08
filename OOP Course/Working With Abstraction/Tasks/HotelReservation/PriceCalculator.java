package hotelReservation;

public class PriceCalculator {

    public static double getPrice(Reservation reservation){

        double result = reservation.getPrice()*reservation.getDays()*reservation.getSeason();

        double procent = 1;
        if(reservation.getType()!=0){
            procent = 1-(reservation.getType()/100.0);
        }


        return result*procent;
    }

}
