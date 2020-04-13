#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{

    double firstNumber;

    double secondNumber;

    scanf("%lf",&firstNumber);

    scanf("%lf",&secondNumber);

    double result = (firstNumber*firstNumber) + (secondNumber * secondNumber);

    printf("Hypotenuse is %.0lf.",sqrt(result));

    return 0;
}
