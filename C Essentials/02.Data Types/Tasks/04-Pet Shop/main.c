#include <stdio.h>
#include <stdlib.h>

int main()
{

    int zoo_dogs_count;

    int my_dogs_count;

    scanf("%d",&zoo_dogs_count);

    scanf("%d",&my_dogs_count);



    double price_for_theZoo = 2.50;

    double price_for_my_dogs = 4.00;

    //Do the calculation

    double sum = (zoo_dogs_count * price_for_theZoo) + (my_dogs_count * price_for_my_dogs);

    printf("%.2f lv.",sum);



    return 0;
}
