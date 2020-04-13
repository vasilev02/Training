#include <stdio.h>
#include <stdlib.h>

int main()
{

    int campaign_days;

    int workers_count;

    int cakes_count;

    int waffles_count;

    int pancakes_count;



    scanf("%d",&campaign_days);

    scanf("%d",&workers_count);

    scanf("%d",&cakes_count);

    scanf("%d",&waffles_count);

    scanf("%d",&pancakes_count);

    double total_amount = (cakes_count * 45) + (waffles_count * 5.80) + (pancakes_count * 3.20);



    double amount_for_total_days = total_amount * workers_count * campaign_days;


    amount_for_total_days = amount_for_total_days * 0.875;


    printf("%.2lf",amount_for_total_days);

    return 0;
}
