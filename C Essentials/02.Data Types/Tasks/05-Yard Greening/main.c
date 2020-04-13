#include <stdio.h>
#include <stdlib.h>

int main()
{

    double square_meters;


    scanf("%lf",&square_meters);


    double price_for_one_meter = 7.61;

    //Do the calculation

    double final_price = square_meters * price_for_one_meter;

    //Discount

    double discount = final_price * 0.18;

    printf("The final price is: %.2lf lv.\n",final_price - discount);

    printf("The discount is: %.2lf lv.",discount);


    return 0;
}
