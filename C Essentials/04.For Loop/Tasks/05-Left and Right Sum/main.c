#include <stdio.h>
#include <stdlib.h>

int main()
{

    int n;
    
    scanf("%d", &n);
    
    int left_sum = 0;
    
    int right_sum = 0;
    
    for(int i = 1; i <= n; i++) {
            
        int current_num;
        
        scanf("%d", &current_num);
    
        left_sum = left_sum + current_num;
    }
    
    for(int i = 1; i <= n; i++) {
            
        int current_num;
        
        scanf("%d", &current_num);
    
        right_sum = right_sum + current_num;
    }
    
    // TODO: read and calculate the rightSum
    
    if(left_sum == right_sum) {

        printf("Yes, sum = %d\n", left_sum);

    } else {

        printf("No, diff = %d\n", abs(right_sum - left_sum));

    }

}
