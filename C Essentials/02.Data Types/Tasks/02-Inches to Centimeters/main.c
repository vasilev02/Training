#include <stdio.h>
#include <stdlib.h>

int main()
{

    double value;

    scanf("%lf",&value);

    //Result when compile inches to centimeters

    double result = value * 2.54;

    printf("%lf",result);

    return 0;
}
