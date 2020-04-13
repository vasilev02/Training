#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main()
{

    int count_of_numbers;

    scanf("%d", &count_of_numbers);

    int divisible_by_two = 0;
    int divisible_by_three = 0;
    int divisible_by_four = 0;

    int i;

    for(i = 0; i < count_of_numbers; i++){

        int current_value;
        scanf("%d", &current_value);

        if(current_value % 2 ==0){
            divisible_by_two++;
        }

        if(current_value % 3 ==0){
            divisible_by_three++;
        }

        if(current_value % 4 ==0){
            divisible_by_four++;
        }

    }

    printf("%.2lf%%\n",(divisible_by_two*1.0)/count_of_numbers*100);

    printf("%.2lf%%\n",(divisible_by_three*1.0)/count_of_numbers*100);

    printf("%.2lf%%",(divisible_by_four*1.0)/count_of_numbers*100);

}
