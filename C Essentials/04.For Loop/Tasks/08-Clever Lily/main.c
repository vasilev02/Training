#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main()
{

    int age;

    double washing_machine_price;

    int toy_price;

    scanf("%d", &age);

    scanf("%lf", &washing_machine_price);

    scanf("%d", &toy_price);

    int toy_counter = 0;
    double money_saved = 0.0;

    double given_sum = 9;

    int i;

    for(i = 1; i <=age; i++){

        if(i % 2 == 1){

            toy_counter++;

        }else{

        money_saved = money_saved + given_sum;

        given_sum = given_sum + 10;

        }

    }

    money_saved = money_saved + (toy_counter * toy_price);

    if(money_saved >= washing_machine_price){

        printf("Yes! %.2lf",money_saved - washing_machine_price);

    }else{

        printf("No! %.2lf",washing_machine_price - money_saved);

    }

}
