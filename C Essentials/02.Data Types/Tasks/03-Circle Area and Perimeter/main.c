#include <stdio.h>
#include <stdlib.h>

int main()
{

    double radius;

    scanf("%lf",&radius);

    double NUMBER_PI = 3.14159265359;


    //Find area

    double area = NUMBER_PI * radius * radius;

    printf("%.2f\n",area);

    //Find parameter

    double parameter = 2 * NUMBER_PI * radius;

    printf("%.2f",parameter);


    return 0;
}
