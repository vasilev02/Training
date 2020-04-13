#include <stdio.h>
#include <stdlib.h>

int main()
{

    int lenght;

    int width;

    int height;

    double percent;


    scanf("%d",&lenght);

    scanf("%d",&width);

    scanf("%d",&height);

    scanf("%lf",&percent);


    int volume = height * width * lenght;

    double liters_needed = volume * 0.001;

    double items_volume = liters_needed * (percent * 0.01);

    printf("%.3lf",liters_needed - items_volume);

    return 0;
}
